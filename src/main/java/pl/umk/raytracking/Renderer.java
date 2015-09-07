/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Szymon
 */
public class Renderer extends Thread{
    private BufferedImage bf;
    
            double camx ;
            double camy ;
            double camz ;
            double lookx ;
            double lookz ;
            double looky ;
            double ambilent ;
            int a;

    public Renderer(double camx, double camy, double camz, double lookx, double lookz, double looky, double ambilent, int a) {
        this.bf = bf;
        this.camx = camx;
        this.camy = camy;
        this.camz = camz;
        this.lookx = lookx;
        this.lookz = lookz;
        this.looky = looky;
        this.ambilent = ambilent;
        this.a = a;
    }

    
    
    @Override
    public void run(){
        try {  
            Driver.render(640, 480,ambilent ,
                    camx, camy, camz, lookx ,
                    looky, lookz, a, "");
        } catch (IOException ex) {
            Logger.getLogger(Renderer.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
