/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.arquillian.batch;

import java.util.StringTokenizer;
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
    public Person processItem(Object t) {
        StringTokenizer tokens = new StringTokenizer((String) t, ",");

        Person person = new Person();

        person.setFirstName(tokens.nextToken());
        person.setLastName(tokens.nextToken());
        person.setGender(tokens.nextToken());
        person.setCity(tokens.nextToken());
        person.setZipCode(tokens.nextToken());
        person.setEmail(tokens.nextToken());
        person.setUsername(tokens.nextToken());
        person.setTelephone(tokens.nextToken());

        return person;
    }
}
