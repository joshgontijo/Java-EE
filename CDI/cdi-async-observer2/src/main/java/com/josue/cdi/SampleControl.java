package com.josue.cdi;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

/**
 * Created by Josue on 21/02/2016.
 */
@ApplicationScoped
public class SampleControl {

    private static final Logger logger = Logger.getLogger(SampleControl.class.getName());

    public void listener(@Observes SampleEvent event) throws InterruptedException {
        logger.info("Received event: " + event);
        Thread.sleep(3000);
        logger.info("Finished");
    }

    public void listener(@Observes String event) throws InterruptedException {
        logger.info("Received event (String): " + event);
        Thread.sleep(3000);
        logger.info("Finished");
    }
}
