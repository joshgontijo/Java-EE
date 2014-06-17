/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.cdi.app.config.rest;

import com.josue.eap.cdi.app.config.rest.cfg.Config;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("generic")
@Stateless
public class GenericResource {

    private static final Logger LOG = Logger.getLogger(GenericResource.class.getName());

    @Inject
    @Config(key = "myKey")
    private String sampleString;

    @GET
    @Produces("text/plain")
    public String getText() {
        LOG.info(sampleString);
        return sampleString;
    }

}
