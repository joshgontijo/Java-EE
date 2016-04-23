package com.josue.javaee.jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by Josue on 23/04/2016.
 */
@Path("streaming")
public class StreamingResource {

    @GET
    public Response getFileStream() {
        StreamingOutput stream = getStreamingOutput();
        return Response.ok(stream).build();
    }

    @GET
    @Path("as-file")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFileStreamAsFile() {
        StreamingOutput stream = getStreamingOutput();
        return Response.ok(stream).header("Content-Disposition", "attachment; filename=\"sample.txt\"").build();
    }

    @POST
    @Consumes("*/*") // to accept all input types
    public Response upload(InputStream stream) throws IOException {

        String fileDestination = System.getProperty("user.home") + UUID.randomUUID().toString().substring(0,4) + ".txt";

        Files.copy(stream, Paths.get(new File(fileDestination).toURI()));
        return Response.ok().entity("Uploaded to: " + fileDestination).build();
    }


    private StreamingOutput getStreamingOutput() {
        return new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("sample.txt");

                int nextByte;
                while ((nextByte = inputStream.read()) != -1) {
                    outputStream.write(nextByte);
                }
                outputStream.close();
                inputStream.close();
            }
        };
    }


}
