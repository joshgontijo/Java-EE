package com.josue.concurrency;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

/**
 * Created by Josue on 27/04/2016.
 */
public class RejectionListener implements RejectedExecutionHandler {

    private static final Logger logger = Logger.getLogger(RejectionListener.class.getName());

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        logger.info("Thread rejected... Runnable: " + r + ", ThreadPoolExecutor: " + executor);
    }
}
