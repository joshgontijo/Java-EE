package async;


import com.josue.cdi.async.Async;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

/**
 * Created by Josue on 21/02/2016.
 */
@ApplicationScoped
public class SampleControl {

    private static final Logger logger = Logger.getLogger(SampleControl.class.getName());

    @Async
    public void asyncMethod()  {
        logger.info(":: STARTING ASYNC TASK ::");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(":: FINISHED ASYNC TASK ::");
    }
}
