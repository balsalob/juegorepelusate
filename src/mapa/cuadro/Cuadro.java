package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

/**
 * Created by Jose Luis on 03/02/2017.
 */
public abstract class Cuadro {
    public int x;
    public int y;

    public Sprite sprite;

    //Colección de cuadros

    public static final Cuadro VACIO = new CuadroVacio(Sprite.VACIO);
    public static final Cuadro ASFALTO = new CuadroAsfalto(Sprite.ASFALTO);

    //Fin de la colección

    public Cuadro(Sprite sprite) {
        this.sprite = sprite;
    }

    public void mostrar(int x, int y, Pantalla pantalla) {
        pantalla.mostrarCuadro(x << 5, y << 5, this);
    }

    public boolean solido() {
        return false;
    }
}
