package com.josue.cdi.rest;

import async.SampleControl;

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
    SampleControl control;

    @GET
    @Produces("text/plain")
    public String fireEvent() {
        control.asyncMethod();
        return "OK";
    }

}
