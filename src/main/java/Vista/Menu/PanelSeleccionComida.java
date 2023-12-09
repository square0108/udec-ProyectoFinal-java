package Vista.Menu;

import Model.Enumerations.ComidaEnum;
import Vista.Enumerations.BotonesEnum;
import Vista.Enumerations.EnumEstadosBoton;
import Vista.Interface.BotonClickListener;
import Vista.Interface.ParentPanel;
import Vista.Interface.SubPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * Panel en que se muestra de forma visual, los tipos de comida que se pueden poner en los distintos habitats,
 * Este posee dos botones a los costados para cambiar entre la comida seleccionada, para añadir alimento a un habitat
 * Se presiona una vez sobre el botón con el icono de la comida y luego en el habitat donde se quiere añadir esta.
 * OJO: NO esta implementado elegir entre distintos tipos de comida todavia, pero se podria agregar.
 */
public class PanelSeleccionComida implements BotonClickListener, MouseListener, SubPanel {
    private final int WIDTH = 380;
    private final int HEIGHT = 200;
    private static ComidaEnum selectedComida = ComidaEnum.CARNE;
    private BufferedImage comidaIcon;
    private BufferedImage fondo;
    protected BotonFlecha flechaDer;
    protected BotonFlecha flechaIzq;
    private Point position;
    protected Rectangle clickableArea;
    protected ParentPanel parentPanel;

    /**
     * Constructor de PanelSeleccionComida. Se le dan las coordenadas donde se dibujara el panel.
     * @param x Coordenada x.
     * @param y Coordenada y.
     */
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

    public ComidaEnum getSelectedComida() {return selectedComida;}

    /**
     * Dibuja el Panel.
     * @param g
     * @param imageObserver
     */
    public void draw(Graphics g, ImageObserver imageObserver) {
        // Dibujar fondo
        g.setColor(Color.GRAY);
        g.drawImage(fondo,(int) this.position.getX(), (int) this.position.getY(),WIDTH, HEIGHT,imageObserver);

        /* OJO: Si esto se agrega se dibujarian los Botones con flechas.
        Esto se debe hacer en caso de querer agregar distintos tipos de comida
        flechaIzq.draw(g, imageObserver);
        flechaDer.draw(g, imageObserver);
        */

        // Dibujar Icono
        g.drawImage(comidaIcon, (int) this.position.getX() + WIDTH/2 -50, (int) this.position.getY() + HEIGHT/2 - 50,
                100, 100, imageObserver);
    }
    /**
     * Cambia el parametro selectedComida(Enum), al siguiente del Enum.
     */
    public void cambiarSiguienteComida() {
        selectedComida = selectedComida.siguiente();
        setComidaIcon();
    }
    /**
     * Cambia el parametro selectedComida(Enum), al anterior del Enum.
     */
    public void cambiarAnteriorComida() {
        selectedComida = selectedComida.anterior();
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
    /**
     * Selecciona la textura del icono mostrado en el Panel, va cambiando dependiendo de la comida seleccionada.
     */
    public void setComidaIcon() {
        String texture_path = "src/main/java/resources/icons/" + selectedComida.getTexturePath();
        try {
            this.comidaIcon = ImageIO.read(new File(texture_path));
        } catch (IOException e) {
            System.out.println("TEXTURA NO ENCONTRADA!!!!! (PanelSeleccionAnimal)");
        }
    }

    @Override
    public void notifyParent() {
        parentPanel.contextualUpdate(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (clickableArea.contains(e.getPoint())) {
            notifyParent();
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
