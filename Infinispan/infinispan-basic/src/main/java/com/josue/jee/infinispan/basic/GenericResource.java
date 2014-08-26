/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.infinispan.basic;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("generic")
@Stateless
public class GenericResource {

    @SuppressWarnings("unused")
    @Resource(lookup = "java:jboss/infinispan/myCache")
    private EmbeddedCacheManager container;

    private Cache<String, String> cache;
    
    @PostConstruct
    public void start() {
        this.cache = this.container.getCache();
    }

    @GET
    @Produces("text/plain")
    public String getCacheValue(@QueryParam("key")String key) {
        return cache.get(key);
    }
    
    @GET
    @Path("insert")
    @Produces("text/plain")
    public String getCacheValue(@QueryParam("key")String key, @QueryParam("value")String value) {
        cache.put(key, value);
        return "OK";
    }

    /*
     <subsystem xmlns="urn:jboss:domain:infinispan:2.0">
     <cache-container name="myCache" default-cache="cachedb" jndi-name="java:jboss/infinispan/myCache">
     <local-cache name="cachedb"/>
     </cache-container>
     .....
     */
}
