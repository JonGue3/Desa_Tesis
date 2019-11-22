package com.tesis.v1.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USER_PROYECT", schema = "USR_SYSINFO", catalog = "")
@IdClass(UserProyectEntityPK.class)
public class UserProyectEntity {
    private int idUser;
    private int idProyect;

    @Id
    @Column(name = "ID_USER")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Id
    @Column(name = "ID_PROYECT")
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
        UserProyectEntity that = (UserProyectEntity) o;
        return idUser == that.idUser &&
                idProyect == that.idProyect;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idProyect);
    }
}
