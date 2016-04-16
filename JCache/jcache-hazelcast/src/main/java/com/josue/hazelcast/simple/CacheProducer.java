/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.hazelcast.simple;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.cache.Cache;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Josue
 */
@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CacheProducer {

    public static final String CACHE_NAME = "josue-cache";

    CachingProvider cachingProvider;
    Cache<String, Object> cache;

    @PostConstruct
    public void init() {
        cachingProvider = Caching.getCachingProvider();
        cache = cachingProvider.getCacheManager().createCache(CACHE_NAME, new MutableConfiguration<String, Object>());
    }

    @PreDestroy
    public void destroy() {
        cache.close();
        cachingProvider.close();
    }

    @Produces
    public Cache<String, Object> produceCache() {
        return cache;
    }
}
