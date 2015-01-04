/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jboss.custom.jackson.provider;

import java.util.Date;

/**
 *
 * @author Josue
 */
public class User {

    private String name;
    private int age;
    private Date birthdate;

    private String nullValue;

    public User() {
    }

    public User(String name, int age, Date birthdate) {
        this.nullValue = null;
        this.name = name;
        this.age = age;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getNullValue() {
        return nullValue;
    }

    public void setNullValue(String nullValue) {
        this.nullValue = nullValue;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", age=" + age + ", birthdate=" + birthdate + '}';
    }

}
