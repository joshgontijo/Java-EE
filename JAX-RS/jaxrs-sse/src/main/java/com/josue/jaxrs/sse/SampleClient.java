package com.josue.jaxrs.sse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Josue on 22/01/2017.
 */
public class SampleClient {

    public static void main(String[] args) throws IOException {
        Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target("http://localhost:8080/jaxrs-sse/api/sse");

        InputStream is = target.request().get(InputStream.class);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String message;
        while ((message = br.readLine()) != null) {
            if (!message.isEmpty()) {
                System.out.println("RESPONSE: " + message);
            }
        }

        is.close();
        System.out.println("Done....");
    }
}
