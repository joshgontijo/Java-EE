/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.admin.applicaiton;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
@SessionScoped
public class AdminController implements Serializable {

    private static final Logger logger = Logger.getLogger(AdminController.class.getName());

    private String page = "main";

    public void message() {
        Messages.infoMessage("asljdkadasd");
    }

    @PostConstruct
    public void init() {
        logger.info("********* Initializing ADMIN CONTROLLER*************");
    }

    public String navigate(String page) {
        String appendParam = page.contains("?") ? page + "&faces-redirect=true" : page + "?faces-redirect=true";
        return appendParam;
    }

    public String index() {
        return "/index.xhtml?faces-redirect=true";
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

}
