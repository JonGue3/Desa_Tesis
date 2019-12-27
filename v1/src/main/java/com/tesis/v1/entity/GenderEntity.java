package com.tesis.v1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_GENDER")
public class GenderEntity {

    @Id
    @Column(name = "ID_GENDER")
    private  long idGender;

    @Column(name = "DESCRIPTION")
    private String description;

    public GenderEntity() {
    }

    public GenderEntity(long idGender, String description) {
        this.idGender = idGender;
        this.description = description;
    }

    public long getIdGender() {
        return idGender;
    }

    public void setIdGender(long idGender) {
        this.idGender = idGender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "GenderEntity{" +
                "idGender=" + idGender +
                ", description='" + description + '\'' +
                '}';
    }
}
