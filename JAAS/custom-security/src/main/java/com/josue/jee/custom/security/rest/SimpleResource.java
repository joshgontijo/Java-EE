/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.custom.security.rest;

import java.security.Principal;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("/")
public class SimpleResource {

    @Inject
    private Principal principal;

    @GET
    @Path("secured")
    @Consumes("text/plain")
    @RolesAllowed("josue")
    public String secured(@Context SecurityContext sc) {

        String name = principal == null ? "NONE" : principal.getName();
        LOG.info(String.valueOf(sc.isUserInRole("josue")));
        return "PROTECTED RESOURCE, PRINCIPAL: " + name;// + customPrincipal.getLogin() + " - " + customPrincipal.getRole();
    }
    private static final Logger LOG = Logger.getLogger(SimpleResource.class.getName());

}
