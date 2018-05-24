package com.example.dh.entregablenro3;


import java.util.List;

public class ContenedorDePaints {

    private List<Paint> paints;
    private Integer posicion;

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
