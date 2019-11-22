package com.tesis.v1.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "T_MENU")
public class MenuEntity {

    @Id
    @Column(name = "ID_MENU")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private long idMenu;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "MENU_URL")
    private String menuUrl;

    public MenuEntity() {
    }

    public MenuEntity(String description, String menuUrl) {
        this.description = description;
        this.menuUrl = menuUrl;
    }

    public long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(long idMenu) {
        this.idMenu = idMenu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    @Override
    public String toString() {
        return "MenuEntity{" +
                "idMenu=" + idMenu +
                ", description='" + description + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                '}';
    }
}
