package com.josue.concurrency;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Josue on 27/04/2016.
 */
@ApplicationScoped
public class ExecutorProducer {

    @Resource
    ManagedThreadFactory threadFactory;

    @Produces
    public ExecutorService produces(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1),
                new RejectionListener());

        return threadPoolExecutor;
    }
}
