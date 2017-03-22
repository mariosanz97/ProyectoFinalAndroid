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
    String paraQuien; // mujer hombre niño
    String desc;

    @Override
    public String toString() {
        return "Pantalones{" +
                "existencias=" + existencias +
                ", foto=" + foto +
                ", modelo='" + modelo + '\'' +
                ", precio=" + precio +
                ", paraQuien='" + paraQuien + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public Pantalones(int existencias, ImageView foto, String modelo, int precio, String paraQuien, String desc) {
        this.existencias = existencias;
        this.foto = foto;
        this.modelo = modelo;
        this.precio = precio;
        this.paraQuien = paraQuien;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
