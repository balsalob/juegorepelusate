package mapa;

import graficos.Pantalla;

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
        int norte = compensacionY >> 5;
        int sur = (compensacionY + pantalla.getAlto()) >> 5;
        int este = (compensacionX + pantalla.getAncho()) >> 5;
        int oeste = compensacionX >> 5;
    }

    protected void generarMapa() {

    }
}
