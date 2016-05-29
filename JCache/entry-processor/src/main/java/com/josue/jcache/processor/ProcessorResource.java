package com.josue.jcache.processor;

import com.josue.jcache.processor.processor.ScriptProcessor;
import com.josue.jcache.processor.processor.SimpleProcessor;

import javax.cache.Cache;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by Josue on 26/05/2016.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Produces("text/plain")
@Consumes("text/plain")
@ApplicationScoped
public class ProcessorResource {

    @Inject
    private Cache<String, Integer> cache;

    @POST
    @Path("simple")
    public Response simpleProcessor(@PathParam("key") String key, Integer value) {
        Integer invokeResult = cache.invoke(key, new SimpleProcessor(value));
        return Response.ok("Last cache value: " + invokeResult).build();
    }


    @POST
    @Path("script")
    public Response scriptProcessor(@PathParam("key") String key, String script) {
        Integer invokeResult = cache.invoke(key, new ScriptProcessor(script));
        return Response.ok("Last cache value: " + invokeResult).build();
    }
}
