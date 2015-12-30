package com.josue.jcache;

import java.util.Iterator;
import java.util.logging.Logger;
import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryExpiredListener;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.event.CacheEntryRemovedListener;
import javax.cache.event.CacheEntryUpdatedListener;

/**
 *
 * @author Josue Gontijo <josue.gontijo@maersk.com>
 */
public class SimpleCacheListener <String, Object>
        implements CacheEntryCreatedListener<String, Object>,
                   CacheEntryUpdatedListener<String, Object>,
                   CacheEntryRemovedListener<String, Object>,
                   CacheEntryExpiredListener<String, Object> {

    private static final Logger logger = Logger.getLogger(SimpleCacheListener.class.getName());

    
    
    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends String, ? extends Object>> itrbl) throws CacheEntryListenerException {
        printEvents(itrbl);
    }

    @Override
    public void onUpdated(Iterable<CacheEntryEvent<? extends String, ? extends Object>> itrbl) throws CacheEntryListenerException {
        printEvents(itrbl);
    }

    @Override
    public void onRemoved(Iterable<CacheEntryEvent<? extends String, ? extends Object>> itrbl) throws CacheEntryListenerException {
        printEvents(itrbl);
    }

    @Override
    public void onExpired(Iterable<CacheEntryEvent<? extends String, ? extends Object>> itrbl) throws CacheEntryListenerException {
        printEvents(itrbl);
    }
    
    
    private void printEvents(Iterable<CacheEntryEvent<? extends String, ? extends Object>> cacheEntryEvents) {
        Iterator<CacheEntryEvent<? extends String, ? extends Object>> iterator = cacheEntryEvents.iterator();
        while (iterator.hasNext()) {
            CacheEntryEvent<? extends String, ? extends Object> event = iterator.next();
           logger.info(event.getEventType().name());
        }
    }
}
