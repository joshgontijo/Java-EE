/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.selectonemenu.converter;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jgontijo NOTE: THIS ONLY WORKS WHEN THE BEAN LIST IS A PROPERTY OF A
 * VIEWSCOPED BEAN)
 */
@FacesConverter("genericConverter")
public class GenericConverter implements Converter, Serializable {

    private static final Logger logger = Logger.getLogger(GenericConverter.class.getName());

    @PostConstruct
    public void init() {
        logger.info(":: POSTCONSTRUCT ::");
    }

    @PreDestroy
    public void destroy() {
        logger.info(":: PREDESTROY ::");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        logger.info(value);

        HtmlSelectOneMenu selectItemUI = (HtmlSelectOneMenu) component;
        UISelectItems items = (UISelectItems) selectItemUI.getChildren().get(1);

        List<?> values = (List) items.getValue();
        for (Object item : values) {
            if (item.toString().equals(value)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

}
