package com.tesis.v1.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "T_ACTIVITY_STATUS", schema = "USR_SYSINFO", catalog = "")
public class TActivityStatusEntity {
    private int idActivityStatus;
    private String description;
    private Integer status;

    @Id
    @Column(name = "ID_ACTIVITY_STATUS")
    public int getIdActivityStatus() {
        return idActivityStatus;
    }

    public void setIdActivityStatus(int idActivityStatus) {
        this.idActivityStatus = idActivityStatus;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "STATUS")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TActivityStatusEntity that = (TActivityStatusEntity) o;
        return idActivityStatus == that.idActivityStatus &&
                Objects.equals(description, that.description) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActivityStatus, description, status);
    }
}
