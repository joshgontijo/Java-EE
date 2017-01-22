package com.josue.sse.jersey;

import org.glassfish.jersey.media.sse.EventInput;
import org.glassfish.jersey.media.sse.InboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Created by Josue on 22/01/2017.
 */
public class SseClient {

    public static void main(String[] args) {

        Client client = ClientBuilder.newBuilder()
                .register(SseFeature.class)
                .build();

        WebTarget target = client.target("http://localhost:8080/jaxrs-sse/api/sse");

        EventInput eventInput = target.request().get(EventInput.class);
        while (!eventInput.isClosed()) {
            final InboundEvent inboundEvent = eventInput.read();
            if (inboundEvent == null) {
                // connection has been closed
                break;
            }
            System.out.println(inboundEvent.getName() + "; "
                    + inboundEvent.readData(String.class));
        }

    }
}
