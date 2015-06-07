package com.josue.eap.sample.distributed;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.infinispan.Cache;
import org.infinispan.manager.CacheContainer;

/**
 * Created by Josue on 05/06/2015.
 */
@Named
@RequestScoped
public class CacheController implements Serializable {
    /*
     <cache-container name="server" default-cache="default" module="org.wildfly.clustering.server" aliases="singleton cluster">
     <transport lock-timeout="60000"/>
     <replicated-cache name="default" batching="true" mode="SYNC">
     <locking isolation="REPEATABLE_READ"/>
     </replicated-cache>
     </cache-container>
     */

    private static final Logger logger = Logger.getLogger(CacheController.class.getName());

    //cache-container name="server" --- cluster ready
//    @Resource(lookup = "java:jboss/infinispan/container/myCache")
    @Inject
    private CacheContainer container;

    private Cache<String, String> cache;

    private String key;
    private String value;

    @PostConstruct
    public void init() {
        this.cache = this.container.getCache();
    }

    public void addToCache() {
        if (key != null) {
            cache.put(key, value);
            logger.log(Level.INFO, "ADDED TO CACHE KEY={0} VALUE={1}", new Object[]{key, value});
        }
        key = null;
        value = null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Cache<String, String> getCache() {
        return cache;
    }
}
