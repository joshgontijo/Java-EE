/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.jaxrs2.bean.param;

import java.util.logging.Logger;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("generic")
public class GenericResource {

    private static final Logger LOG = Logger.getLogger(ApplicationConfig.class.getName());

    @GET
    @Produces("text/plain")
    public String getText(@BeanParam ParamWrapper wrapper) {

        LOG.info(wrapper.toString());
        return wrapper.toString();
    }

    @GET
    @Path("map")
    @Produces("text/plain")
    public String getMap(@Context UriInfo uriInfo) {

        String values = "";
        MultivaluedMap<String, String> mpAllQueParams = uriInfo.getQueryParameters();
        for (String key : mpAllQueParams.keySet()) {
            values += key + "=" + mpAllQueParams.get(key) + "\n";
        }
        LOG.info(values);
        return values;
    }

}
