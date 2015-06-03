/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.unicorn.jsf;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
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

    @PostConstruct
    public void init() {
        logger.info("********* Initializing ADMIN CONTROLLER*************");
    }

    public String getMenuClass(String menuSection) {
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        String path = viewId.substring(viewId.lastIndexOf("/") + 1);
        String pageName = path.substring(0, path.lastIndexOf("."));
        if (pageName.contains(menuSection)) {
            return "active";
        }
        return "";
    }

}
