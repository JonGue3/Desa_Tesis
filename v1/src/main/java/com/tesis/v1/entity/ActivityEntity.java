package com.tesis.v1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Date startDate;

    @Column(name = "ENDING_DATE")
    private Date endingDate;

    @ManyToOne
    @JoinColumn(name = "ID_ACTIVITY_STATUS")
    private ActivityStatusEntity activityStatusEntity;

    @ManyToOne
    @JoinColumn(name = "ID_PROJECT")
    private ProjectEntity projectEntity;

    @JsonIgnore
    @ManyToMany(mappedBy = "activityEntitySet", cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Set<UserEntity> userEntitySet = new HashSet<>();

    public ActivityEntity() {
    }

    public ActivityEntity(String activityName, ActivityPointsEntity activityPointsEntity, Date startDate, Date endingDate, ActivityStatusEntity activityStatusEntity, ProjectEntity projectEntity, Set<UserEntity> userEntitySet) {
        this.activityName = activityName;
        this.activityPointsEntity = activityPointsEntity;
        this.startDate = startDate;
        this.endingDate = endingDate;
        this.activityStatusEntity = activityStatusEntity;
        this.projectEntity = projectEntity;
        this.userEntitySet = userEntitySet;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
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

    public Set<UserEntity> getUserEntitySet() {
        return userEntitySet;
    }

    public void setUserEntitySet(Set<UserEntity> userEntitySet) {
        this.userEntitySet = userEntitySet;
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
                ", userEntitySet=" + userEntitySet +
                '}';
    }
}
