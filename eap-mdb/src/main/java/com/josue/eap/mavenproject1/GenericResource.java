/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josue.eap.mavenproject1;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

    @EJB
    private MDBProducer producer;
    
    public GenericResource() {
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getString() throws JMSException {
        
        producer.sendJMSMessageToTest("*** MESSAGE ***");
        
        return "OK";
    }
}
