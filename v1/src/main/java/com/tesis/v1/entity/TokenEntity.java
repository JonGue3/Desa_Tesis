package com.tesis.v1.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "T_TOKEN")
@Scope(value = "prototype")
public class TokenEntity {
    private static final int EXPIRATION = 15 * 1; //expira en 15 minutos
    @Id
    @SequenceGenerator(name = "TOKEN_SEQ", sequenceName = "TOKEN_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOKEN_SEQ")
    @Column(name = "ID_TOKEN")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private long idToken;

    @Column(name = "TOKEN_DESCRIPTION")
    private String tokenDescription;

    @Column(name = "EXPIRY_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date expiryDate;

    @OneToOne
    @JoinColumn(name = "ID_USER")
    private UserEntity userEntity;


    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public boolean isExpired() {
        return new Date().after(this.expiryDate);
    }

    public TokenEntity() {
    }

    public TokenEntity(String tokenDescription, UserEntity userEntity) {
        this.tokenDescription = tokenDescription;
        this.userEntity = userEntity;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public TokenEntity(String tokenDescription, Date expiryDate, UserEntity userEntity) {
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

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
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
