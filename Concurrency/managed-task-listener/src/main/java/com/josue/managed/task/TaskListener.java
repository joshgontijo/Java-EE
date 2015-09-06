/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.managed.task;

import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedTaskListener;

/**
 *
 * @author Josue
 */
public class TaskListener implements ManagedTaskListener {

    private static final Logger logger = Logger.getLogger(TaskListener.class.getName());

    @Override
    public void taskSubmitted(Future<?> future, ManagedExecutorService executor, Object task) {
        logger.info(":: TASK SUBMITTED ::");
    }

    @Override
    public void taskAborted(Future<?> future, ManagedExecutorService executor, Object task, Throwable exception) {
        logger.info(":: TASK ABORTED ::");
    }

    @Override
    public void taskDone(Future<?> future, ManagedExecutorService executor, Object task, Throwable exception) {
        logger.info(":: TASK DONE ::");
    }

    @Override
    public void taskStarting(Future<?> future, ManagedExecutorService executor, Object task) {
        logger.info(":: TASK STARTING ::");
    }

}
