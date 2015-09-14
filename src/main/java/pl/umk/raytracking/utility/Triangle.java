/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking.utility;

import pl.umk.raytracking.geometry.GeometricObject;
import pl.umk.raytracking.utility.Color;

import pl.umk.raytracking.utility.Ray;
import pl.umk.raytracking.utility.Vector3D;

/**
 *
 * @author Szymon
 */
public class Triangle implements GeometricObject{
    public Vector3D A,B,C;
    public Vector3D normal;
    double distance;
    public Color color;   
    
    public Triangle(){
        A = new Vector3D(1, 0, 0);
        B = new Vector3D(0, 1, 0);
        C = new Vector3D(0, 0, 1);
        
        color = new Color(0.5F, 0.5F, 0.5F, 0.2F);
    }
    
    public Triangle(Vector3D A, Vector3D B, Vector3D C, Color color) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.color = color;
    }
    
    public Vector3D getTriangleNormal(){
        Vector3D CA = new Vector3D(C.x - A.x, C.y - A.y , C.z - A.z);
        Vector3D BA = new Vector3D(B.x - A.x, B.y - A.y , B.z - A.z);
        normal = CA.crossProduct(BA).normalizeout();
        return normal;
    }
    
    public double getTriangleDistance(){
        normal = getTriangleNormal();
        distance = normal.dotProduct(A);
        
        return distance;
    }


    @Override
    public double findIntersection(Ray ray) {
		Vector3D ray_direction = ray.direction;
		Vector3D ray_origin = ray.origin;
                        
                normal = getTriangleNormal();
		double a = getTriangleDistance();
		
		if (a == 0) {
			// ray is parallel to the triangle
			return -1;
		}
		else {
			double b = normal.dotProduct(ray.origin.vectAdd(normal.vectMult(distance).negative()));
                        double distance2plane = -1*b/a;
                        
                        double Qx = ray_direction.vectMult(distance2plane).getX() + ray_origin.getX();
                        double Qy = ray_direction.vectMult(distance2plane).getY() + ray_origin.getY();
                        double Qz = ray_direction.vectMult(distance2plane).getZ() + ray_origin.getZ();
                        
                        Vector3D Q = new Vector3D(Qx, Qy, Qz);
                        
                        Vector3D CA = new Vector3D(C.x - A.x, C.y - A.y , C.z - A.z);
                        Vector3D QA = new Vector3D(Q.x - A.x, Q.y - A.y , Q.z - A.z);                        
                        double test1 = (CA.crossProduct(QA)).dotProduct(normal);
                        
                        Vector3D BC = new Vector3D(B.x - C.x, B.y - C.y , B.z - C.z);
                        Vector3D QC = new Vector3D(Q.x - C.x, Q.y - C.y , Q.z - C.z);                        
                        double test2 = (BC.crossProduct(QC)).dotProduct(normal);
                        
                        Vector3D AB = new Vector3D(A.x - B.x, A.y - B.y , A.z - B.z);
                        Vector3D QB = new Vector3D(Q.x - B.x, Q.y - B.y , Q.z - B.z);                        
                        double test3 = (AB.crossProduct(QB)).dotProduct(normal);
                        
                        
                        if( (test1 >= 0) && (test2 >= 0) && (test3 >= 0) ){
                            // w trojakcie
                            return -1*b/a;
                        }else{
                            //poza trojkatem
                         return -1;
                        }
			
		}
	}

    public Vector3D getNormalAt(Vector3D point) {
        return normal;
    }

    @Override
    public Vector3D getPointAt() {
       return new Vector3D();
    }

    @Override
    public Color getColor() {
      return color;
    }

    @Override
    public double hit(Ray ray) {
        return findIntersection(ray);
    }


    
    
}
