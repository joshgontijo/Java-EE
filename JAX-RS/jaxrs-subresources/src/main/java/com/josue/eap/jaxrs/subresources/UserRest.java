/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.jaxrs.subresources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

//Do not use @Path here, this class is no longer a root class, and should be registered within JAX-RS Application
public class UserRest {

    @Context
    private UriInfo context;

    @GET
    @Produces("text/plain")
    @Path("users")
    public Response getUsers() {
        return Response.status(Response.Status.OK).entity("*** HERE IS A LIST OF USERS ***").build();
    }

    @GET
    @Produces("text/plain")
    @Path("users/{id}")
    public Response getUserById(@PathParam("id") Integer id) {
        return Response.status(Response.Status.OK).entity("*** HERE IS A USER WITH ID: " + id + " ***").build();
    }

}
