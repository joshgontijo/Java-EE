/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josue.jsf.novapontocom;

import java.util.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author cit
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of com.josue.jsf.novapontocom.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        
        return Response.status(Response.Status.OK).entity(new App(10, null, new Date())).build();
        
    }

}
