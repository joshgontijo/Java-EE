/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.batch.checkpoint;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
public class MyItemWriter extends AbstractItemWriter {

    private static final Logger LOG = Logger.getLogger(MyItemWriter.class.getName());

    @Override
    public void writeItems(List<Object> items) throws Exception {

        String content = "";
        for (Object o : items) {
            content += o.toString();
        }
        LOG.log(Level.INFO, "Received, list CONTENT: {0}", content);
    }

}
