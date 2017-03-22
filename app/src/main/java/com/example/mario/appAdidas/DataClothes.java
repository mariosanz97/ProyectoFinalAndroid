package com.example.mario.appAdidas;

import com.example.mario.appAdidas.Items.Pantalones;
import com.example.mario.appAdidas.Items.Sudaderas;
import com.example.mario.appAdidas.Items.Zapatillas;

import java.util.ArrayList;

/**
 * Created by seeketing on 22/3/17.
 */

/**
 * clase donde se almacenan los datos de la bbdd
 */
public class DataClothes {
    public ArrayList<Zapatillas> zapatillas;
    public ArrayList<Pantalones> pantalones;
    public ArrayList<Sudaderas> sudaderas;

    public DataClothes(){
        zapatillas = new ArrayList<>();
        pantalones = new ArrayList<>();
        sudaderas = new ArrayList<>();
    }

    public ArrayList<Zapatillas> getZapatillas() {
        return zapatillas;
    }

    public void setZapatillas(ArrayList<Zapatillas> zapatillas) {
        this.zapatillas = zapatillas;
    }

    public ArrayList<Pantalones> getPantalones() {
        return pantalones;
    }

    public void setPantalones(ArrayList<Pantalones> pantalones) {
        this.pantalones = pantalones;
    }

    public ArrayList<Sudaderas> getSudaderas() {
        return sudaderas;
    }

    public void setSudaderas(ArrayList<Sudaderas> sudaderas) {
        this.sudaderas = sudaderas;
    }
}
