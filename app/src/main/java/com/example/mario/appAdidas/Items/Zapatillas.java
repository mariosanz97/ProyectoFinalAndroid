package com.example.mario.appAdidas.Items;

import android.widget.ImageView;

/**
 * Created by seeketing on 22/3/17.
 */

public class Zapatillas {
    int existencias;
    ImageView foto;
    String modelo;
    int precio;
    String paraQuien; // mujer hombre niño

    public Zapatillas(int existencias, ImageView foto, String modelo, int precio, String paraQuien) {
        this.existencias = existencias;
        this.foto = foto;
        this.modelo = modelo;
        this.precio = precio;
        this.paraQuien = paraQuien;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public ImageView getFoto() {
        return foto;
    }

    public void setFoto(ImageView foto) {
        this.foto = foto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getParaQuien() {
        return paraQuien;
    }

    public void setParaQuien(String paraQuien) {
        this.paraQuien = paraQuien;
    }
}
