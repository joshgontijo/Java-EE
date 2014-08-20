package com.josue.eap.cdi.producer;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Josue on 8/16/2014.
 */
@ApplicationPath("api")
public class MyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set classes = new  HashSet<>();
        classes.add(somker.class);
        return classes;
    }
}
