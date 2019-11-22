package com.tesis.v1.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "T_USER_STATUS")
public class UserStatusEntity {

    @Id
    @Column(name = "ID_USER_STATUS")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private long idUserStatus;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    private byte status;

    public UserStatusEntity() {
    }

    public UserStatusEntity(String description, byte status) {
        this.description = description;
        this.status = status;
    }

    public long getIdUserStatus() {
        return idUserStatus;
    }

    public void setIdUserStatus(long idUserStatus) {
        this.idUserStatus = idUserStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserStatusEntity{" +
                "idUserStatus=" + idUserStatus +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
