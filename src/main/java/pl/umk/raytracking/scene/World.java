/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking.scene;

import java.util.ArrayList;
import pl.umk.raytracking.geometry.Cube;
import pl.umk.raytracking.geometry.GeometricObject;
import pl.umk.raytracking.geometry.Plane;
import pl.umk.raytracking.geometry.SmallPlane;
import pl.umk.raytracking.geometry.Sphere;
import pl.umk.raytracking.light.Light;
import pl.umk.raytracking.light.Source;
import pl.umk.raytracking.utility.Color;
import pl.umk.raytracking.utility.Triangle;
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
        background = new Color(0.0F ,0.0F, 0.2F, 0);
        
        objects = new ArrayList<>();
        lightSources = new ArrayList<>();
        
        Color shinyRed = new Color(1, 0, 0, 0.65F);
        Color shinyGreen = new Color(0, 1, 0, 0.65F);
        Color shinyBlue = new Color(0, 0, 1, 0.65F);
        Color shinyWhite = new Color(1, 1, 1, 0.65F);
        Color shinyMagenta = new Color(1, 0, 1, 0.65F);
        Color shinyYellow = new Color(1, 1, 0, 0.65F);
        Color shinySilver = new Color(0.75F, 0.75F, 0.75F, 1);
        
        Color whiteLight = new Color(1.0F, 1.0F, 1.0F, 0);
         
        //sfery
        objects.add(new Sphere(new Vector3D(-50, 0, 0), 50, shinyRed));        
        objects.add(new Sphere(new Vector3D(+450, 150, 390), 50, shinyRed));
          objects.add(new Sphere(new Vector3D(0, 0, -150), 130, shinyMagenta));
        objects.add(new Sphere(new Vector3D(-650, 0, 0), 150, shinyGreen));
        
        //objects.add(new Sphere(new Vector3D(-650, 0, -400), 100, shinyYellow));        
        objects.add(new Sphere(new Vector3D(350, 0, 0), 150, shinyBlue));        
        objects.add(new Sphere(new Vector3D(0, 0, 200), 25, shinyWhite));
        objects.add(new Sphere(new Vector3D(-300, 0, 400), 56, shinyYellow));
        
        //kostka
        //objects.addAll(new Cube(new Vector3D(-100, 100, 0), new Vector3D(100, 200, -200), shinyRed).addToScene());
        
        //lustro
        objects.addAll(new SmallPlane(new Vector3D(-100, -50, -200), 200, 500, shinySilver).addToScene());
        
        //podloga
        objects.add(new Plane(new Vector3D(0.0, -100.0, 0.0), new Vector3D(0.0, 1.0, 0.0 ) , new Color(0.3F, 0.3F,  0.3F, 0.1F, 1)) );
       
        
        
        
        //nad swiatlem
        //objects.add(new Sphere(new Vector3D(-300.0, 550.0, 800.0), 20, whiteLight));
        
       
        Light light = new Light(new Vector3D(-300.0, 500.0, 800.0), whiteLight);
        //Light light2 = new Light(new Vector3D(-300.0, 300.0, -3000.0), whiteLight);
        lightSources.add(light);
       // lightSources.add(light2);
       
        
    }
}
