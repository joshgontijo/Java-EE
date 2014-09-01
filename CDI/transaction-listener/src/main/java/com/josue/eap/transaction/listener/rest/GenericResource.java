/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josue.eap.transaction.listener.rest;

import com.josue.eap.transaction.listener.core.SampleEJB;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
@Stateless
public class GenericResource {
    
    @EJB
    private SampleEJB sampleEJB;

    @Context
    private UriInfo context;

    
    
    @GET
    @Produces("text/plain")
    public String getText() {
        sampleEJB.doSomething();
        return "OK";
    }

   
}
