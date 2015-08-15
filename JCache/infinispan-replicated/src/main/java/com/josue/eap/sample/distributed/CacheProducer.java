/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.sample.distributed;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import org.infinispan.manager.CacheContainer;

/**
 *
 * @author Josue
 */
@Singleton
public class CacheProducer {

    //HA
    @Produces
    @Resource(lookup = "java:jboss/infinispan/container/server")
    private static CacheContainer cacheContainer;

    //for standalone
//     @Produces
//    @Resource(lookup = "java:jboss/infinispan/container/myCache")
//    private static CacheContainer cacheContainer;
}
