package com.tesis.v1.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "T_USER_STATUS", schema = "USR_SYSINFO", catalog = "")
public class TUserStatusEntity {
    private int idUserStatus;
    private String description;
    private Integer status;

    @Id
    @Column(name = "ID_USER_STATUS")
    public int getIdUserStatus() {
        return idUserStatus;
    }

    public void setIdUserStatus(int idUserStatus) {
        this.idUserStatus = idUserStatus;
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
        TUserStatusEntity that = (TUserStatusEntity) o;
        return idUserStatus == that.idUserStatus &&
                Objects.equals(description, that.description) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserStatus, description, status);
    }
}
