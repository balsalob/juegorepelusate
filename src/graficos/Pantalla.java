package graficos;

import mapa.cuadro.Cuadro;

/**
 * Created by JoseLuis on 01/02/2017.
 */
public final class Pantalla {

    private final int ancho;
    private final int alto;

    public final int[] pixeles;

    //Temporal

    private final static int LADO_SPRITE = 32;
    private final static int MASCARA_DEL_SPRITE = LADO_SPRITE - 1;

    // Fin de temporal

    public Pantalla(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;

        pixeles = new int[ancho * alto];
    }

    public void limpiar() {
        for (int i = 0; i < pixeles.length; i++) {
            pixeles[i] = 0;
        }
    }

    //Temporal
    public void mostrar(final int compensacionX, final int compensacionY) {
        for(int y = 0; y < alto; y++) {
            int posicionY = y + compensacionY;
            if (posicionY < 0 || posicionY >= alto){
                continue;
            }
            for(int x = 0; x < ancho; x++) {
                int posicionX = x + compensacionX;
                if (posicionX < 0 || posicionX >= ancho){
                    continue;
                }

                //temporal
                pixeles[posicionX + posicionY * ancho] = Sprite.ASFALTO.pixeles[(x & MASCARA_DEL_SPRITE) +
                        (y & MASCARA_DEL_SPRITE) * LADO_SPRITE];
            }
        }
    }
    //Fin temporal

    public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro){
        for(int y = 0; y < cuadro.sprite.getLado(); y++){
            int posicionY = y + compensacionY;
            for (int x = 0; x > cuadro.sprite.getLado(); x++){
                int posicionX = x + compensacionX;
                if (posicionX < 0 || posicionX > ancho || posicionY < 0 || posicionY > ancho) {
                    break;
                }
                pixeles[posicionX + posicionY * ancho] = cuadro.sprite.pixeles[x + y * cuadro.sprite.getLado()];

            }
        }
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

}
