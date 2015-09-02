/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import pl.umk.raytracking.geometry.Sphere;
import pl.umk.raytracking.light.Light;
import pl.umk.raytracking.projection.Orthographic;
import pl.umk.raytracking.projection.Perspective;
import pl.umk.raytracking.projection.Projection;
import pl.umk.raytracking.sampling.JitteredSample;
import pl.umk.raytracking.sampling.RegularSample;
import pl.umk.raytracking.sampling.Sampler;
import pl.umk.raytracking.scene.World;
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
    
    public static double ambientlight = 0.2;
    public static double accuracy = 0.000000001;
    
    public  Driver() throws IOException {
        long start = System.nanoTime();
        Random random = new Random();

        world = new World(640, 480, 1);
        myImage = new Image("Image.png");
        tracer = new Tracer();
        sampler = new RegularSample(3);
        projection = new Perspective(new Vector3D(0, 0, 800), new Vector3D(0.0, 0.0, 0.0), 35);
          
        
        for (int y = 0; y < world.viewPlane.height/2; y+=1) {
            for (int x = 0; x < world.viewPlane.width; x+=1) {

               tracer.trace(x,y);
            }
        }
        
        for (int y = world.viewPlane.height/2; y < world.viewPlane.height; y+=1) {
            for (int x = 0; x < world.viewPlane.width; x+=1) {

               tracer.trace(x,y);
            }
        }

        myImage.write("PNG");
        
        long end = System.nanoTime();
        System.out.println("time: " + (end - start) / 1000000000.0F);

    }
    
    public BufferedImage render(int width, int height, double ambit, double camPosX, double camPosY, double camPosZ,
            double camLookAtX, double  camLookAtY, double camLookAtZ, int antyAliasing, String filepath) throws IOException{

        long start = System.nanoTime();
        //Random random = new Random();
        
        System.out.println(camPosX + " " +camPosZ + " " + camPosY);
        System.out.println(camLookAtX + " " +camLookAtY + " " + camLookAtZ);
        System.out.println("antyalising: " + antyAliasing);
        
        ambientlight = ambit;

        world = new World(width, height, 1);
        if(!"".equals(filepath)) myImage = new Image(filepath);
        else myImage = new Image("Image.png");
        tracer = new Tracer();
        sampler = new RegularSample(antyAliasing);
        projection = new Perspective(new Vector3D(camPosX, camPosY, camPosZ), new Vector3D(camLookAtX, camLookAtY, camLookAtZ), 35);
          
        
        for (int y = 0; y < world.viewPlane.height/2; y+=1) {
            for (int x = 0; x < world.viewPlane.width; x+=1) {

               tracer.trace(x,y);
            }
        }
        
        for (int y = world.viewPlane.height/2; y < world.viewPlane.height; y+=1) {
            for (int x = 0; x < world.viewPlane.width; x+=1) {

               tracer.trace(x,y);
            }
        }

         if(!"".equals(filepath)) myImage.write("PNG");
        
        long end = System.nanoTime();
        System.out.println("time: " + (end - start) / 1000000000.0F);
        
         return myImage.buffer;
    }
}
