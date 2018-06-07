package com.example.dh.entregablenro3.Model.POJO;

import java.util.Date;

import javax.sql.StatementEvent;

public class Mensaje {

    private String nombreUserMensaje;
    private String  horaMensaje;
    private String textoMensaje;
    private String userId;

    public Mensaje() {
    }

    public Mensaje(String nombreUserMensaje, String horaMensaje, String textoMensaje, String userId) {
        this.nombreUserMensaje = nombreUserMensaje;
        this.horaMensaje = horaMensaje;
        this.textoMensaje = textoMensaje;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNombreUserMensaje() {
        return nombreUserMensaje;
    }

    public void setNombreUserMensaje(String nombreUserMensaje) {
        this.nombreUserMensaje = nombreUserMensaje;
    }

    public String getHoraMensaje() {
        return horaMensaje;
    }

    public void setHoraMensaje(String horaMensaje) {
        this.horaMensaje = horaMensaje;
    }

    public String getTextoMensaje() {
        return textoMensaje;
    }

    public void setTextoMensaje(String textoMensaje) {
        this.textoMensaje = textoMensaje;
    }
}
