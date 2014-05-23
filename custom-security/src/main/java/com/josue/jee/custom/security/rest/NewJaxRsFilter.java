/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.custom.security.rest;

import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

/**
 *
 * @author Josue
 */
//@PreMatching
@Provider
@Priority(Priorities.AUTHENTICATION)
//@PreMatching
public class NewJaxRsFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(NewJaxRsFilter.class.getName());

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {

        String apiKey = requestContext.getHeaderString("ApiKey");
        if (apiKey != null) {
            //apikey based request
        } else {

            String authType = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION).split(" ")[0];
            String credential = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION).split(" ")[1];

            if (authType == null || credential == null) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            }
            final String decoded = StringUtils.newStringUtf8(Base64.decodeBase64(credential));

//            requestContext.setSecurityContext(new SecurityContextImpl(new UserPasswordPrincipal("josue2", "admin")));
        }
    }

}
