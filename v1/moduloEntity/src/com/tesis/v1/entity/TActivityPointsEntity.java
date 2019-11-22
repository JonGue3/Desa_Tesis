package com.tesis.v1.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "T_ACTIVITY_POINTS", schema = "USR_SYSINFO", catalog = "")
public class TActivityPointsEntity {
    private long idActivityPoints;
    private Integer points;

    @Id
    @Column(name = "ID_ACTIVITY_POINTS")
    public long getIdActivityPoints() {
        return idActivityPoints;
    }

    public void setIdActivityPoints(long idActivityPoints) {
        this.idActivityPoints = idActivityPoints;
    }

    @Basic
    @Column(name = "POINTS")
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TActivityPointsEntity that = (TActivityPointsEntity) o;
        return idActivityPoints == that.idActivityPoints &&
                Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActivityPoints, points);
    }
}
