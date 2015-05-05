/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.view.scope;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
//http://incepttechnologies.blogspot.co.uk/2013/05/jsf22-viewscope-using-cdi.html
//http://stackoverflow.com/questions/20251176/viewscoped-bean-recreated-on-every-postback-request-when-using-jsf-2-2
//Use @javax.faces.view.ViewScoped instead of @javax.faces.bean.ViewScope
@Named
@javax.faces.view.ViewScoped
public class ViewBoundary implements Serializable {

    private int counter;
    private static final Logger logger = Logger.getLogger(ViewBoundary.class.getName());

    @Inject
    SampleService service;

    @PostConstruct
    public void inti() {
        logger.info("******** @PostConstruct **********");
    }

    public void increment() {
        counter += service.getOne();
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}
