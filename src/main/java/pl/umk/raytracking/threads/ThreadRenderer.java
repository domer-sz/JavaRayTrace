
package pl.umk.raytracking.threads;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.umk.raytracking.Driver;
import static pl.umk.raytracking.Driver.tracer;
import static pl.umk.raytracking.Driver.world;

/**
 *
 * @author Szymon
 */
public class ThreadRenderer implements Callable{

    int width;
    int y;

    public ThreadRenderer(int width, int y) {
        this.width = width;
        this.y = y;

    }

    
    public Integer call() throws Exception {
        int pom = 0;        
        for (int yy = y; yy < (y + 100) && yy< Driver.world.viewPlane.height; yy++) {

            for (int x = 0; x < world.viewPlane.width; x += 1) {
                try {                
                    tracer.trace(x, yy);
                } catch (IOException ex) {
                    Logger.getLogger(ThreadRenderer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            pom++;
           
        }
        

       return pom;
    }

}
