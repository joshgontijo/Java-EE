/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.arquillian.batch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Josue
 */
@ApplicationScoped
public class BatchRepository {

    private Map<Long, List<Person>> entriesMap = new HashMap<>();

    public List<Person> getEntries(long jobId) {
        return entriesMap.get(jobId);
    }

    public Set<Long> getAllJobs() {
        return entriesMap.keySet();
    }

    public List<Person> addEntries(long jobId, List<Person> persons) {
        if (entriesMap.containsKey(jobId)) {
            entriesMap.get(jobId).addAll(persons);
            return entriesMap.get(jobId);
        }
        return entriesMap.put(jobId, persons);
    }

}
