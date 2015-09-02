/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking.geometry;

import pl.umk.raytracking.utility.Color;

import pl.umk.raytracking.utility.Ray;
import pl.umk.raytracking.utility.Vector3D;


/**
 *
 * @author Szymon
 */
public interface GeometricObject {
     
    public abstract double hit(Ray ray);
    public abstract Vector3D getNormalAt(Vector3D point);
    public abstract Color getColor();
    public abstract Vector3D getPointAt();
    public abstract double findIntersection(Ray ray) ;

    
    
}
