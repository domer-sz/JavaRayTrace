/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking.projection;

import pl.umk.raytracking.utility.Point2D;

import pl.umk.raytracking.utility.Ray;
import pl.umk.raytracking.utility.Vector3D;

/**
 *
 * @author Szymon
 */
public abstract class Projection {
    
    public Vector3D eye;
    public Vector3D lookAt;
    public double distance;
    public Vector3D u, v, w;
    
    public abstract Ray createRay(Point2D point);
    
    public void  computeUVW(){
        
        w = eye.sub(lookAt);
       // System.out.println(w);
        w.normalize();
       // System.out.println(w);
        
        Vector3D up = new Vector3D(0.00424, 1.0, 0.00764);
        
        u = up.cross(w);
       // System.out.println(u);
        u.normalize();
       // System.out.println(u);
        
        v = w.cross(u);
       // System.out.println(v);
        v.normalize();
      //  System.out.println(v);
        
        
    }
}
