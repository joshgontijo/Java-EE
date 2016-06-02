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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

/**
 * Created by Josue on 02/06/2016.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/chunk")
public class ChunkResource {

    private static final Logger logger = Logger.getLogger(ChunkResource.class.getName());

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
        List<Chunk> chunks = new ArrayList<>();

        long start = System.currentTimeMillis();
        utx.begin();
        for (int i = 1; i <= num; i++) {
            Chunk chunk = new Chunk(i, Status.CREATED);
            em.persist(chunk);
            chunks.add(chunk);
        }
        utx.commit();
        long end = System.currentTimeMillis();
        logger.info(":: INSERTED " + num + " ITEMS IN THE DATABASE IN " + (end - start) + "ms ::");

        start = System.currentTimeMillis();
        for (Chunk c : chunks) {
            queue.offer(c);
        }
        end = System.currentTimeMillis();
        logger.info(":: INSERTED " + num + " ITEMS IN THE QUEUE IN "+ (end - start) + "ms ::");

        return Response.ok().build();
    }
}
