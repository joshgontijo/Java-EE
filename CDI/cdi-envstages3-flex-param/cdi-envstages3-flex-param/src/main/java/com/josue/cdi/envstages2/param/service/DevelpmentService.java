package com.josue.cdi.envstages2.param.service;

import com.josue.cdi.envstages2.param.cdi.Environment;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Josue
 */
@Environment("dev")
@ApplicationScoped
public class DevelpmentService implements Service {

    @Override
    public String doIt() {
        return "DEVELOPMENT SERVICE";
    }
}
