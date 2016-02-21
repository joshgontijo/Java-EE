package com.josue.cdi.rest;


import com.josue.cdi.SampleEvent;
import com.josue.cdi.async.AsyncEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.logging.Logger;

/**
 * Created by Josue on 21/02/2016.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/async")
@ApplicationScoped
public class SampleAsync {

    private static final Logger logger = Logger.getLogger(SampleAsync.class.getName());

    @Inject
    private AsyncEvent<SampleEvent> asyncA;

    @Inject
    private AsyncEvent<String> asyncB;

    @GET
    @Produces("text/plain")
    public String fireEvent() {
        asyncA.fire(new SampleEvent());
        asyncB.fire("Another-Event");

        return "OK";
    }

}
