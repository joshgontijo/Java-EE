/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.javaee.cdi.rest;

import com.josue.jee.javaee.cdi.IMessage;
import com.josue.jee.javaee.cdi.qualifier.Message;
import com.josue.jee.javaee.cdi.qualifier.MessageType;
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
@Path("/")
public class MessageResource {

    @Context
    private UriInfo context;

    @Inject
    @Message(MessageType.EMAIL)
    private IMessage email;

    @Inject
    @Message(MessageType.SMS)
    private IMessage sms;

    @GET
    @Path("email")
    @Produces("text/plain")
    public String getEmail() {
        return email.message();
    }

    @GET
    @Path("sms")
    @Produces("text/plain")
    public String getSMS() {
        return sms.message();
    }

}
