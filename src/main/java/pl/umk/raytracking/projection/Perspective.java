/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking.projection;

import pl.umk.raytracking.Driver;
import pl.umk.raytracking.utility.Point2D;

import pl.umk.raytracking.utility.Ray;
import pl.umk.raytracking.utility.Vector3D;

/**
 *
 * @author Szymon
 */
public class Perspective extends Projection {
    
    public Perspective(Vector3D eye, Vector3D lookAt, double FOV) {
        this.eye = new Vector3D(eye);
        this.lookAt = new Vector3D(lookAt);
        this.distance = Driver.world.viewPlane.height / 2 / Math.tan(Math.toRadians(FOV));
       
        computeUVW();
    }
    
    @Override
    public Ray createRay(Point2D point) {
        Ray ray = new Ray(new Vector3D(eye), u.mult(point.x).add(v.mult(point.y).sub(w.mult(distance))));
        ray.direction.normalize();
        return ray;
    }
    
}
