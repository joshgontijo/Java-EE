/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.jaxrs.filter.filter;

import com.josue.eap.jaxrs.filter.binding.annot.DynamicBinder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
@DynamicBinder
public class DynamicFilter implements ContainerRequestFilter, ContainerResponseFilter {

    //this should be updatable =)
    private final String[] ALLOWED_URLS = {"/dynamic", "/dynamic/path1"};
    private final String[] ALLOWED_METHODS = {"GET", "DELETE"};

    @Override
    public void filter(ContainerRequestContext requestContext) {

    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        if (matchMethod(requestContext) && matchUrl(requestContext)) {
            LOG.log(Level.INFO, "RESOURCE MATCHED... NOW LOGGING...");
            LOG.log(Level.INFO, "{0} {1}", new Object[]{requestContext.getMethod(), requestContext.getUriInfo().getPath()});
        }

    }

    private boolean matchMethod(ContainerRequestContext requestContext) {
        for (String method : ALLOWED_METHODS) {
            if (requestContext.getMethod().equals(method)) {
                return true;
            }
        }
        return false;
    }

    private boolean matchUrl(ContainerRequestContext requestContext) {
        for (String url : ALLOWED_URLS) {
            if (requestContext.getUriInfo().getPath().equals(url)) {
                return true;
            }
        }
        return false;
    }

    private static final Logger LOG = Logger.getLogger(DynamicFilter.class.getName());

}
