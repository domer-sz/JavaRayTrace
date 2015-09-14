
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

    int xx;

    public ThreadRenderer(int x) {
       
        this.xx = x;

    }

    
    @Override
    public Integer call() throws Exception {
        long start = System.nanoTime();
        int pom = 0; 
        
        for (int x = xx; x < (xx + Driver.renderThreadNumber) && x< Driver.world.viewPlane.width; x++) {
           
            for (int y = 0; y < world.viewPlane.height; y += 1) {
                try {                
                    tracer.trace(x, y);
                } catch (IOException ex) {
                    Logger.getLogger(ThreadRenderer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            pom++;
           
        }
        
        long end = System.nanoTime();
        System.out.println("wykonanie wątku trwało: " + (end - start) / 1000000000.0F);
       return pom;
    }

}
