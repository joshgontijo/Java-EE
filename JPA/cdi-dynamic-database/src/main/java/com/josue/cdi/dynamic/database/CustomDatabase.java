/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.cdi.dynamic.database;

import java.lang.annotation.ElementType;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Target;
import javax.inject.Qualifier;


/**
 * @author Josue
 */
@Qualifier
@Retention(RUNTIME)
@Target({FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
public @interface CustomDatabase {

}
