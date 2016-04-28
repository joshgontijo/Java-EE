package com.josue.concurrency;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

/**
 * Created by Josue on 27/04/2016.
 */

@Path("/generic")
@ApplicationScoped
public class SampleResource {

    private static final Logger logger = Logger.getLogger(SampleResource.class.getName());

    @Inject
    ExecutorService service;

    @GET
    @Produces("text/plain")
    public String getMessage() {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info(":: Finished task ::");
            }
        });
        return "Hello World";
    }
}
