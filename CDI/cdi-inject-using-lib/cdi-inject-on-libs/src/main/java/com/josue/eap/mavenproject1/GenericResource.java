/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.mavenproject1;

import com.josue.eap.mavenproject3.InjectMe;
import com.josue.eap.mavenproject3.NewClass;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
@Path("generic")
@ApplicationScoped
public class GenericResource {

    @Context
    private UriInfo context;

    @Inject
    // @Named("newClass")
    NewClass newClass;

    @GET
    @Produces("text/plain")
    public String getText() {
        return newClass.justReturn();
    }

    //****** THE PRODUCER METHOD ********
    //here we provide the object that will be inject within the LIB project
    @javax.enterprise.inject.Produces
    @InjectMe
    public String provider() {
        return "josue";
    }

}
