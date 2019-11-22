package com.tesis.v1.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "T_PROFILE")
public class ProfileEntity {

    @Id
    @Column(name = "ID_PROFILE")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private long idProfile;

    @Column(name = "DESCRIPTION")
    private String description;

    public ProfileEntity() {
    }

    public ProfileEntity(String description) {
        this.description = description;
    }

    public long getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(long idProfile) {
        this.idProfile = idProfile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProfileEntity{" +
                "idProfile=" + idProfile +
                ", description='" + description + '\'' +
                '}';
    }
}
