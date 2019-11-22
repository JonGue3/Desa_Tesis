package com.tesis.v1.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "T_TOKEN", schema = "USR_SYSINFO", catalog = "")
public class TTokenEntity {
    private int idToken;
    private String tokenDescription;
    private Timestamp expiryDate;

    @Id
    @Column(name = "ID_TOKEN")
    public int getIdToken() {
        return idToken;
    }

    public void setIdToken(int idToken) {
        this.idToken = idToken;
    }

    @Basic
    @Column(name = "TOKEN_DESCRIPTION")
    public String getTokenDescription() {
        return tokenDescription;
    }

    public void setTokenDescription(String tokenDescription) {
        this.tokenDescription = tokenDescription;
    }

    @Basic
    @Column(name = "EXPIRY_DATE")
    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TTokenEntity that = (TTokenEntity) o;
        return idToken == that.idToken &&
                Objects.equals(tokenDescription, that.tokenDescription) &&
                Objects.equals(expiryDate, that.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idToken, tokenDescription, expiryDate);
    }
}
