package com.tesis.v1.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "T_TOKEN")
public class TokenEntity {

    @Id
    @SequenceGenerator(name="TOKEN_SEQ",sequenceName="TOKEN_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TOKEN_SEQ")
    @Column(name = "ID_TOKEN")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private long idToken;

    @Column(name = "TOKEN_DESCRIPTION")
    private String tokenDescription;

    @Column(name = "EXPIRY_DATE")
    private Calendar expiryDate;

    @OneToOne
    @JoinColumn(name = "ID_USER")
    private UserEntity userEntity;

    public TokenEntity() {
    }

    public TokenEntity(String tokenDescription, Calendar expiryDate, UserEntity userEntity) {
        this.tokenDescription = tokenDescription;
        this.expiryDate = expiryDate;
        this.userEntity = userEntity;
    }

    public long getIdToken() {
        return idToken;
    }

    public void setIdToken(long idToken) {
        this.idToken = idToken;
    }

    public String getTokenDescription() {
        return tokenDescription;
    }

    public void setTokenDescription(String tokenDescription) {
        this.tokenDescription = tokenDescription;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "TokenEntity{" +
                "idToken=" + idToken +
                ", tokenDescription='" + tokenDescription + '\'' +
                ", expiryDate=" + expiryDate +
                ", userEntity=" + userEntity +
                '}';
    }
}
