package com.josue.distributed.lambda;

import com.hazelcast.config.Config;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Created by Josue on 22/07/2016.
 */
@ApplicationScoped
public class HazelcastProducer {

    @Produces
    @ApplicationScoped
    public HazelcastInstance getInstance(){
        SerializerConfig sc = new SerializerConfig().
                setImplementation(new KryoSerialiser()).
                setTypeClass(Runnable.class);
        Config config = new Config();
        config.getSerializationConfig().addSerializerConfig(sc);

        return Hazelcast.newHazelcastInstance(config);

    }
}
