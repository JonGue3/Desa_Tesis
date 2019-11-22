package com.tesis.v1.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "T_ACTIVITY", schema = "USR_SYSINFO", catalog = "")
public class TActivityEntity {
    private int idActivity;
    private String activityName;
    private Timestamp startDate;
    private Timestamp endingDate;

    @Id
    @Column(name = "ID_ACTIVITY")
    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    @Basic
    @Column(name = "ACTIVITY_NAME")
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
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
        TActivityEntity that = (TActivityEntity) o;
        return idActivity == that.idActivity &&
                Objects.equals(activityName, that.activityName) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endingDate, that.endingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActivity, activityName, startDate, endingDate);
    }
}
