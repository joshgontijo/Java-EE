/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.async.jaxrs.rest;

import com.josue.jee.async.jaxrs.async.JobExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("/")
public class AsyncResource {

    @Inject
    private JobExecutor executor;
    private static final Logger LOG = Logger.getLogger(AsyncResource.class.getName());

    @GET
    @Path("async")
    @Consumes("text/plain")
    public String putText(@Suspended final AsyncResponse asyncResponse, @QueryParam("exception") final Boolean exception) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                LOG.log(Level.INFO, "STARTED THREAD {0}", Thread.currentThread().getName());

                String resp = "empty response";
                try {
                    LOG.log(Level.INFO, "CANCELING RESPONSE", Thread.currentThread().getName());
                    resp = executor.longTask(exception);
                } catch (Throwable e) {
                    asyncResponse.cancel();
                }

                asyncResponse.resume(resp);
            }

        }).start();
        return null;
    }
}
