/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking.projection;

import pl.umk.raytracking.Driver;
import pl.umk.raytracking.utility.Point2D;

import pl.umk.raytracking.utility.Ray;


/**
 *
 * @author Szymon
 */
public class Orthographic extends Projection{

    @Override
    public Ray createRay(Point2D point) {
      Ray ray = new Ray();
      
      ray.origin.x = Driver.world.viewPlane.size*(point.x);
      ray.origin.y = Driver.world.viewPlane.size*(point.y);
      ray.origin.z = 100;
      
      ray.direction.x = 0.0;
      ray.direction.y = 0.0;
      ray.direction.z = -1.0;
      
      return ray;
    }
    
}
