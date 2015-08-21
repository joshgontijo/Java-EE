/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.composite.children;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author jgontijo
 */
@Named
@ViewScoped
public class CriteriaBean implements Serializable {

    private static final Logger logger = Logger.getLogger(CriteriaBean.class.getName());

    private List<Wrapper> headers;

    private List<Wrapper> selectedHeaders;
    private String selectedHead;

    @PostConstruct
    public void init() {
        headers = new ArrayList<>();
        headers.add(new Wrapper("header1"));
        headers.add(new Wrapper("header2"));
        headers.add(new Wrapper("header3"));

        selectedHeaders = new ArrayList<>();
        selectedHeaders.add(new Wrapper());
    }
    
    public void submit(){
        logger.info(":: SUBMITTING ::");
        for(Wrapper selectedString : selectedHeaders){
            logger.info(selectedString.getValue());
        }
    }

    public void addEntry() {
        logger.info(":: ADDING FIELD ::");
        selectedHeaders.add(new Wrapper());
    }

    public void removeEntry(Wrapper header) {
        logger.info(":: REMOVING FIELD ::");
        selectedHeaders.remove(header);
    }

    public List<Wrapper> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Wrapper> headers) {
        this.headers = headers;
    }

    public List<Wrapper> getSelectedHeaders() {
        return selectedHeaders;
    }

    public void setSelectedHeaders(List<Wrapper> selectedHeaders) {
        this.selectedHeaders = selectedHeaders;
    }

    public String getSelectedHead() {
        return selectedHead;
    }

    public void setSelectedHead(String selectedHead) {
        this.selectedHead = selectedHead;
    }
    
    

}
