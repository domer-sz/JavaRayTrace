/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.umk.raytracking.geometry.Sphere;
import pl.umk.raytracking.light.Light;
import pl.umk.raytracking.projection.Orthographic;
import pl.umk.raytracking.projection.Perspective;
import pl.umk.raytracking.projection.Projection;
import pl.umk.raytracking.sampling.JitteredSample;
import pl.umk.raytracking.sampling.RegularSample;
import pl.umk.raytracking.sampling.Sampler;
import pl.umk.raytracking.scene.World;
import pl.umk.raytracking.threads.ThreadRenderer;
import pl.umk.raytracking.utility.Color;
import pl.umk.raytracking.utility.Image;

import pl.umk.raytracking.utility.Vector3D;

/**
 *
 * @author Szymon
 */
public class Driver {

    public static World world;
    public static Image myImage;
    public static Tracer tracer;
    public static Sampler sampler;
    public static Projection projection;
    // public static Projection lightProjection;

    public static double ambientlight = -0.2;
    public static double accuracy = 0.000000001;

    public Driver() throws IOException {
        long start = System.nanoTime();
        Random random = new Random();

        world = new World(800, 600, 1);
        myImage = new Image("Image.png");

        sampler = new RegularSample(3);
        projection = new Perspective(new Vector3D(0, 0, 800), new Vector3D(0.0, 0.0, 0.0), 35);
        tracer = new Tracer();
        for (int y = 0; y < world.viewPlane.height; y += 1) {
            for (int x = 0; x < world.viewPlane.width; x += 1) {

                // tracer = new Tracer(x,y);
                // tracer.run();
                tracer.trace(x, y);
            }
        }

//        for (int y = world.viewPlane.height / 2; y < world.viewPlane.height; y += 1) {
//            for (int x = 0; x < world.viewPlane.width; x += 1) {
//
//                //tracer = new Tracer(x,y);
//                //tracer.run();
//            }
//        }

        myImage.write("PNG");

        long end = System.nanoTime();
       // System.out.println("time: " + (end - start) / 1000000000.0F);

    }

    public static BufferedImage render(int width, int height, double ambit, double camPosX, double camPosY, double camPosZ,
            double camLookAtX, double camLookAtY, double camLookAtZ, int antyAliasing, String filepath) throws IOException {

        long start = System.nanoTime();

        ambientlight = ambit;

        world = new World(width, height, 1);
        if (!"".equals(filepath)) {
            myImage = new Image(filepath);
        } else {
            myImage = new Image("Image.png");
        }

        tracer = new Tracer();
        sampler = new RegularSample(antyAliasing);
        projection = new Perspective(new Vector3D(camPosX, camPosY, camPosZ), new Vector3D(camLookAtX, camLookAtY, camLookAtZ), 35);
         
       int p = 0;
       //ExecutorService pool = Executors.newCachedThreadPool();
       ExecutorService pool = Executors.newFixedThreadPool(height/100);
       Set<Future<Integer>> set  = new HashSet<> (height/100);
       
       for (int y = 0; y < world.viewPlane.height; y += 100) {
            Callable<Integer> callable = new ThreadRenderer(width, y);
            Future<Integer> future = pool.submit(callable);
            set.add(future);
        }
       
        for (Future<Integer> thread : set) {
            try {
                p += thread.get();
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //System.out.println("skladanie renderu zakonczone " + p);
 

        if (!"".equals(filepath)) {
            myImage.write("PNG");
        }

        long end = System.nanoTime();
        System.out.println("time: " + (end - start) / 1000000000.0F);

        return myImage.buffer;
    }
}
