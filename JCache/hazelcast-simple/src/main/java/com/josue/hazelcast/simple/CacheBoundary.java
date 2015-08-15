/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.hazelcast.simple;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.cache.Cache;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
@ViewScoped
public class CacheBoundary implements Serializable {

    private static final Logger logger = Logger.getLogger(CacheBoundary.class.getName());

    @Inject
    transient Cache<String, Object> cache;

    private String key;
    private String val;

    public void addTocache() {
        logger.log(Level.INFO, "Adding to Cache: {0}={1}", new Object[]{key, val});
        cache.put(key, val);
    }

    public void findByKey() {
        Object get = cache.get(key);
        if (get != null) {
            logger.log(Level.INFO, "Found from Cache: {0}={1}", new Object[]{key, val});
            val = get.toString();
        } else {
            logger.log(Level.INFO, "No value found for key {0}", key);
        }
    }

    public Map<String, Object> getCache() {
        Map<String, Object> values = new HashMap<>();
        Iterator<Cache.Entry<String, Object>> iterator = cache.iterator();
        while (iterator.hasNext()) {
            Cache.Entry<String, Object> next = iterator.next();
            values.put(next.getKey(), next.getValue());
        }
        return values;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

}
