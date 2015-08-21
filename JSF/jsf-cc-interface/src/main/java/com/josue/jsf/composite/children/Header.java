/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.composite.children;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Josue
 */
public class Header implements Serializable {

    private String headerValue;

    public String getHeaderValue() {
        return headerValue;
    }

    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }

    public Header() {
    }

    public Header(String headerValue) {
        this.headerValue = headerValue;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.headerValue);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Header other = (Header) obj;
        if (!Objects.equals(this.headerValue, other.headerValue)) {
            return false;
        }
        return true;
    }
    
    

}
