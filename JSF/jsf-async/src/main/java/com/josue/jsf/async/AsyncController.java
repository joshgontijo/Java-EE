/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.async;

import java.io.Serializable;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
@ViewScoped
public class AsyncController implements Serializable {

    private static final Logger logger = Logger.getLogger(AsyncController.class.getName());

    @Resource(lookup = "java:comp/DefaultManagedExecutorService")
    private ManagedExecutorService executor;

    private boolean loading;
    private String asyncResult;

    public void executeAsync() throws InterruptedException {
        logger.info("**** STARTED EXECUTE ASYNC *****");

        loading = true;
        asyncResult = null;
        executor.submit(new Runnable() {
            @Override
            public void run() {
                logger.info("---- STARTED RUNNABLE");
                try {
                    Thread.sleep(3000);

                    asyncResult = UUID.randomUUID().toString();
                    loading = false;

                } catch (InterruptedException ex) {
                    Logger.getLogger(AsyncController.class.getName()).log(Level.SEVERE, null, ex);
                }
                logger.info("---- FINISHED RUNNABLE");
            }
        });
        logger.info("**** FINISHED EXECUTE ASYNC *****");
    }

    public void ping() {
        logger.info("PING...");
    }

    public String getAsyncResult() {
        return asyncResult;
    }

    public void setAsyncResult(String asyncResult) {
        this.asyncResult = asyncResult;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

}
