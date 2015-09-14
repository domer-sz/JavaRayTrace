/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking.geometry;

import java.util.ArrayList;
import java.util.List;
import pl.umk.raytracking.utility.Color;
import pl.umk.raytracking.utility.Triangle;
import pl.umk.raytracking.utility.Vector3D;

/**
 *
 * @author Szymon
 */
public class Cube {
    private Vector3D A,B,C,D,E,F;
    private List<GeometricObject> cubeAsTriangleList = new ArrayList<>(12);
    
    public Cube(Vector3D corner1, Vector3D corner2, Color color){
        double c1x = corner1.x;
        double c1y = corner1.y;
        double c1z = corner1.z;
        
        double c2x = corner2.x;
        double c2y = corner2.y;
        double c2z = corner2.z;
        
        A = new Vector3D(c2x, c1y, c1z);
        B = new Vector3D(c2x, c1y, c2z);
        C = new Vector3D(c1x, c1y, c2z);
        
        D = new Vector3D(c2x, c2y, c1z);
        E = new Vector3D(c1x, c2y, c1z);
        F = new Vector3D(c1x, c2y, c2z);
        
        //left side
        cubeAsTriangleList.add(new Triangle(D, A, corner1, color));
        cubeAsTriangleList.add(new Triangle(corner1, E, D, color));
        //far side
        cubeAsTriangleList.add(new Triangle(corner2, B, A, color));
        cubeAsTriangleList.add(new Triangle(A, D, corner2, color));
        //right side
        cubeAsTriangleList.add(new Triangle(F, C, B, color));
        cubeAsTriangleList.add(new Triangle(B, corner2, F, color));
        //frons side
        cubeAsTriangleList.add(new Triangle(E, corner1, C, color));
        cubeAsTriangleList.add(new Triangle(C, F, E, color));
        //top
        cubeAsTriangleList.add(new Triangle(D, E, F, color));
        cubeAsTriangleList.add(new Triangle(F, corner2, D, color));
        //bottom
        cubeAsTriangleList.add(new Triangle(corner1, A, B, color));
        cubeAsTriangleList.add(new Triangle(B, C, corner1, color));
        
    }
    
    public List<GeometricObject> addToScene(){
        return cubeAsTriangleList;
    }
}
