/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.jaas.custom.login.logout;

import java.io.IOException;
import java.util.logging.Logger;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

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
    // A tatabase table should be created to store credentials
    private static final Logger LOG = Logger.getLogger(SimpleResource.class.getName());
    @Context
    private HttpServletResponse response;

//    @Context
//    private ResourceInfo resourceInfo;
    @Context
    private UriInfo uriInfo;

    @Context
    private HttpServletRequest request;

    @GET
    @Path("unsecured")
    @Produces("text/plain")
    public String unsecured() {
        return "UNPROTECTED RESOURCE";
    }

    @GET
    @Path("secured")
    @Produces("text/plain")
    @RolesAllowed("user") //  Either this or <security-role><description>desc</description><role-name>user</role-name> in web.xml
    public String secured(@Context SecurityContext sc) throws IOException {

//        Response.temporaryRedirect(uriInfo.getBaseUri().resolve("/secured/dashboard.html"));
        return "PROTECTED RESOURCE, PRINCIPAL: " + sc.getUserPrincipal();
    }
}
