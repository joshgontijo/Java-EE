/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.exception.handling;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
@RequestScoped
public class SimpleController {

    public void checkedException() throws Exception {
        throw new Exception("SOMETHING WRONG HAPPENED !!! OMG *CHECKED EXCEPTION*");
    }

    public void uncheckedException() {
        throw new RuntimeException("SOMETHING WRONG HAPPENED !!! OMG *UNCHECKED EXCEPTION*");
    }

    public void exceptionMessage() {
        try {
            throw new Exception("SOMETHING WRONG HAPPENED !!! OMG *EXCEPTION MESSAGE*");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", e.getMessage()));
        }
    }

}
