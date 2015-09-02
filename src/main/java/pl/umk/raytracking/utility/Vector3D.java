/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking.utility;

/**
 *
 * @author Szymon
 */
public class Vector3D {

    public double x, y, z;

    public Vector3D() {
        x = 0.0;
        y = 0.0;
        z = 0.0;
    }

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(Vector3D vector) {
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
    }

    public Vector3D add(Vector3D vector) {
        return new Vector3D(x + vector.x, y + vector.y, z + vector.z);
    }
    
    

    public Vector3D sub(Vector3D vector) {
        return new Vector3D(x - vector.x, y - vector.y, z - vector.z);
    }
    
    public Vector3D mult(double scalar){
        return new Vector3D(x*scalar, y*scalar, z*scalar);
    }
    
     
    public Vector3D cross(Vector3D vector){
        Vector3D tempVector = new Vector3D();
        tempVector.x = this.y*vector.z - this.z*vector.y;
        tempVector.y = this.z*vector.x - this.x*vector.z;
        tempVector.z = this.x*vector.y - this.y*vector.x;
        
        return tempVector;
    }

    public double dot(Vector3D vector) {
        return x * vector.x + y * vector.y + z * vector.z;
    }

   
    
     public void normalize(){
        double magnitude = Math.sqrt(x*x + y*y + z*z);
        
        x/= magnitude;
        y/= magnitude;
        z/= magnitude;
        
    }

    public double magnitude () {
                    return Math.sqrt((x*x) + (y*y) + (z*z));
        }
	
	public Vector3D normalizeout () {
		double magnitude = Math.sqrt((x*x) + (y*y) + (z*z));
		return new Vector3D (x/magnitude, y/magnitude, z/magnitude);
	}
	
	public Vector3D negative () {
		return new Vector3D (-x, -y, -z);
	}
	
	public double dotProduct(Vector3D v) {
		return x*v.getX() + y*v.getY() + z*v.getZ();
	}
	
	public Vector3D crossProduct(Vector3D v) {
		return new Vector3D (y*v.getZ() - z*v.getY(), z*v.getX() - x*v.getZ(), x*v.getY() - y*v.getX());
	}
	
	public Vector3D vectAdd (Vector3D v) {
		return new Vector3D (x + v.getX(), y + v.getY(), z + v.getZ());
	}

	public Vector3D vectMult (double scalar) {
		return new Vector3D (x*scalar, y*scalar, z*scalar);
	}

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Vector3D{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
     
        
    
   
}
