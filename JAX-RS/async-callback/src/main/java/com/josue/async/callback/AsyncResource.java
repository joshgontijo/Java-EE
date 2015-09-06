/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.async.callback;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("async")
public class AsyncResource {

    private static final Logger logger = Logger.getLogger(AsyncResource.class.getName());

    @Resource
    ManagedExecutorService mes;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAsync(@Suspended final AsyncResponse asyncResponse) {

        //REGISTER A NEW CALLBACK
        asyncResponse.register(new CompletionCallback() {
            @Override
            public void onComplete(Throwable throwable) {
                if (throwable == null) {
                    // no throwable - the processing ended successfully
                    // (response already written to the client)
                } else {

                }
            }
        });

        //SUBMIT A NEW RUNNALE
        mes.execute(new Runnable() {

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
        });

        return "OK";
    }
}
