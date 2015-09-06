/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.managed.task;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josue
 */
public class Task implements Runnable {

    private static final Logger logger = Logger.getLogger(GenericResource.class.getName());

    @Override
    public void run() {
        try {
            logger.log(Level.INFO, ":: MANAGED THREAD STARTED ::");
            Thread.sleep(3000);
            logger.log(Level.INFO, ":: MANAGED THREAD FINISHED ::");
        } catch (InterruptedException ex) {
            logger.severe(ex.getMessage());
        }
    }

}
