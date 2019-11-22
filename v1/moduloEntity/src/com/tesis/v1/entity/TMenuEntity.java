package com.tesis.v1.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "T_MENU", schema = "USR_SYSINFO", catalog = "")
public class TMenuEntity {
    private int idMenu;
    private String description;
    private String menuUrl;

    @Id
    @Column(name = "ID_MENU")
    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "MENU_URL")
    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TMenuEntity that = (TMenuEntity) o;
        return idMenu == that.idMenu &&
                Objects.equals(description, that.description) &&
                Objects.equals(menuUrl, that.menuUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMenu, description, menuUrl);
    }
}
