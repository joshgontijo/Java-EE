package com.josue.jcache.processor;

import javax.cache.Cache;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by Josue on 26/05/2016.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/cache")
@Produces("text/plain")
@Consumes("text/plain")
public class CacheResource {

    @Inject
    private Cache<String, Integer> cache;

    @Inject
    private ProcessorResource processorResource;

    @Path("{key}/processor")
    public ProcessorResource processor() {
        return processorResource;
    }

    @POST
    @Path("{key}")
    public Response addEntry(@PathParam("key") String key, Integer value) {
        cache.put(key, value);
        return Response.ok(":: INSERT " + key + "=" + value + " ::").build();
    }

    @GET
    @Path("{key}")
    public Response getEntry(@PathParam("key") String key) {
        Integer invokeResult = cache.get(key);
        return Response.ok("VALUE = " + invokeResult).build();
    }

}
