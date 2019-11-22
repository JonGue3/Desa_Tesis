package com.tesis.v1.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "T_ACTIVITY")
public class ActivityEntity {

    @Id
    @SequenceGenerator(name="ACTIVITY_SEQ",sequenceName="ACTIVITY_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ACTIVITY_SEQ")
    @Column(name = "ID_ACTIVITY")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private long idActivity;

    @Column(name = "ACTIVITY_NAME")
    private String activityName;

    @ManyToOne
    @JoinColumn(name = "ID_ACTIVITY_POINTS")
    private ActivityPointsEntity activityPointsEntity;

    @Column(name = "START_DATE")
    private Calendar startDate;

    @Column(name = "ENDING_DATE")
    private Calendar endingDate;

    @ManyToOne
    @JoinColumn(name = "ID_ACTIVITY_STATUS")
    private ActivityStatusEntity activityStatusEntity;

    @ManyToOne
    @JoinColumn(name = "ID_PROYECT")
    private ProjectEntity projectEntity;

    public ActivityEntity() {
    }

    public long getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(long idActivity) {
        this.idActivity = idActivity;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public ActivityPointsEntity getActivityPointsEntity() {
        return activityPointsEntity;
    }

    public void setActivityPointsEntity(ActivityPointsEntity activityPointsEntity) {
        this.activityPointsEntity = activityPointsEntity;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Calendar endingDate) {
        this.endingDate = endingDate;
    }

    public ActivityStatusEntity getActivityStatusEntity() {
        return activityStatusEntity;
    }

    public void setActivityStatusEntity(ActivityStatusEntity activityStatusEntity) {
        this.activityStatusEntity = activityStatusEntity;
    }

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    @Override
    public String toString() {
        return "ActivityEntity{" +
                "idActivity=" + idActivity +
                ", activityName='" + activityName + '\'' +
                ", activityPointsEntity=" + activityPointsEntity +
                ", startDate=" + startDate +
                ", endingDate=" + endingDate +
                ", activityStatusEntity=" + activityStatusEntity +
                ", projectEntity=" + projectEntity +
                '}';
    }
}
