package com.josue.jcache.queue;

import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.MutableEntry;
import java.io.Serializable;
import java.util.Queue;

/**
 * Created by Josue on 26/05/2016.
 */
public class Incrementor implements EntryProcessor<String, Queue<Integer>, Queue<Integer>>, Serializable {

    private final Integer value;

    public Incrementor(Integer value) {
        this.value = value;
    }

    @Override
    public Queue<Integer> process(MutableEntry<String, Queue<Integer>> mutableEntry, Object... objects) throws EntryProcessorException {
        Queue<Integer> queue = mutableEntry.getValue();
        queue.offer(value);
        mutableEntry.setValue(queue);
        return queue;
    }
}
