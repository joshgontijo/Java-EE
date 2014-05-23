/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.rest.apikey;

import com.josue.jee.rest.apikey.selector.ApiKey;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("/")
public class GenericResource {

    @Context
    private UriInfo context;

    @GET
    @Path("nonfiltered")
    @Produces("text/plain")
    public String getXml() {
        return "Josue";
    }

    @GET
    @Path("filtered")
    @Produces("text/plain")
    @ApiKey
    public String getString() {
        return "Josue filtered";
    }

}
