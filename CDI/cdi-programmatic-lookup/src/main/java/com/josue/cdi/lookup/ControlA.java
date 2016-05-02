package com.josue.cdi.lookup;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.logging.Logger;

/**
 * @author Josue Gontijo .
 */
@Named("controlA")
@ApplicationScoped
public class ControlA implements Control {


    private static final Logger logger = Logger.getLogger(ControlA.class.getName());

    @PostConstruct
    public void init() {
        logger.info(":: New ControlA instance ::");
    }

    @Override
    public String getMessage() {
        return "Control A message";

    }
}
