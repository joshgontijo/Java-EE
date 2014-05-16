/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package josue.jee.liquibaseejb.basiccdiliquibase;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity(name = "resource")
public class Resource implements Serializable {

    @Id
    private String uuid;

    @Transient
    private String href;

    @Column(name = "date_created", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "date_updated", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    @PrePersist
    public void prePersist() {

        this.uuid = UUID.randomUUID().toString();
        this.dateCreated = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.dateUpdated = new Date();
    }

    public static Resource fromHref(String href) {
        Resource res = new Resource();
        res.setHref(href);
        return res;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = new Timestamp(dateCreated.getTime());
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = new Timestamp(dateUpdated.getTime());
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.uuid);
        hash = 19 * hash + Objects.hashCode(this.href);
        hash = 19 * hash + Objects.hashCode(this.dateCreated);
        hash = 19 * hash + Objects.hashCode(this.dateUpdated);
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
        final Resource other = (Resource) obj;
        if (!Objects.equals(this.uuid, other.uuid)) {
            return false;
        }
        if (!Objects.equals(this.href, other.href)) {
            return false;
        }
        if (!Objects.equals(this.dateCreated, other.dateCreated)) {
            return false;
        }
        if (!Objects.equals(this.dateUpdated, other.dateUpdated)) {
            return false;
        }
        return true;
    }

}
