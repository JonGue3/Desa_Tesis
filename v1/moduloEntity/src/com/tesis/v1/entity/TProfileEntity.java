package com.tesis.v1.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "T_PROFILE", schema = "USR_SYSINFO", catalog = "")
public class TProfileEntity {
    private int idProfile;
    private String description;

    @Id
    @Column(name = "ID_PROFILE")
    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TProfileEntity that = (TProfileEntity) o;
        return idProfile == that.idProfile &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProfile, description);
    }
}
