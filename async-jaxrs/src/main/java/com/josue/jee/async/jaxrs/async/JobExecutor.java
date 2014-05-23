/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.async.jaxrs.async;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josue
 */
public class JobExecutor {

    private static final Logger LOG = Logger.getLogger(JobExecutor.class.getName());

    public String longTask(Boolean exception) {
        try {
            LOG.log(Level.INFO, "JOB STARTED {0} ... EXCEPTION VALUE={1}", new Object[]{Thread.currentThread().getName(), String.valueOf(exception)});
            Thread.sleep(10000);

            if (exception) {
                LOG.log(Level.SEVERE, "EXCEPTION THROWN", Thread.currentThread().getName());
                throw new RuntimeException("ERROR ON ASYNC PROCESS SOMETHING");
            }

            LOG.log(Level.INFO, "JOB FINISHED {0}", Thread.currentThread().getName());
            return "FINISHED";

        } catch (InterruptedException ex) {

        }
        return "ERROR";
    }

}
