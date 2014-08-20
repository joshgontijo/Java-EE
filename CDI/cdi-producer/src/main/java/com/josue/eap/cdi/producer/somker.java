package com.josue.eap.cdi.producer;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

/**
 * Created by Josue on 8/16/2014.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
@Stateless
public class somker {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World";
    }


}
