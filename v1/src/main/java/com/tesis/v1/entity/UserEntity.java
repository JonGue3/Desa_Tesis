package com.tesis.v1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_USER")
public class UserEntity {

    @Id
    @SequenceGenerator(name="USER_SEQ",sequenceName="USER_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USER_SEQ")
    @Column(name = "ID_USER")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private long idUser;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY")
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "ID_USER_STATUS")
    private UserStatusEntity userStatusEntity;

    @ManyToOne
    @JoinColumn (name = "ID_PROFILE")
    private ProfileEntity profileEntity;

    @ManyToOne
    @JoinColumn(name = "ID_GENDER")
    private  GenderEntity genderEntity;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "T_USER_PROYECT", joinColumns = @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER"),
                inverseJoinColumns = @JoinColumn(name = "ID_PROYECT", referencedColumnName = "ID_PROYECT"))
    @Fetch(FetchMode.SUBSELECT)
    private Set<ProjectEntity> projectEntitySet = new HashSet<>();

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "T_USER_ACTIVITY", joinColumns = @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_ACTIVITY", referencedColumnName = "ID_ACTIVITY"))
    @Fetch(FetchMode.SUBSELECT)
    private Set<ActivityEntity> activityEntitySet = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(String fullName, String username, String password, String email, Date birthday, UserStatusEntity userStatusEntity, ProfileEntity profileEntity, GenderEntity genderEntity, Set<ProjectEntity> projectEntitySet, Set<ActivityEntity> activityEntitySet) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.userStatusEntity = userStatusEntity;
        this.profileEntity = profileEntity;
        this.genderEntity = genderEntity;
        this.projectEntitySet = projectEntitySet;
        this.activityEntitySet = activityEntitySet;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public UserStatusEntity getUserStatusEntity() {
        return userStatusEntity;
    }

    public void setUserStatusEntity(UserStatusEntity userStatusEntity) {
        this.userStatusEntity = userStatusEntity;
    }

    public ProfileEntity getProfileEntity() {
        return profileEntity;
    }

    public void setProfileEntity(ProfileEntity profileEntity) {
        this.profileEntity = profileEntity;
    }

    public GenderEntity getGenderEntity() {
        return genderEntity;
    }

    public void setGenderEntity(GenderEntity genderEntity) {
        this.genderEntity = genderEntity;
    }

    public Set<ProjectEntity> getProjectEntitySet() {
        return projectEntitySet;
    }

    public void setProjectEntitySet(Set<ProjectEntity> projectEntitySet) {
        this.projectEntitySet = projectEntitySet;
    }

    public Set<ActivityEntity> getActivityEntitySet() {
        return activityEntitySet;
    }

    public void setActivityEntitySet(Set<ActivityEntity> activityEntitySet) {
        this.activityEntitySet = activityEntitySet;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "idUser=" + idUser +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", userStatusEntity=" + userStatusEntity +
                ", profileEntity=" + profileEntity +
                ", genderEntity=" + genderEntity +
                ", projectEntitySet=" + projectEntitySet +
                ", activityEntitySet=" + activityEntitySet +
                '}';
    }
}
