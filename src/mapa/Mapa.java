package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;
import mapa.cuadro.CuadroAsfalto;

/**
 * Created by Jose Luis on 02/02/2017.
 */
public abstract class Mapa {

    protected int ancho;
    protected int alto;

    protected int[] cuadros;

    public Mapa(String ruta) {
        cargarMapa(ruta);
    }

    public Mapa(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;

        cuadros = new int[ancho * alto];
        generarMapa();
    }

    private void cargarMapa(String ruta) {
    }

    public void actualizar() {

    }

    public void mostrar(int compensacionX, int compensacionY, Pantalla pantalla) {

        pantalla.estableceDiferenciaX(compensacionX, compensacionY);

        int norte = compensacionY >> 5;
        int sur = (compensacionY + pantalla.getAlto()) >> 5;
        int oeste = compensacionX >> 5;
        int este = (compensacionX + pantalla.getAncho()) >> 5;

        for (int y = norte; y < sur; y++) {
            for (int x = oeste; x < este; x++) {
                obtenCuadro(x, y).mostrar(x, y, pantalla);
            }
        }
    }

    public Cuadro obtenCuadro(final int x, final int y) {

        switch (cuadros[x + y * ancho]) {
            case 0:
                return Cuadro.ASFALTO;
            case 1:
                return Cuadro.VACIO;
            case 2:
                return Cuadro.VACIO;
            case 3:
                return Cuadro.VACIO;
            default:
                return Cuadro.VACIO;
        }
    }

    protected void generarMapa() {

    }
}
