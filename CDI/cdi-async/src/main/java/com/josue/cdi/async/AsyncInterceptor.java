package com.josue.cdi.async;

import javax.annotation.Priority;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

/**
 * Created by Josue on 21/02/2016.
 */
@Interceptor
@Async
@Priority(Interceptor.Priority.PLATFORM_BEFORE)
public class AsyncInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(AsyncInterceptor.class.getName());

    @Resource
    private ManagedExecutorService mes;

    @AroundInvoke
    public Object submitAsync(final InvocationContext ctx) throws Exception {
        logger.info(":: Submiting async ::");
//        return ctx.proceed();
        return mes.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return ctx.proceed();
            }
        });
    }
}