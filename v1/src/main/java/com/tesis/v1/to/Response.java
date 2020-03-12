package com.tesis.v1.to;

import java.io.Serializable;

public class Response implements Serializable {
    private String status;
    private Object data;
    private Object data2;
    private Object data3;
    private String token;
    private String perfil;
    private Object statusMembership;

    public Response(){

    }

    public Response(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Response(String status, Object data, Object data2, Object data3) {
        this.status = status;
        this.data = data;
        this.data2 = data2;
        this.data3 = data3;
    }

    public Response(String status, String token, String perfil) {
        this.status = status;
        this.token = token;
        this.perfil = perfil;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Object getStatusMembership() {
        return statusMembership;
    }

    public void setStatusMembership(Object statusMembership) {
        this.statusMembership = statusMembership;
    }
}