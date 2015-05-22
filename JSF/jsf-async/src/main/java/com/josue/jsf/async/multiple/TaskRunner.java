/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.async.multiple;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jgontijo
 */
public class TaskRunner implements Runnable {

    private static final Logger logger = Logger.getLogger(TaskRunner.class.getName());

    private Task task;

    Random randon = new Random();

    public TaskRunner(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "---- STARTED TASK: {0}", task.getId());
        try {
            int runningTime = randon.nextInt(10 - 3) + 3;

            Thread.sleep(runningTime * 1000);

            task.setResult("DONE TASK " + task.getId());
            task.setRunning(false);

        } catch (InterruptedException ex) {
            Logger.getLogger(MultipleAsyncController.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.log(Level.INFO, "---- FINISHED RUNNABLE: {0}", task.getId());
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
