package Vista.Menu;

import Model.Enumerations.ComidaEnum;
import Vista.Enumerations.BotonesEnum;
import Vista.Enumerations.EnumEstadosBoton;
import Vista.Interface.BotonClickListener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/*
Quizas podria hacer una clase Panel Selección y que el panel seleccion animal extienda a esa, lo mismo con
Panel comida
 */
public class PanelSeleccionComida implements BotonClickListener, MouseListener {
    private final int WIDTH = 380;
    private final int HEIGHT = 200;
    private ComidaEnum comida = ComidaEnum.CARNE;
    private BufferedImage comidaIcon;
    private BufferedImage fondo;
    protected BotonFlecha flechaDer;
    protected BotonFlecha flechaIzq;
    private Point position;
    private Rectangle clickableArea;


    public PanelSeleccionComida(int x, int y) {
        try {
            this.fondo = ImageIO.read(new File("src/main/java/resources/icons/panelsbackgroud.png"));
        } catch (IOException e) {
            System.out.println("TEXTURA NO ENCONTRADA!!!!! (FondoPanelSelecciónComida)");
        }

        this.position = new Point(x, y);
        flechaDer = new BotonFlecha(this.position, WIDTH - 90, 10, BotonesEnum.FLECHADERECHA);
        flechaIzq = new BotonFlecha(this.position, 10, 10, BotonesEnum.FLECHAIZQUIERDA);

        clickableArea = new Rectangle((int) this.position.getX() + WIDTH / 2 - 50,
                (int) this.position.getY() + HEIGHT / 2 - 50, 100, 100);

        flechaDer.setBotonClickListener(this);
        flechaIzq.setBotonClickListener(this);

        setComidaIcon();
    }

    public void draw(Graphics g, ImageObserver imageObserver) {
        g.setColor(Color.GRAY);
        g.drawImage(fondo,(int) this.position.getX(), (int) this.position.getY(),WIDTH, HEIGHT,imageObserver);
        flechaIzq.draw(g, imageObserver);
        flechaDer.draw(g, imageObserver);

        // Dibuja el panel intermedio directamente
        // TODO: Aquí puede estar el error
        g.drawImage(comidaIcon, (int) this.position.getX() + WIDTH/2 -50, (int) this.position.getY() + HEIGHT/2 - 50,
                100, 100, imageObserver);
    }

    public void cambiarSiguienteComida() {
        comida = comida.siguiente();
        setComidaIcon();
    }
    public void cambiarAnteriorComida() {
        comida = comida.anterior();
        setComidaIcon();
    }

    @Override
    public void onBotonClick() {
        if (flechaDer.getState() == EnumEstadosBoton.CLICK) {
            cambiarSiguienteComida();
        } else if (flechaIzq.getState() == EnumEstadosBoton.CLICK) {
            cambiarAnteriorComida();
        }
    }

    public void setComidaIcon() {
        String texture_path = "src/main/java/resources/icons/" + comida.getTexturePath();
        try {
            this.comidaIcon = ImageIO.read(new File(texture_path));
        } catch (IOException e) {
            System.out.println("TEXTURA NO ENCONTRADA!!!!! (PanelSeleccionAnimal)");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (clickableArea.contains(e.getPoint())) {
            System.out.println("CLICK EN COMIDA");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}