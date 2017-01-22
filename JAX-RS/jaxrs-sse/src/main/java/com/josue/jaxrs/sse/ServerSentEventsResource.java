package com.josue.jaxrs.sse;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Josue on 22/01/2017.
 */
@Path("sse")
@ApplicationScoped
public class ServerSentEventsResource {

    private static final Logger logger = Logger.getLogger(ServerSentEventsResource.class.getName());

    @GET
    public Response broadcastEvent() {
        logger.info("-- NEW REQUEST");

        StreamingOutput streamingOutput = (output) -> {
            try {
                Writer writer = new BufferedWriter(new OutputStreamWriter(output));
                for (int i = 0; i < 100; i++) {
                    String message = new SampleMessage("" + i).toString();

                    logger.info("--- Writing: " + message);

                    writer.write("data: " + message + "\n\n");
                    writer.flush();

                    Thread.sleep(1000);

                }
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Error / Connection closed: ", ex);
            }
        };

        logger.info("Done...");
        return Response
                .ok(streamingOutput)
                .type("text/event-stream;charset=UTF-8")
                .build();
    }

}
