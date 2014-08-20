/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.batch.complete.jpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
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

    private BufferedReader reader;

    @Override
    public Object readItem() throws Exception {
        try {
            return reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(MyItemReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void open(Serializable checkpoint) throws UnsupportedEncodingException {
        reader = new BufferedReader(
                new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/META-INF/FakeNameGenerator.com_76bded74.csv"), "UTF8"));
    }

}
