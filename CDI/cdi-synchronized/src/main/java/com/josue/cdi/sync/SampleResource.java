package com.josue.cdi.sync;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Josue on 25/02/2016.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/sync")
@ApplicationScoped
public class SampleResource {

    @Inject
    SyncControl control;

    @GET
    @Produces("text/plain")
    public String getMessage() {
        return "" + control.runSynchronized();
    }

}
