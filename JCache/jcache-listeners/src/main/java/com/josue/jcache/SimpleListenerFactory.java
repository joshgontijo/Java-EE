package com.josue.jcache;

import javax.cache.configuration.Factory;

/**
 *
 * @author Josue Gontijo <josue.gontijo@maersk.com>
 */
public class SimpleListenerFactory implements Factory<SimpleCacheListener<String, Object>>{

    @Override
    public SimpleCacheListener<String, Object> create() {
        return new SimpleCacheListener<>();
    }
    
}
