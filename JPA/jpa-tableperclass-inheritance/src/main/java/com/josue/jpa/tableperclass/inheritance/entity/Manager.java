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
public class Manager extends Employee {

    private String project;

    public Manager() {
    }

    public Manager(String project) {
        this.project = project;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

}
