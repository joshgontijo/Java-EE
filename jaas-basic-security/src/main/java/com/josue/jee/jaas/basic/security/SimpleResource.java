/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josue.jee.jaas.basic.security;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("/")
public class SimpleResource {

    
    // project reference: http://www.thejavageek.com/2013/09/18/configure-jaas-jboss-7-1-mysql/
    //check for web.xml and JBOSS-JAAS-FRAGMENT
    // A tatabase table should be created to store credentials

    @GET
    @Path("unsecured")
    @Produces("text/plain")
    public String unsecured() {
        return "UNPROTECTED RESOURCE";
    }
    
    
    @GET
    @Path("secured")
    @Consumes("text/plain")
    public String secured() {
        return "PROTECTED RESOURCE";
    }
}
