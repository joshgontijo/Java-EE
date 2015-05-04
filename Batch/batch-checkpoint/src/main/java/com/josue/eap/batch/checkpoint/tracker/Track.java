/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josue.eap.batch.checkpoint.tracker;

/**
 *
 * @author Josue
 */
public class Track {
    
    private int id;
    private String name;

    public Track(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Track{" + "id=" + id + ", name=" + name + '}';
    }
    
    
    
    
}
