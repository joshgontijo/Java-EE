package com.josue.batch.flow;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Josue on 05/06/2015.
 */
@Path("/helloworld")
public class Sample {

    @GET
    @Produces("text/plain")
    public String getClichedMessage() {
        return "Hello World";
    }

}
