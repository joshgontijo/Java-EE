package com.josue.sample;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Josue on 02/06/2016.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/chunk")
public class ChunkResource {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private BlockingQueue<Chunk> queue;

    @Resource
    private UserTransaction utx;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getMessage(@DefaultValue("100") @QueryParam("num") Integer num) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        for (int i = 1; i <= num; i++) {
            Chunk chunk = new Chunk(i, Status.CREATED);
            utx.begin();
            em.persist(chunk);
            utx.commit();
            queue.offer(chunk);
        }
        return Response.ok().build();
    }
}
