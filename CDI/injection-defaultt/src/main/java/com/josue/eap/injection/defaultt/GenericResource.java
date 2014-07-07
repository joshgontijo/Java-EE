package com.josue.eap.injection.defaultt;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    @Inject
    private SimpleClass sc;

    @GET
    @Produces("text/plain")
    public String getText() {
        return sc.doIt();
    }

}
