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
public class Plane implements GeometricObject{
    Vector3D point;
    Vector3D normal;
    public Color color;   
    
    public Plane(Vector3D point, Vector3D normal, Color color) {
        this.point = new Vector3D(point);
        this.normal = new Vector3D(normal);
        this.color = new Color(color);
    }

    @Override
    public double hit(Ray ray) {
        double t = point.sub(ray.origin).dot(normal)/ray.direction.dot(normal);       
        
        if(t > 10E-9) return t;
        else return 0.0;
    }   
    
    @Override
    public double findIntersection(Ray ray) {
        return hit(ray);
    }

    public Vector3D getNormalAt(Vector3D point) {
        return normal;
    }

    @Override
    public Vector3D getPointAt() {
       return this.point;
    }

    @Override
    public Color getColor() {
      return color;
    }


    
    
}
