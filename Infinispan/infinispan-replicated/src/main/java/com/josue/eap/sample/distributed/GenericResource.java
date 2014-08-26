/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.sample.distributed;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.infinispan.Cache;
import org.infinispan.manager.CacheContainer;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("generic")
@Stateless
public class GenericResource {

    @SuppressWarnings("unused")
    //cache-container name="server" --- cluster ready
    @Resource(lookup = "java:jboss/infinispan/container/server")
    private CacheContainer container;

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
     <cache-container name="server" default-cache="default" module="org.wildfly.clustering.server" aliases="singleton cluster">
          <transport lock-timeout="60000"/>
          <replicated-cache name="default" batching="true" mode="SYNC">
             <locking isolation="REPEATABLE_READ"/>
          </replicated-cache>
     </cache-container>
     */

}
