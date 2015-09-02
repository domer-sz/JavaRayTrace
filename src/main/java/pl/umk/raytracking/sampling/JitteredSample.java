/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking.sampling;

import java.util.Random;
import pl.umk.raytracking.Driver;
import pl.umk.raytracking.utility.Point2D;

/**
 *
 * @author Szymon
 */
public class JitteredSample extends Sampler {

    public Random random = new Random();

    public JitteredSample(int samples) {
        this.samples = samples;
    }

    @Override
    public Point2D sample(int row, int col, int x, int y) {

        return new Point2D(x - Driver.world.viewPlane.width / 2 + (col + random.nextFloat()) / samples, y - Driver.world.viewPlane.height / 2 + (row + random.nextFloat()) / samples);
    }

}
