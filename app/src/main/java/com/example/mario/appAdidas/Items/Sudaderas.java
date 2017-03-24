package com.example.mario.appAdidas.Items;

import android.widget.ImageView;

/**
 * Created by seeketing on 22/3/17.
 */

public class Sudaderas {
    int existencias;
    String foto;
    String modelo;
    double precio;
    String para; // mujer hombre niño
    String descripcion;

    public Sudaderas(){}

    public Sudaderas(int existencias, String foto, String modelo, double precio, String para, String descripcion) {
        this.existencias = existencias;
        this.foto = foto;
        this.modelo = modelo;
        this.precio = precio;
        this.para = para;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

}
