/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.lottery.eap.jerseyviewable;

import com.sun.jersey.api.view.Viewable;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("generic")
@Stateless
public class GenericResource {

    @Context
    private UriInfo context;
    

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    //Standard REST response
    @GET
    @Produces("text/plain")
    public String getText() {
        return "sadasdasd";
    }

    //Page handling
    @GET
    @Path("mypage")
    public Viewable getJsp() {
        return new Viewable("/mypage");

    }
}
