/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.rest.assured;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

public class UserRestClient {

    private static final String BASE_URI = "http://localhost:8080/jaxrs-testing-client-1.0-SNAPSHOT/webresources";
    private static final String USER_RESOURCE = "user";

    Client client;
    WebResource webResource;

    public UserRestClient() {

        ClientConfig clientConfig = new DefaultClientConfig();
        //Register the body writer
        clientConfig.getClasses().add(JacksonJsonProvider.class);
        clientConfig.getFeatures().put(
                JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        client = Client.create(clientConfig);
        webResource = client.resource(BASE_URI);

    }

    public ClientResponse createUser(Object requestEntity) {
        return webResource.path(USER_RESOURCE)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, requestEntity);
    }

    public ClientResponse getByUuid(String uuid) {
        return webResource.path(USER_RESOURCE).path(uuid)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    }

    public ClientResponse getAll() {
        return webResource.path(USER_RESOURCE)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    }

    public ClientResponse update(Object requestEntity, String uuid) {
        return webResource.path(USER_RESOURCE).path(uuid)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, requestEntity);
    }

    public ClientResponse delete(String uuid) {
        return webResource.path(USER_RESOURCE).path(uuid)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .delete(ClientResponse.class);
    }
}
