package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

/**
 * Created by Jose Luis on 03/02/2017.
 */
public class CuadroAsfalto extends Cuadro{

    public CuadroAsfalto(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void mostrar(int x, int y, Pantalla pantalla) {
        pantalla.mostrarCuadro(x, y, this);
    }
}
