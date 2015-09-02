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
public abstract class Source {
    

    public abstract Vector3D getPosition();

    public abstract void setPosition(Vector3D position);

    public abstract Color getColor();

    public abstract void setColor(Color color);
    
    
}
