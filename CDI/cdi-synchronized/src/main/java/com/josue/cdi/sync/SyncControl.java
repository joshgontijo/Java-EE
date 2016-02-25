package com.josue.cdi.sync;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Created by Josue on 25/02/2016.
 */
@ApplicationScoped
public class SyncControl {

    private static final Object LOCK = new Object();
    private static AtomicInteger counter = new AtomicInteger();

    private static final Logger logger = Logger.getLogger(SyncControl.class.getName());

    public int runSynchronized() {
        logger.info(":: WAITING FOR LOCK ::");
        synchronized (LOCK) {
            int accessId = counter.incrementAndGet();
            logger.info(":: LOCK ACQUIRED... ACCESS ID: " + accessId + " ::");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.severe(e.getMessage());
            }
            logger.info(":: RELEASING LOCK... ACCESS ID: " + accessId + " ::");
            return accessId;
        }
    }
}
