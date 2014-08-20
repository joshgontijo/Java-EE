/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.jaas.basic.security;

import java.security.Principal;
import java.util.logging.Logger;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("/")
@DeclareRoles({"user", "ADMIN"}) // case sensitive
public class SimpleResource {

    // project reference: http://www.thejavageek.com/2013/09/18/configure-jaas-jboss-7-1-mysql/
    //http://blog.amatya.net/2012/09/implementing-security-with-jaas-on.html
    //check for web.xml and JBOSS-JAAS-FRAGMENT
    // A database table should be created to store credentials
    @Inject
    private Principal principal;

    @GET
    @Path("unsecured")
    @Produces("text/plain")
    public String unsecured() {
        return "UNPROTECTED RESOURCE";
    }

    @GET
    @Path("secured")
    @Consumes("text/plain")
    @RolesAllowed("user") //  Either this or <security-role><description>desc</description><role-name>user</role-name> in web.xml
    public String secured(@Context SecurityContext sc) {

//        LOG.info(String.valueOf(sc.getUserPrincipal()));
//        LOG.info(String.valueOf(sc.getAuthenticationScheme()));
//        LOG.info(String.valueOf(sc.isUserInRole("user")));
        return "PROTECTED RESOURCE, PRINCIPAL: " + sc.getUserPrincipal();
    }
    private static final Logger LOG = Logger.getLogger(SimpleResource.class.getName());
}
