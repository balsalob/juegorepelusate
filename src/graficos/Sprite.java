package graficos;

/**
 * Created by JoseLuis on 30/01/2017.
 */
public final class Sprite {
    private final int lado;

    private int x;
    private int y;

    public int[] pixeles;
    private final HojaSprite hoja;

    //Colección de sprites

    public static final Sprite ASFALTO = new Sprite(32, 0, 0, HojaSprite.desierto);

    //Fin de la colección

    public Sprite(final int lado, final int columna, final int fila, final HojaSprite hoja) {
        this.lado = lado;
        this.hoja = hoja;

        pixeles = new int[lado * lado];

        this.x = columna * lado;
        this.y = fila * lado;

        for (int y = 0; y < lado; y++) {
            for (int x = 0; x < lado; x++) {
                pixeles[y + x * lado] = hoja.pixeles[(x + this.x)+(y+this.y) * hoja.getAncho()];
            }
        }
    }

    public int getLado() {
        return lado;
    }
}
