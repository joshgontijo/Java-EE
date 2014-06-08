/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.infinispan.rest;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.infinispan.Cache;
import org.infinispan.manager.CacheContainer;

/**
 *
 * @author Josue
 */
@Provider
@ApplicationScoped
public class ThrottlingFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(ThrottlingFilter.class.getName());

    @Resource(lookup = "java:jboss/infinispan/myCache")
    private CacheContainer container;
//    @Inject
//    transient AdvancedCache<Object, Object> cache;
    private Cache<String, String> cache;

    @PostConstruct
    public void start() {
        this.cache = this.container.getCache();
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {
        LOG.info("Filtering");
        long currentMils = System.currentTimeMillis();
        LOG.info(String.valueOf(currentMils));

        String lastMils = cache.get("time");

        if (lastMils == null) {
            cache.put("time", String.valueOf(currentMils));
        } else {
            long actualMils = System.currentTimeMillis();
            long last = Long.valueOf(lastMils);
            LOG.info(String.valueOf(actualMils - last));
            if (actualMils - last < 10000) {
                requestContext.abortWith(Response.status(429).entity((actualMils - last) / 1000).build());
            } else {
                cache.put("time", String.valueOf(currentMils));
            }
        }

    }

}
