package com.example.mario.appAdidas.Items;

import android.widget.ImageView;

/**
 * Created by mario on 28/02/2017.
 */

public class Pantalones {
    int existencias;
    ImageView foto;
    String modelo;
    int precio;


    public Pantalones(int existencias, ImageView foto, String modelo, int precio) {
        this.existencias = existencias;
        this.foto = foto;
        this.modelo = modelo;
        this.precio = precio;
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
}
