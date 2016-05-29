/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jcache.queue;

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
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Josue
 */
@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CacheProducer {

    public static final String CACHE_NAME = "josue-cache";

    CachingProvider cachingProvider;
    Cache<String, Queue<Integer>> cache;

    public static final String QUEUE_KEY = "QUEUE";

    @PostConstruct
    public void init() {
        cachingProvider = Caching.getCachingProvider();
        cache = cachingProvider.getCacheManager().createCache(CACHE_NAME, new MutableConfiguration<String, Queue<Integer>>());
        if (!cache.containsKey(QUEUE_KEY)) {
            cache.put(QUEUE_KEY, new ConcurrentLinkedQueue<Integer>());
        }
    }

    @PreDestroy
    public void destroy() {
        cache.close();
        cachingProvider.close();
    }

    @Produces
    public Cache<String, Queue<Integer>> produceCache() {
        return cache;
    }
}
