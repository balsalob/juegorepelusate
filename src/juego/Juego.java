package juego;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import control.Teclado;
import graficos.Pantalla;

/**
 * Created by Jose Luis on 29/01/2017.
 */
public class Juego extends Canvas implements Runnable{

    private static final int ANCHO = 800;
    private static final int ALTO = 600;

    private static volatile boolean enfuncionamiento = false;

    private static final String NOMBRE = "Repelusate";

    private static int aps = 0;
    private static int fps = 0;

    private static int x = 0;
    private static int y = 0;

    private static JFrame ventana;
    private static Thread thread;
    private static Teclado teclado;
    private static Pantalla pantalla;

    private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
    private static int[] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();
    private static final ImageIcon icono = new ImageIcon(Juego.class.getResource("/icono/icono.png"));

    private Juego() {
        setPreferredSize(new Dimension(ANCHO, ALTO));

        pantalla = new Pantalla(ANCHO, ALTO);

        teclado = new Teclado();
        addKeyListener(teclado);

        ventana = new JFrame(NOMBRE);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setIconImage(icono.getImage());
        ventana.setLayout(new BorderLayout());
        ventana.add(this, BorderLayout.CENTER);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciar();
    }

    private synchronized void iniciar() {
        enfuncionamiento = true;

        thread = new Thread(this, "Graficos");
        thread.start();
    }

    private synchronized void detener() {
        enfuncionamiento = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void actualizar() {
        teclado.actualizar();

        if(teclado.arriba) {
            y++;
        }
        if(teclado.abajo) {
            y--;
        }
        if(teclado.izquierda) {
            x++;
        }
        if(teclado.derecha) {
            x--;
        }
        aps++;
    }

    private void mostrar() {

        BufferStrategy estrategia = getBufferStrategy();

        if(estrategia == null) {
            createBufferStrategy(3);
            return;
        }

        pantalla.limpiar();
        pantalla.mostrar(x, y);

        System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);

        Graphics g = estrategia.getDrawGraphics();

        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
        g.dispose();

        estrategia.show();

        fps++;
    }

    @Override
    public void run() {
        final int NS_POR_SEGUNDO = 1000000000;
        final byte APS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO/APS_OBJETIVO;

        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();

        double tiempoTranscurrido;
        double delta = 0;

        requestFocus();

        while(enfuncionamiento) {
            final long inicioBucle = System.nanoTime();

            tiempoTranscurrido = inicioBucle  - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;

            delta +=  tiempoTranscurrido/NS_POR_ACTUALIZACION;


            while (delta >= 1) {
                actualizar();
                delta--;
            }

            mostrar();

            if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
                ventana.setTitle(NOMBRE + " || APS: " + aps + " || FPS: " + fps);
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }
}
