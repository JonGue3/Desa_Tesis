package com.tesis.v1.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "ID_USER_STATUS")
    private UserStatusEntity userStatusEntity;

    @ManyToOne
    @JoinColumn (name = "ID_PROFILE")
    private ProfileEntity profileEntity;

    public UserEntity() {
    }

    public UserEntity(String fullName, String username, String password, String email, UserStatusEntity userStatusEntity, ProfileEntity profileEntity) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userStatusEntity = userStatusEntity;
        this.profileEntity = profileEntity;
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

    @Override
    public String toString() {
        return "UserEntity{" +
                "idUser=" + idUser +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userStatusEntity=" + userStatusEntity +
                ", profileEntity=" + profileEntity +
                '}';
    }
}
