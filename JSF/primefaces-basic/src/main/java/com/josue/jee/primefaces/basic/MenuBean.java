/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.primefaces.basic;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

@Named(value = "menuBean")
@ManagedBean
@SessionScoped
public class MenuBean implements Serializable {

    private String page = gearList;
    private static final String gearList = "pages/gear/list.xhtml";
    private static final String gearCreate = "pages/gear/create.xhtml";
    private static final String gearUpdate = "pages/gear/update.xhtml";

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void navigateMenu(String page) {

        LOG.info(page);
        switch (page) {
            case "gearList":
                this.page = gearList;
                break;
            case "gearCreate":
                this.page = gearCreate;
                break;
            case "gearUpdate":
                this.page = gearUpdate;
                break;
            default:
                this.page = gearList;
                break;
        }
//        this.page = "pages/gear/list_1.xhtml";
    }
    private static final Logger LOG = Logger.getLogger(MenuBean.class.getName());

}
