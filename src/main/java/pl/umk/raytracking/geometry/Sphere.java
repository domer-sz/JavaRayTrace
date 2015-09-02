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
public class Sphere implements GeometricObject{
    public Vector3D center;
    public double radius;
    public Color color;   
    
    public Sphere(Vector3D center, double radius, Color color) {
        this.center = new Vector3D(center);
        this.radius = radius;
        this.color = new Color(color);
    }

    @Override
    public double hit(Ray ray) {
       double a = ray.direction.dot(ray.direction);
       double b = 2*ray.origin.sub(center).dot(ray.direction);
       double c = ray.origin.sub(center).dot(ray.origin.sub(center))-radius*radius;
       
       double discriminantant = b*b-4*a*c;
       
       if(discriminantant < 0.0){
           return 0.0;
       }else{
           double t = (-b - Math.sqrt(discriminantant))/(2*a);
           
           if(t >10E-9){
               return t;
           }else {
               //return ((Math.sqrt(discriminantant) -b)/2) -0.000001;
               return 0.0;
           }
       }
    }
    
    public Vector3D getNormalAt(Vector3D point) {
		Vector3D normal_Vect;
        normal_Vect = point.vectAdd(center.negative()).normalizeout();
		return normal_Vect;
	}

    @Override
    public double findIntersection(Ray ray) {
        Vector3D ray_origin = ray.origin;
		double ray_origin_x = ray_origin.getX();
		double ray_origin_y = ray_origin.getY();
		double ray_origin_z = ray_origin.getZ();
		
		Vector3D ray_direction = ray.direction;
		double ray_direction_x = ray_direction.getX();
		double ray_direction_y = ray_direction.getY();
		double ray_direction_z = ray_direction.getZ();
		
		Vector3D sphere_center = center;
		double sphere_center_x = sphere_center.getX();
		double sphere_center_y = sphere_center.getY();
		double sphere_center_z = sphere_center.getZ();
		
		double a = 1; // normalized
		double b = (2*(ray_origin_x - sphere_center_x)*ray_direction_x) + (2*(ray_origin_y - sphere_center_y)*ray_direction_y) + (2*(ray_origin_z - sphere_center_z)*ray_direction_z);
		double c = Math.pow(ray_origin_x - sphere_center_x, 2) + Math.pow(ray_origin_y - sphere_center_y, 2) + Math.pow(ray_origin_z - sphere_center_z, 2) - (radius*radius);
		
		double discriminant = b*b - 4*c;
		
		if (discriminant > 0) {
			/// the ray intersects the sphere
			
			// the first root
			double root_1 = ((-1*b - Math.sqrt(discriminant))/2) - 0.000001;
			
			if (root_1 > 0) {
				// the first root is the smallest positive root
				return root_1;
			}
			else {
				// the second root is the smallest positive root
				double root_2 = ((Math.sqrt(discriminant) - b)/2) - 0.000001;
				return root_2;
			}
		}
		else {
			// the ray missed the sphere
			return -1;
		}
    }

    @Override
    public Vector3D getPointAt() {
       return this.center;
    }

    @Override
    public Color getColor() {
        return  color;
    }
    
    
}
