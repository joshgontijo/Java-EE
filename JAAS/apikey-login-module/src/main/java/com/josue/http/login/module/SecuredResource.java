/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.http.login.module;

import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 *
 * REST Web Service
 *
 * @author Josue
 */
@Path("secured")
@ApplicationScoped
public class SecuredResource {

    @Context
    SecurityContext sc;

    @Inject
    SimpleService service;

    @Context
    private UriInfo context;

    @GET
    public String nonSecured() {

        LOG.log(Level.INFO, "isUser in user role: {0}", String.valueOf(sc.isUserInRole("user1")));
        LOG.log(Level.INFO, "isUser in admin role: {0}", String.valueOf(sc.isUserInRole("admin1")));

        Principal p = sc.getUserPrincipal();
        LOG.log(Level.INFO, "PRINCIPAL: {0}", p == null ? null : p.getName());

        return "PROTECTED RESOURCE, PRINCIPAL: " + service.getInjectedPrincipal();
    }
    private static final Logger LOG = Logger.getLogger(SecuredResource.class.getName());

}
