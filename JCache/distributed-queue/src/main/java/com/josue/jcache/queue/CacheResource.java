package com.josue.jcache.queue;

import javax.cache.Cache;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Queue;

/**
 * Created by Josue on 26/05/2016.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/cache")
@ApplicationScoped
public class CacheResource implements Serializable {

    @Inject
    private Cache<String, Queue<Integer>> cache;

    @GET
    @Produces("text/plain")
    public Response getMessage() {
        Queue<Integer> queue = cache.get(CacheProducer.QUEUE_KEY);
        Integer total = 0;
        Integer i;
        while ((i = queue.poll()) != null) {
            total += i;
        }
        return Response.ok(total).build();
    }

    @POST
    @Produces("text/plain")
    @Consumes("text/plain")
    public Response increment(final Integer value) {
        cache.invoke(CacheProducer.QUEUE_KEY, new Incrementor(value));
        return Response.ok(cache.get(CacheProducer.QUEUE_KEY).size()).build();
    }
}
