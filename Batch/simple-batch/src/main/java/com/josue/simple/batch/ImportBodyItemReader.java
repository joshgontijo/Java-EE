/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.simple.batch;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
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
public class ImportBodyItemReader extends AbstractItemReader {

    private static final Logger logger = Logger.getLogger(ImportBodyItemReader.class.getName());

    protected LineNumberReader lineNumberReader;

    @Override
    public Object readItem() throws Exception {
        String readLine = lineNumberReader.readLine();
        return readLine;
    }

    @Override
    public void open(Serializable checkpoint) throws IOException {
        lineNumberReader = new LineNumberReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("import-file.csv")));
        if (checkpoint != null) {
            processRestart(checkpoint);
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        //set the actual line for each checkpoint
        return lineNumberReader.getLineNumber();
    }

    protected void processRestart(Serializable checkpoint) throws IOException {
        Integer lineCheckpoint = (Integer) checkpoint;
        logger.log(Level.INFO, "Restarting at line {0}", lineCheckpoint + 1);
        for (int line = 0; line < lineCheckpoint; line++) {
            lineNumberReader.readLine();
        }
    }

}
