/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.batch.checkpoint;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

@Named
public class MyItemProcessor implements ItemProcessor {

    private static final Logger LOG = Logger.getLogger(MyItemProcessor.class.getName());

    public MyItemProcessor() {
        LOG.info("!--- PROCESSOR CREATED ---!");

    }

    @Override
    public String processItem(Object t) {

        t = "  #" + t.toString() + "#";

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyItemProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        LOG.log(Level.INFO, "PROCESSED ITEM: {0}", t);
        return t.toString();

    }
}
