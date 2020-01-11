package com.tesis.v1.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_TRANSACTION")
public class TransactionEntity {

    @Id
    @Column(name = "ID_TRANSACTION")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private long idTransaction;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "T_TRANSACTION_PROFILE", joinColumns = @JoinColumn(name = "ID_TRANSACTION",
            referencedColumnName = "ID_TRANSACTION"), inverseJoinColumns = @JoinColumn(name = "ID_PROFILE",
            referencedColumnName = "ID_PROFILE"))
    @Fetch(FetchMode.SUBSELECT)
    private Set<ProfileEntity> profileEntitySet = new HashSet<>();

    public TransactionEntity() {
    }

    public TransactionEntity(long idTransaction, String description) {
        this.idTransaction = idTransaction;
        this.description = description;
    }

    public long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProfileEntity> getProfileEntitySet() {
        return profileEntitySet;
    }

    public void setProfileEntitySet(Set<ProfileEntity> profileEntitySet) {
        this.profileEntitySet = profileEntitySet;
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "idTransaction=" + idTransaction +
                ", description='" + description + '\'' +
                '}';
    }
}
