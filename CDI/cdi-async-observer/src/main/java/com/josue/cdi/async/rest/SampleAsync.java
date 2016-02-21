package com.josue.cdi.async.rest;

import com.josue.cdi.async.SampleEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
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
    Event<SampleEvent> event;

    @GET
    @Produces("text/plain")
    public String fireEvent() {
        logger.info("Firing event");

        event.fire(new SampleEvent());

        logger.info("Event fired, returning response");
        return "OK";
    }

    @GET
    @Produces("text/plain")
    @Path("multiple")
    public String fireEvents() {
        logger.info("Firing event");

        event.fire(new SampleEvent());
        event.fire(new SampleEvent());
        event.fire(new SampleEvent());

        logger.info("Event fired, returning response");
        return "OK";
    }
}
