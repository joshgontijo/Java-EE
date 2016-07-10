package com.josue.jaxrs.validation;
import com.josue.jaxrs.validation.validator.ValidMessage;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Josue on 10/07/2016.
 */
@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
    
    @POST
    public String save(@ValidMessage(message = "Invalid message") SampleMessage sampleMessage) {
        return "received +" + sampleMessage;
    }

    @PUT
    public String update(SampleMessage sampleMessage) {
        return "received +" + sampleMessage;
    }
}
