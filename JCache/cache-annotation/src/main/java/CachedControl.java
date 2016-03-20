import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;
import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

/**
 * Created by Josue on 20/03/2016.
 */
@ApplicationScoped
@CacheDefaults(cacheName = "sample-cache")
public class CachedControl {

    private static final Logger logger = Logger.getLogger(CachedControl.class.getName());

    @CacheResult
    public String simpleCache(String key) {
        logger.info(":: CACHE MISS ::");
        return key + " - > OK";
    }

    @CachePut
    public void addToCache(@CacheKey String key, @CacheValue String value) {
        logger.info(":: @CachePut  " + key + " -> " + value);
    }

    @CacheRemove
    public void delete(String key) {
        logger.info(":: @CacheRemove " + key);
    }


    @CacheRemoveAll
    public void deleteAll() {
        logger.info(":: @CacheRemoveAll ::");
    }
}
