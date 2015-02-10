/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.cdi.startup.service;

import com.josue.cdi.startup.RunAtStartup;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Josue
 */
@ApplicationScoped
@RunAtStartup
public class SimpleService {

    private static final Logger logger = Logger.getLogger(SimpleService.class.getName());

    @PostConstruct
    public void onInit() {
        logger.info("########## RUNNING ON STARTUP ##########");
    }

}
