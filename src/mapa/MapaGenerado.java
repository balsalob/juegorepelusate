package mapa;

import java.util.Random;

/**
 * Created by Jose Luis on 03/02/2017.
 */
public class MapaGenerado extends Mapa{

    private final Random aleatorio = new Random();

    public MapaGenerado(int ancho, int alto) {
        super(ancho, alto);
    }

    protected void generarMapa() {
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < alto; x++) {
                cuadros[x + y * ancho] = aleatorio.nextInt(3);
            }
        }
    }

}
