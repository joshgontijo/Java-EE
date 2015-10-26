/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.composite.children;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
@ViewScoped
public class SampleBean implements Serializable {

    private static final Logger logger = Logger.getLogger(SampleBean.class.getName());
    private int count = 1;
    private boolean stop = false;

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void changePollStatus(Boolean newStatus) {
        logger.log(Level.INFO, "CHANGING STATS TO: {0}", newStatus);
        stop = newStatus;
    }

    //POLLING
    public void pollMethod() {
        count++;
        logger.log(Level.INFO, "POLLING... COUNT: {0} -> POLL STATUS {1}", new Object[]{count, stop});
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //MODAL
    public void confirm() {
        logger.info("CONFIRM");
    }

    public void cancel() {
        logger.info("CANCEL");
    }

    public void closeModal() {
        logger.info("Closing modal");
    }

    //MESSAGE
    public void addMessage() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SEVERITY_INFO", "Info detail"));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "SEVERITY_WARN", "Warn detail"));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SEVERITY_ERROR", "Error detail"));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "SEVERITY_FATAL", "Fatal detail"));
    }

    //NOTIFICATION
    public void addNotification() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SEVERITY_INFO", "Info detail"));
    }
}
