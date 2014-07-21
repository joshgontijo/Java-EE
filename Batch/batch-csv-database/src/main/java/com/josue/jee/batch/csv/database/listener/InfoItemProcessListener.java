/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.batch.csv.database.listener;

import java.util.logging.Logger;
import javax.batch.api.chunk.listener.ItemProcessListener;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
public class InfoItemProcessListener implements ItemProcessListener {

    private static final Logger LOG = Logger.getLogger(InfoItemProcessListener.class.getName());

    @Override
    public void beforeProcess(Object item) throws Exception {
//        LOG.info("BEFORE");
    }

    @Override
    public void afterProcess(Object item, Object result) throws Exception {
//        LOG.info("AFTER");
    }

    @Override
    public void onProcessError(Object item, Exception ex) throws Exception {
//        LOG.info("ON PROCESS ERROR");
    }

}
