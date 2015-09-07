/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking.utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import pl.umk.raytracking.Driver;

/**
 *
 * @author Szymon
 */
public class Image {
    public static BufferedImage buffer;
    public File image;
    private String filen;
    
    public Image(String filename){
        image = new File(filename);
        filen = filename;
        buffer = new BufferedImage(Driver.world.viewPlane.width, Driver.world.viewPlane.height, BufferedImage.TYPE_INT_RGB);
        
    }
        
    public void write(String filetype){
        try {
            //image = new File(new Date().toString() + filen);
            ImageIO.write(buffer, filetype, image);
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not write file");
            System.exit(1);
        }
    }
    
}
