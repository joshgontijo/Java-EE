/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.arquillian.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
public class MyItemWriter extends AbstractItemWriter {

    private static final Logger LOG = Logger.getLogger(MyItemWriter.class.getName());

    @Inject
    BatchRepository repository;

    @Inject
    JobContext jobContext;

    @Override
    public void writeItems(List<Object> items) throws Exception {
        LOG.log(Level.INFO, "Received, list size: {0}", items.size());

        List<Person> persons = new ArrayList<>(items.size());
        for (Object o : items) {
            Person p = (Person) o;
            persons.add(p);
        }
        repository.addEntries(jobContext.getInstanceId(), persons);
    }

}
