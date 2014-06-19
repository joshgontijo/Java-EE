/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.jaxrs.filters;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Josue
 */
@Provider
public class SimpleFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        LOG.info("*** Request");
        LOG.log(Level.INFO, "{0} {1}", new Object[]{requestContext.getMethod(), requestContext.getUriInfo().getPath()});
        LOG.info("*** Response");
        LOG.info(String.valueOf(responseContext.getEntity()));

    }
    private static final Logger LOG = Logger.getLogger(SimpleFilter.class.getName());

}
