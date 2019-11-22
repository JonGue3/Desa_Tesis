package com.tesis.v1.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "T_ACTIVITY_POINTS")
public class ActivityPointsEntity {

    @Id
    @Column(name = "ID_ACTIVITY_POINTS")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private long idActivityPoints;

    @Column(name = "POINTS")
    private Integer points;

    public ActivityPointsEntity() {
    }

    public ActivityPointsEntity(Integer points) {
        this.points = points;
    }

    public long getIdActivityPoints() {
        return idActivityPoints;
    }

    public void setIdActivityPoints(long idActivityPoints) {
        this.idActivityPoints = idActivityPoints;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "ActivityPointsEntity{" +
                "idActivityPoints=" + idActivityPoints +
                ", points=" + points +
                '}';
    }
}
