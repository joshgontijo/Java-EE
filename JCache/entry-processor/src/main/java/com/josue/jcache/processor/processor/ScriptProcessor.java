package com.josue.jcache.processor.processor;

import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.MutableEntry;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.logging.Logger;

/**
 * Created by Josue on 26/05/2016.
 */
public class ScriptProcessor implements EntryProcessor<String, Integer, Integer> {

    private final ScriptEngineManager manager = new ScriptEngineManager();
    private final ScriptEngine engine = manager.getEngineByName("JavaScript");
    private final String script;

    private static final Logger log = Logger.getLogger(ScriptProcessor.class.getName());

    public ScriptProcessor(String script) {
        this.script = script;
    }

    @Override
    public Integer process(MutableEntry<String, Integer> mutableEntry, Object... objects) throws EntryProcessorException {
        try {
            String finalScript = "function run(value, log) {"
                    + script
                    + "}";

            engine.eval(finalScript);
            Double outputValue  = (Double) ((Invocable) engine).invokeFunction("run", new Object[]{mutableEntry.getValue(), log});
            int newValue = outputValue.intValue();
            mutableEntry.setValue(newValue);
            return newValue;

        } catch (Exception e) {
            throw new EntryProcessorException("Error executing script", e);
        }
    }
}
