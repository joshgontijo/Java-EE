/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.cdi.envstages2.param;

import com.josue.cdi.envstages2.param.cdi.Environment;
import com.josue.cdi.envstages2.param.service.Service;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("generic")
@RequestScoped
public class GenericResource {

    @Inject
    private Service service;

    @GET
    @Produces("text/plain")
    public String getXml() {
        return service.doIt();
    }

    @GET
    @Path("update-env")
    @Produces("text/plain")
    public String getXml(@QueryParam("stage") String stage) {
        System.getProperties().put(Environment.ENV_KEY, stage);
        return "ENVIRONMENT UPDATED TO: " + stage;
    }

}
