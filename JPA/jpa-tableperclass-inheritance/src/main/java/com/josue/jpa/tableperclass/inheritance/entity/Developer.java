/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jpa.tableperclass.inheritance.entity;

import javax.persistence.Entity;

/**
 *
 * @author Josue
 */
@Entity
public class Developer extends Employee {

    private String language;

    public Developer() {
    }

    public Developer(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
