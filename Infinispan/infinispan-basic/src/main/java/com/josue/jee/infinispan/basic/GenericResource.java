/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.infinispan.basic;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.infinispan.manager.EmbeddedCacheManager;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("generic")
@RequestScoped
public class GenericResource {

    @SuppressWarnings("unused")
    @Resource(lookup = "java:jboss/infinispan/myCache")
    private static EmbeddedCacheManager container;

    @GET
    @Produces("application/xml")
    public String getXml() {
        return container.toString();
    }

}
