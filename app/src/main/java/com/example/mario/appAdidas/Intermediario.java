package com.example.mario.appAdidas;

/**
 * Created by seeketing on 22/3/17.
 */

public class Intermediario {
    PaneActivity paneActivity;
    public int selected;

    public Intermediario(int selected) {
        this.selected = selected;
        paneActivity = new PaneActivity();
        paneActivity.inicializar(selected);
    }
}
