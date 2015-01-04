/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jboss.custom.jackson.provider;

import java.util.Date;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("user")
public class UserResource {

    @Context
    private UriInfo context;

    @GET
    @Produces("application/json")
    public Response getJson() {
        return Response.status(Response.Status.OK).entity(new User("josue", 25, new Date())).build();
    }

    @POST
    @Consumes("application/json")
    public void putJson(User user) {
        LOG.info(user.toString());
    }
    private static final Logger LOG = Logger.getLogger(UserResource.class.getName());
}
