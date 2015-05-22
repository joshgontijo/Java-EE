/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.multiplefiles.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author Josue
 */
@Named
@RequestScoped
public class UploadBoundary {

    private static final Logger logger = Logger.getLogger(UploadBoundary.class.getName());
    private List<Part> files;

    public void upload() {
        for (Part file : files) {

            logger.info("call upload...");
            logger.log(Level.INFO, "content-type:{0}", file.getContentType());
            logger.log(Level.INFO, "filename:{0}", file.getName());
            logger.log(Level.INFO, "submitted filename:{0}", file.getSubmittedFileName());
            logger.log(Level.INFO, "size:{0}", file.getSize());
            try {

                byte[] results = new byte[(int) file.getSize()];
                InputStream in = file.getInputStream();
                in.read(results);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, " ex @{0}", ex);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Uploaded!"));
        }
    }

    public List<Part> getFiles() {
        return files;
    }

    public void setFiles(List<Part> files) {
        this.files = files;
    }

}
