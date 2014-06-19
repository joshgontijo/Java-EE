/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.jaxrs.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("generic")
public class GenericResource {

    @GET
    @Produces("text/plain")
    public String getText() {
        return "my response body";
    }

    @PUT
    @Produces("text/plain")
    @Consumes("text/plain")
    public String putText(String text) {
        return "Server response: " + text;
    }

}
