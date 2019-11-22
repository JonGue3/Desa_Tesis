package com.tesis.v1.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "T_PROYECT", schema = "USR_SYSINFO", catalog = "")
public class TProyectEntity {
    private int idProyect;
    private String proyectName;
    private Integer totalActivities;
    private Integer finishedActivities;
    private Timestamp startDate;
    private Timestamp endingDate;

    @Id
    @Column(name = "ID_PROYECT")
    public int getIdProyect() {
        return idProyect;
    }

    public void setIdProyect(int idProyect) {
        this.idProyect = idProyect;
    }

    @Basic
    @Column(name = "PROYECT_NAME")
    public String getProyectName() {
        return proyectName;
    }

    public void setProyectName(String proyectName) {
        this.proyectName = proyectName;
    }

    @Basic
    @Column(name = "TOTAL_ACTIVITIES")
    public Integer getTotalActivities() {
        return totalActivities;
    }

    public void setTotalActivities(Integer totalActivities) {
        this.totalActivities = totalActivities;
    }

    @Basic
    @Column(name = "FINISHED_ACTIVITIES")
    public Integer getFinishedActivities() {
        return finishedActivities;
    }

    public void setFinishedActivities(Integer finishedActivities) {
        this.finishedActivities = finishedActivities;
    }

    @Basic
    @Column(name = "START_DATE")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "ENDING_DATE")
    public Timestamp getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Timestamp endingDate) {
        this.endingDate = endingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TProyectEntity that = (TProyectEntity) o;
        return idProyect == that.idProyect &&
                Objects.equals(proyectName, that.proyectName) &&
                Objects.equals(totalActivities, that.totalActivities) &&
                Objects.equals(finishedActivities, that.finishedActivities) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endingDate, that.endingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProyect, proyectName, totalActivities, finishedActivities, startDate, endingDate);
    }
}
