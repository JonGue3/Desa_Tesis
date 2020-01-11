package com.tesis.v1.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_PROFILE")
public class ProfileEntity {

    @Id
    @Column(name = "ID_PROFILE")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private long idProfile;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(mappedBy = "profileEntitySet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TransactionEntity> transactionEntitySet = new HashSet<>();

    public ProfileEntity() {
    }

    public ProfileEntity(String description) {
        this.description = description;
    }

    public long getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(long idProfile) {
        this.idProfile = idProfile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TransactionEntity> getTransactionEntitySet() {
        return transactionEntitySet;
    }

    public void setTransactionEntitySet(Set<TransactionEntity> transactionEntitySet) {
        this.transactionEntitySet = transactionEntitySet;
    }

    @Override
    public String toString() {
        return "ProfileEntity{" +
                "idProfile=" + idProfile +
                ", description='" + description + '\'' +
                '}';
    }
}
