/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking.scene;

import java.util.ArrayList;
import pl.umk.raytracking.geometry.GeometricObject;
import pl.umk.raytracking.geometry.Plane;
import pl.umk.raytracking.geometry.Sphere;
import pl.umk.raytracking.light.Light;
import pl.umk.raytracking.light.Source;
import pl.umk.raytracking.utility.Color;
import pl.umk.raytracking.utility.Vector3D;


/**
 *
 * @author Szymon
 */
public class World {
    public ViewPlane viewPlane;
    public ArrayList<GeometricObject> objects;
    public ArrayList<Source> lightSources;
    public Color background;
    
    public World(int width, int height, double size){
        viewPlane = new ViewPlane(width, height, size);
        background = new Color(0.0F ,0.0F, 0.0F, 0);
        
        objects = new ArrayList<>();
        lightSources = new ArrayList<>();
        
        Color shinyRed = new Color(1, 0, 0, 0.65F);
        Color shinyGreen = new Color(0, 1, 0, 0.65F);
        Color shinyBlue = new Color(0, 0, 1, 0.65F);
        Color shinyWhite = new Color(1, 1, 1, 0.65F);
        Color shinyMagenta = new Color(1, 0, 1, 0.65F);
        Color shinyYellow = new Color(1, 1, 0, 0.65F);
        
        objects.add(new Sphere(new Vector3D(-150, 0, 330), 20, shinyRed));
        
        objects.add(new Sphere(new Vector3D(+450, 50, 390), 50, shinyRed));
        objects.add(new Sphere(new Vector3D(0, 0, -150), 150, shinyMagenta));
        objects.add(new Sphere(new Vector3D(-650, 0, 0), 150, shinyGreen));
        objects.add(new Sphere(new Vector3D(-650, 0, -400), 100, shinyYellow));
        objects.add(new Sphere(new Vector3D(350, 0, 0), 150, shinyBlue));        
        objects.add(new Sphere(new Vector3D(0, 0, 200), 25, shinyWhite));
        objects.add(new Sphere(new Vector3D(-300, 0, 400), 56, shinyYellow));
        
        objects.add(new Plane(new Vector3D(0.0, -100.0, 0.0), new Vector3D(0.0, 1.0, 0.0 ) , new Color(1.0F, 1.0F,  0.0F, 2)) );
        
         
        
        Color whiteLight = new Color(1.0F, 1.0F, 1.0F, 0);
        Light light = new Light(new Vector3D(-300.0, 000.0, 700.0), whiteLight);
        Light light2 = new Light(new Vector3D(-300.0, 300.0, -3000.0), whiteLight);
        lightSources.add(light);
        lightSources.add(light2);
       
        
    }
}
