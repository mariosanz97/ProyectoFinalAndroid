package com.example.mario.appAdidas.RopaApp;

import android.widget.ImageView;

/**
 * Created by mario on 22/03/2017.
 */

public class Ropa {
    int precio;
    String modelo;
    String para;
    ImageView img;
    String foto;
    int existencias;
    String descripcion;

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencia) {
        this.existencias = existencia;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Ropa() {

    }

    public Ropa(int precio, String modelo, String para, String descripcion, int existencias, String foto) {
        this.precio = precio;
        this.modelo = modelo;
        this.para = para;
        this.existencias = existencias;
        this.descripcion = descripcion;
        this.foto = foto;

    }
/*
    public Ropa(String modelo, int precio ){
        this.precio=precio;
        this.modelo=modelo;

    }
*/

    @Override
    public String toString() {
        return "Ropa{" +
                "precio=" + precio +
                ", modelo='" + modelo + '\'' +
                ", para='" + para + '\'' +
                ", img=" + img +
                '}';
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }
}
