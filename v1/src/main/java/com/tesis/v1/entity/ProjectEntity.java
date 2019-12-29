package com.tesis.v1.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "T_PROYECT")
public class ProjectEntity {

    @Id
    @SequenceGenerator(name="PROYECT_SEQ",sequenceName="PROYECT_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PROYECT_SEQ")
    @Column(name = "ID_PROYECT")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private long idProject;

    @Column(name = "PROYECT_NAME")
    private String projectName;

    @Column(name = "TOTAL_ACTIVITIES")
    private Integer totalActivities;

    @Column(name = "FINISHED_ACTIVITIES")
    private Integer finishedActivities;

    @Column(name = "START_DATE")
    private Calendar startDate;

    @Column(name = "ENDING_DATE")
    private Calendar endingDate;

    public ProjectEntity() {
    }

    public ProjectEntity(String projectName, Integer totalActivities, Integer finishedActivities, Calendar startDate, Calendar endingDate) {
        this.projectName = projectName;
        this.totalActivities = totalActivities;
        this.finishedActivities = finishedActivities;
        this.startDate = startDate;
        this.endingDate = endingDate;
    }

    public long getIdProject() {
        return idProject;
    }

    public void setIdProject(long idProject) {
        this.idProject = idProject;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getTotalActivities() {
        return totalActivities;
    }

    public void setTotalActivities(Integer totalActivities) {
        this.totalActivities = totalActivities;
    }

    public Integer getFinishedActivities() {
        return finishedActivities;
    }

    public void setFinishedActivities(Integer finishedActivities) {
        this.finishedActivities = finishedActivities;
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

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "idProject=" + idProject +
                ", projectName='" + projectName + '\'' +
                ", totalActivities=" + totalActivities +
                ", finishedActivities=" + finishedActivities +
                ", startDate=" + startDate +
                ", endingDate=" + endingDate +
                '}';
    }
}