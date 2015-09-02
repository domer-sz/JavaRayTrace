/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking.sampling;

import pl.umk.raytracking.Driver;
import pl.umk.raytracking.utility.Point2D;

/**
 *
 * @author Szymon
 */
public class RegularSample extends Sampler{
    
    public RegularSample(int samples){
        this.samples = samples;
    }
    
    @Override
    public Point2D sample(int row, int col, int x, int y) {      
       return new Point2D(x-Driver.world.viewPlane.width/2+(col+.5)/samples, y-Driver.world.viewPlane.height/2+(row+.5)/samples );
    }
    
}
