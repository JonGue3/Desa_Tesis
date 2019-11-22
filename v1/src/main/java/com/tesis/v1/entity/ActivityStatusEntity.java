package com.tesis.v1.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "T_ACTIVITY_STATUS")
public class ActivityStatusEntity {

    @Id
    @Column(name = "ID_ACTIVITY_STATUS")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private long idActivityStatus;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    private byte status;

    public ActivityStatusEntity() {
    }

    public ActivityStatusEntity(String description, byte status) {
        this.description = description;
        this.status = status;
    }

    public long getIdActivityStatus() {
        return idActivityStatus;
    }

    public void setIdActivityStatus(long idActivityStatus) {
        this.idActivityStatus = idActivityStatus;
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
        return "ActivityStatusEntity{" +
                "idActivityStatus=" + idActivityStatus +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
