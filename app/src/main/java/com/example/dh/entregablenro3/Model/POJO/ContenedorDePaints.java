package com.example.dh.entregablenro3.Model.POJO;


import java.io.Serializable;
import java.util.List;

public class ContenedorDePaints implements Serializable {

    private List<Paint> paints;
    private Integer posicion;

    public ContenedorDePaints(){}

    public ContenedorDePaints(List<Paint> paints, Integer posicion) {
        this.paints = paints;
        this.posicion = posicion;
    }

    public List<Paint> getPaints() {
        return paints;
    }

    public void setPaints(List<Paint> paints) {
        this.paints = paints;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

}
