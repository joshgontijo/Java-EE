/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.managed.executor.service;

import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("test")
@RequestScoped
public class SampleResource {

    private static final Logger logger = Logger.getLogger(SampleResource.class.getName());

    //Threads pool are configured within Application server
    //ref: http://blog-emmartins.rhcloud.com/2013/11/wildfly-8-beta-1-and-jsr-236/
    @Resource
    private ManagedExecutorService mes;

    @GET
    @Produces("text/plain")
    public String execute() {
        mes.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    logger.info(":: MANAGED THREAD STARTED ::");
                    Thread.sleep(3000);
                    logger.info(":: MANAGED THREAD FINISHED  ::");
                } catch (InterruptedException ex) {
                    logger.severe(ex.getMessage());
                }
            }
        });
        return "Started Thread";
    }

    @GET
    @Path("async")
    @Produces("text/plain")
    public String executeAsync(@Suspended final AsyncResponse asyncResponse) {
        mes.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    logger.info(":: MANAGED THREAD STARTED ::");
                    Thread.sleep(3000);
                    logger.info(":: MANAGED THREAD FINISHED  ::");

                    asyncResponse.resume(":: FROM THREAD ::");

                } catch (InterruptedException ex) {
                    logger.severe(ex.getMessage());
                }
            }
        });
        return "Started Thread";
    }

}
