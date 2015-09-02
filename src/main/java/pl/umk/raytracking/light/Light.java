/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking.light;


import pl.umk.raytracking.utility.Color;
import pl.umk.raytracking.utility.Vector3D;

/**
 *
 * @author Szymon
 */
public class Light extends Source{
    Vector3D position;
    Color color;

    public Light(){
        this.position = new Vector3D(0, 0, 0);
        this.color = new Color(1.0F, 1.0F, 1.0F, 0.25F);
    };
    
    public Light(Vector3D position, Color color) {
        this.position = position;
        this.color = color;
    }

    @Override
    public Vector3D getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector3D position) {
        this.position = position;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    
    
    
    
    
}
