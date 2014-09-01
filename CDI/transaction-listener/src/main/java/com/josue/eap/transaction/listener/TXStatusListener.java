/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.transaction.listener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;

public class TXStatusListener {

    private static final Logger LOG = Logger.getLogger(TXStatusListener.class.getName());

    public void beforeCompletation(@Observes(during = TransactionPhase.BEFORE_COMPLETION) String msg) {
        LOG.log(Level.INFO, "BEFORE_COMPLETION: {0}", msg);
    }

    public void onInProgress(@Observes(during = TransactionPhase.IN_PROGRESS) String msg) {
        LOG.log(Level.INFO, "IN_PROGRESS: {0}", msg);
    }

    public void onSuccess(@Observes(during = TransactionPhase.AFTER_SUCCESS) String msg) {
        LOG.log(Level.INFO, "AFTER_SUCCESS: {0}", msg);
    }

    public void onFailure(@Observes(during = TransactionPhase.AFTER_FAILURE) String msg) {
        LOG.log(Level.INFO, "AFTER_FAILURE: {0}", msg);
    }


}
