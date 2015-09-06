/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.scheduled.service;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ContextService;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("test")
public class GenericResource {

    private static final Logger logger = Logger.getLogger(GenericResource.class.getName());

    @Resource
    ManagedScheduledExecutorService mses;

    @Resource
    ContextService cs;

    @GET
    @Produces("text/plain")
    public String getText() {
        mses.schedule(new Runnable() {

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
        }, 3000, TimeUnit.SECONDS);

        return "SCHEDULED";
    }
}
