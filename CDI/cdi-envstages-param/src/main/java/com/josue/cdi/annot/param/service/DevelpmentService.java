/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.cdi.annot.param.service;

import com.josue.cdi.annot.param.cdi.Environment;
import com.josue.cdi.annot.param.cdi.Stage;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Josue
 */
@Environment(stage = Stage.DEVELOPMENT)
@ApplicationScoped
public class DevelpmentService implements Service {

    @Override
    public String doIt() {
        return "DEVELOPMENT SERVICE";
    }
}
