/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.jaas.custom.login.logout;

import java.io.IOException;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("account")
public class LoginResource {

    @Context
    private HttpServletRequest request;
    @Context
    private HttpServletResponse response;

    @Context
    private UriInfo uriInfo;
    private static final Logger LOG = Logger.getLogger(LoginResource.class.getName());

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getUserInfo(@Context SecurityContext context) throws LoginException, IOException {
        return "User: " + context.getUserPrincipal();
    }

    @GET
    @Path("logout")
    @Consumes(MediaType.APPLICATION_JSON)
    public void logout() throws LoginException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/index.html");
    }

//    @GET
//    @Path("forward")
//    public Response forward() {
//        LOG.log(Level.INFO, "FORWADING TO..{0}", uriInfo.getBaseUri().toString());
//        LOG.info(uriInfo.getAbsolutePath().toString());
//        LOG.info(uriInfo.getBaseUri().toString());
//
//        return Response.temporaryRedirect(uriInfo.getBaseUri().resolve("secured/dashboard.html")).build();
//
////        return Response.ok().build();
//    }
}
