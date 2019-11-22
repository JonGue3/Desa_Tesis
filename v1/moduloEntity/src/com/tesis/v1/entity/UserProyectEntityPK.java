package com.tesis.v1.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UserProyectEntityPK implements Serializable {
    private int idUser;
    private int idProyect;

    @Column(name = "ID_USER")
    @Id
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Column(name = "ID_PROYECT")
    @Id
    public int getIdProyect() {
        return idProyect;
    }

    public void setIdProyect(int idProyect) {
        this.idProyect = idProyect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProyectEntityPK that = (UserProyectEntityPK) o;
        return idUser == that.idUser &&
                idProyect == that.idProyect;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idProyect);
    }
}
