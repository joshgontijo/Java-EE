/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.async;

import java.io.Serializable;
import java.util.UUID;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
@ViewScoped
public class SyncController implements Serializable {

    private static final Logger logger = Logger.getLogger(SyncController.class.getName());

    private boolean loading;

    private String result;
    private String asyncResult;

    public void execute() throws InterruptedException {
        logger.info("**** STARTED EXECUTE *****");
        loading = true;

        Thread.sleep(3000);

        result = UUID.randomUUID().toString();
        loading = false;

        logger.info("**** FINISEHD EXECUTE *****");
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
