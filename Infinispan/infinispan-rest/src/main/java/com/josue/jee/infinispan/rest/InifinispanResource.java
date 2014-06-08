/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.infinispan.rest;

import java.util.logging.Logger;
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
@Path("infinispan")
@Stateless
public class InifinispanResource {

    private static final Logger LOG = Logger.getLogger(InifinispanResource.class.getName());

    @Resource(lookup = "java:jboss/infinispan/myCache")
    private CacheContainer container;
//    @Inject
//    transient AdvancedCache<Object, Object> cache;
    private Cache<String, String> cache;

    @PostConstruct
    public void start() {
        this.cache = this.container.getCache();
    }

    @GET
    @Produces("text/plain")
    public String getText(@QueryParam("name") String name) {

//        Object cahedName = cache.get("name");
//        LOG.info(cahedName.toString());
//        return cahedName.toString();
        return "OK";
    }

}
