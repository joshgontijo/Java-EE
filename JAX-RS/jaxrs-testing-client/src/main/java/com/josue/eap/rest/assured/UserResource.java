/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.rest.assured;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("user")
@Singleton
public class UserResource {

    Map<String, User> users = new ConcurrentHashMap<>();
    private static final Logger LOG = Logger.getLogger(UserResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(@QueryParam("name") @DefaultValue("none") String name) {
        LOG.log(Level.INFO, "GET: @QueryParam = {0}", name);
        return Response.status(200).entity(users.values()).build();
    }

    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUuid(@PathParam("uuid") String uuid) {
        User foundUser = users.get(uuid);
        if (foundUser == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(foundUser).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {

        user.setUuid(UUID.randomUUID().toString());
        this.users.put(user.getUuid(), user);

        return Response.status(Response.Status.CREATED).entity(user).build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {

        User foundUser = users.get(user.getUuid());
        if (foundUser == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        users.put(foundUser.getUuid(), user);
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @DELETE
    @Path("{uuid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("uuid") String uuid) {

        User foundUser = users.get(uuid);
        if (foundUser == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        users.remove(foundUser.getUuid());
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
