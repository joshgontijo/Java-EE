package com.josue.distributed.lambda;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * Created by Josue on 22/07/2016.
 */
public class Worker implements Callable<String>, Serializable {

    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        return UUID.randomUUID().toString();
    }
}
