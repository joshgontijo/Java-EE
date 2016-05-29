package com.josue.jcache.processor.processor;

import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.MutableEntry;

/**
 * Created by Josue on 26/05/2016.
 */
public class SimpleProcessor implements EntryProcessor<String, Integer, Integer> {

    private final Integer value;

    public SimpleProcessor(Integer value) {
        this.value = value;
    }

    @Override
    public Integer process(MutableEntry<String, Integer> mutableEntry, Object... objects) throws EntryProcessorException {
        Integer cachevalue = mutableEntry.getValue();
        int finalValue = cachevalue + value;
        mutableEntry.setValue(finalValue);
        return finalValue;
    }
}
