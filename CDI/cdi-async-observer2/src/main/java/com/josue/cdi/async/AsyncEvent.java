package com.josue.cdi.async;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * Created by Josue on 21/02/2016.
 */
public class AsyncEvent<T>  {

    @Resource
    private ManagedExecutorService mes;

    @Inject
    private Event<Object> wrappedEvent;

    public void fire(final T t) {
        mes.submit(new Runnable() {
            @Override
            public void run() {
                wrappedEvent.fire(t);
            }
        });
    }
}
