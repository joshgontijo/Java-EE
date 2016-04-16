package com.josue.cdi.startup;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

/**
 * Created by Josue on 16/04/2016.
 */
@ApplicationScoped
public class StartupListener {

    private static final Logger logger = Logger.getLogger(StartupListener.class.getName());

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        logger.info(":: StartupListener#INIT :: " + init);
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {
        logger.info(":: StartupListener#DESTROY ::" + init);
    }
}
