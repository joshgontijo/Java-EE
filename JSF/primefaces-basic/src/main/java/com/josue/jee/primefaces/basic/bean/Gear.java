/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.primefaces.basic.bean;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Josue
 */
@Entity
@Table(name = "gear")
@XmlRootElement
public class Gear extends Resource {

    public static enum GearType {

        HELMET, USHIELD, BELT, BOOTS, CLOTHES
    }

    private GearType gearType;

    public GearType getGearType() {
        return gearType;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.gearType);
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
        final Gear other = (Gear) obj;
        if (this.gearType != other.gearType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Gear{" + "uuid=" + super.getUuid() + '}';
    }

}
