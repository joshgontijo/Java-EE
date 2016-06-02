/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.sample;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

/**
 * @author Josue
 */
@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class QueueManager {

    private BlockingQueue<Chunk> queue;
    private HazelcastInstance hazelcastInstance;
    public static final String QUEUE_KEY = "QUEUE";

    public static final String SERVER_NAME = UUID.randomUUID().toString().substring(0,4);

    //consumer
    @Resource
    private ManagedExecutorService mes;
    private static final int CONSUMERS = 10;

    @Inject
    private Processor processor;

    @PostConstruct
    public void init() {
        hazelcastInstance = Hazelcast.newHazelcastInstance();
        queue = hazelcastInstance.getQueue(QUEUE_KEY);

        for (int i = 0; i < CONSUMERS; i++) {
            mes.submit(processor);
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            queue.clear();
            hazelcastInstance.shutdown();
        }catch (Exception ex){
            //do nothing
        }

//        mes.shutdown();
    }

    @Produces
    public BlockingQueue<Chunk> produceQueue() {
        return queue;
    }
}
