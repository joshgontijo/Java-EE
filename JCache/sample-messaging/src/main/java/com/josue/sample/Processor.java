package com.josue.sample;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Josue on 02/06/2016.
 */
@ApplicationScoped
public class Processor implements Runnable {

    @Resource
    private UserTransaction utx;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private BlockingQueue<Chunk> queue;

    private static final Logger logger = Logger.getLogger(Processor.class.getName());

    private boolean run = true;

    @PostConstruct
    public void init(){
        logger.info(":: INITIALISING CONSUMER ::");
    }

    @Override
    public void run() {
        while (run) {
            try {
                Chunk poll = queue.poll(3, TimeUnit.SECONDS);
                if (poll != null) {
                    poll.setStatus(Status.COMPLETED);

                    utx.begin();
                    em.merge(poll);
                    utx.commit();

                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
                run = false;
            }
        }
    }
}
