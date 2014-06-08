/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.primefaces.basic;

import com.josue.jee.primefaces.basic.bean.Gear;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class GearBean implements Serializable {

    private List<Gear> gears = new ArrayList<>();
    private Gear selected;

    public Gear getSelected() {
        return selected;
    }

    public void setSelected(Gear selected) {
        this.selected = selected;
    }

    public GearBean() {

        for (int i = 0; i < 30; i++) {
            Gear g = new Gear();
            g.setDateCreated(new Date());
            g.setGearType(Gear.GearType.BELT);
            g.setUuid(UUID.randomUUID().toString());
            gears.add(g);
        }

    }

    public List<Gear> getGears() {
        return gears;
    }
    private static final Logger LOG = Logger.getLogger(GearBean.class.getName());

    public void setGears(List<Gear> gears) {
        this.gears = gears;
    }

    public void removeGear() {
        LOG.log(Level.INFO, "Removing gear: {0}", selected);
        this.gears.remove(selected);
    }

    public void editGear() {
        LOG.log(Level.INFO, "Editing gear: {0}", selected);

    }

}
