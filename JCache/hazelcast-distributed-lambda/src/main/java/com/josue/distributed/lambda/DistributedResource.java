package com.josue.distributed.lambda;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * Created by Josue on 22/07/2016.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/distributed")
@ApplicationScoped
public class DistributedResource {

    @Inject
    private HazelcastInstance hazelcast;

    @Resource
    private ManagedScheduledExecutorService mes;

    @GET
    @Produces("text/plain")
    public void getMessage(@Suspended AsyncResponse asyncResponse) throws Exception {


        IExecutorService executorService = hazelcast.getExecutorService("MY-EXECUTOR");
//        Set<Member> members = hazelcast.getCluster().getMembers();
//        members.forEach(m -> ex.executeOnMember(new Worker(), m));


        Supplier<String> s = new Worker()::call;

        CompletableFuture.supplyAsync(() -> new Worker(), mes).thenAccept(asyncResponse::resume);


    }

}
