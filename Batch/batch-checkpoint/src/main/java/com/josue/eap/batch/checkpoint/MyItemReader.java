/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.batch.checkpoint;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
public class MyItemReader extends AbstractItemReader {

    private static final Logger LOG = Logger.getLogger(MyItemReader.class.getName());

    private static final int SIZE = 10;
    private static int ACTUAL = 0;

    @Override
    public Object readItem() throws Exception {
        
        if (ACTUAL < SIZE) {
            ACTUAL++;
            LOG.log(Level.INFO, "READING ITEM: {0}", ACTUAL);
            return ACTUAL;
        }
        return null;
    }

    @Override
    public void open(Serializable checkpoint)  {
        if (checkpoint != null) {
            LOG.log(Level.INFO, "**** CHECKPOINT STATE: {0}", checkpoint.toString());
        } else {
            LOG.info("**** CHECKPOINT STATE: NULL");
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return super.checkpointInfo(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
