/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.async.multiple;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
@ViewScoped
public class MultipleAsyncController implements Serializable {

    private static final Logger logger = Logger.getLogger(MultipleAsyncController.class.getName());

    @Resource(lookup = "java:comp/DefaultManagedExecutorService")
    private ManagedExecutorService executor;

    private int taskNumber;
    private List<Task> tasks = new ArrayList<>();
    
    private boolean allTasksFinished = true;

    public void executeAsync() throws InterruptedException {
        //clean
        tasks = new ArrayList<>();
        allTasksFinished = false;

        logger.log(Level.INFO, "**** TASK POLL SIZE: {0}", taskNumber);
        for (int i = 1; i <= taskNumber; i++) {
            Task task = new Task(i);
            tasks.add(task);

            task.setRunning(true);
            executor.submit(new TaskRunner(task));
        }
    }

    public void ping() {
        int running = 0;
        for(Task task : tasks){
            if(task.isRunning()){
                running += 1;
            }
        }
        logger.log(Level.INFO, "TASKS RUNNING: {0}", running);
        
        if(running == 0){
            logger.info("ALL TASKS FINISHED... STOPPING THE POLL");
            allTasksFinished = true;
        }
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isAllTasksFinished() {
        return allTasksFinished;
    }

    public void setAllTasksFinished(boolean allTasksFinished) {
        this.allTasksFinished = allTasksFinished;
    }

    
}
