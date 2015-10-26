/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.json.provider;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("sample")
public class SampleResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        return new User("Josue", 123);
    }

    @GET
    @Path("client")
    @Produces(MediaType.APPLICATION_JSON)
    public Object sampleClient() {
        return new RestClient().doIt();
    }

}
