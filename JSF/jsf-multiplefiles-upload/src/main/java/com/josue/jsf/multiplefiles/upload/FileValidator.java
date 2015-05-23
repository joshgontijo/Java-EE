/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.multiplefiles.upload;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator(value = "fileValidator")
public class FileValidator implements Validator {

    private static final long MAX_FILE_SIZE = 10485760L; // 10MB.
    private static final List<String> ALLOWED_FORMATS = Arrays.asList("csv");

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        List<Part> files = (List) value;

        for (Part part : files) {
            if (part != null) {
                if (part.getSize() > MAX_FILE_SIZE) {
                    try {
                        part.delete(); // Free resources!
                        throw new ValidatorException(new FacesMessage(String.format(
                                "File exceeds maximum permitted size of %d bytes.", MAX_FILE_SIZE)));
                    } catch (IOException ex) {
                        Logger.getLogger(FileValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                int i = part.getSubmittedFileName().lastIndexOf('.');
                String extension = "";
                if (i > 0) {
                    extension = part.getSubmittedFileName().substring(i + 1);
                }
                if (!ALLOWED_FORMATS.contains(extension)) {
                    throw new ValidatorException(new FacesMessage(String.format(
                            "Invalid format, only .csv is allowed", extension)));
                }
            }
        }

    }

}
