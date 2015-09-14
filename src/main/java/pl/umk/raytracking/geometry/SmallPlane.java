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
public class SmallPlane {

    private Vector3D C, F, E;
    private final List<GeometricObject> planeAsTriangleList = new ArrayList<>(2);

    public SmallPlane(Vector3D corner1, int height, int width, Color color) {

        planeAsTriangleList.add(new Triangle(
                corner1,
                new Vector3D(corner1.x, corner1.y + height, corner1.z),
                new Vector3D(corner1.x - width, corner1.y, corner1.z),
                color));
        planeAsTriangleList.add(new Triangle(
                new Vector3D(corner1.x, corner1.y + height, corner1.z),
                new Vector3D(corner1.x - width, corner1.y + height, corner1.z),
                new Vector3D(corner1.x - width, corner1.y, corner1.z),
                color));
    }

    public List<GeometricObject> addToScene() {
        return planeAsTriangleList;
    }
}
