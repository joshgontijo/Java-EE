/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.jaxrs.filter.service;

import com.josue.eap.jaxrs.filter.binding.annot.DynamicBinder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("dynamic")
@DynamicBinder
public class DynamicLoggingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String root() {
        return "ROOT";
    }

    @GET
    @Path("path1")
    @Produces(MediaType.TEXT_PLAIN)
    public String path1() {
        return "PATH 1";
    }

    @GET
    @Path("path2")
    @Produces(MediaType.TEXT_PLAIN)
    public String path2() {
        return "PATH 2";
    }
}
