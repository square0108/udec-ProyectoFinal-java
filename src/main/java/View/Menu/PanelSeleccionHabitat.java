package View.Menu;

import Model.Enumerations.HabitatEnum;
import View.Enumerations.BotonesEnum;
import View.Enumerations.EnumEstadosBoton;
import View.Interface.BotonClickListener;
import View.Interface.ParentPanel;
import View.Interface.SubPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * Panel en que se muestra de forma visual, los habitats que se pueden poner en los distintos slots vacios,
 * Este posee dos botones a los costados para cambiar entre el habitat seleccionado, para añadir un habitat
 * Se presiona una vez sobre el botón con el icono del habitat y luego en el slot vacio donde se quiere añadir este.
 */
public class PanelSeleccionHabitat implements BotonClickListener, MouseListener, SubPanel {
    private final int WIDTH = 380;
    private final int HEIGHT = 200;
    protected static HabitatEnum selectedHabitat = HabitatEnum.JUNGLA;
    private BufferedImage comidaIcon;
    private BufferedImage fondo;
    protected BotonFlecha flechaDer;
    protected BotonFlecha flechaIzq;
    private Point position;
    private Rectangle clickableArea;
    protected ParentPanel parentPanel;

    /**
     * Constructor de PanelSeleccionHabitat. Se le dan las coordenadas donde se dibujara el panel.
     * @param x Coordenada x.
     * @param y Coordenada y.
     */
    public PanelSeleccionHabitat(int x, int y) {
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

        setHabitatIcon();
    }

    /**
     * Dibuja el Panel.
     * @param g
     * @param imageObserver
     */
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
    /**
     * Cambia el parametro selectedHabitat(Enum), al siguiente del Enum.
     */
    public void cambiarSiguienteHabitat() {
        selectedHabitat = selectedHabitat.siguiente();
        setHabitatIcon();
    }
    /**
     * Cambia el parametro selectedHabitat(Enum), al anterior del Enum.
     */
    public void cambiarAnteriorHabitat() {
        selectedHabitat = selectedHabitat.anterior();
        setHabitatIcon();
    }
    @Override
    public void onBotonClick() {
        if (flechaDer.getState() == EnumEstadosBoton.CLICK) {
            cambiarSiguienteHabitat();
        } else if (flechaIzq.getState() == EnumEstadosBoton.CLICK) {
            cambiarAnteriorHabitat();
        }
    }
    /**
     * Selecciona la textura del icono mostrado en el Panel, va cambiando dependiendo del habitat seleccionado.
     */
    public void setHabitatIcon() {
        String texture_path = "src/main/java/resources/icons/" + selectedHabitat.getTexturePath();
        try {
            this.comidaIcon = ImageIO.read(new File(texture_path));
        } catch (IOException e) {
            System.out.println("TEXTURA NO ENCONTRADA!!!!! (PanelSeleccionHabitat)");
        }
    }

    public HabitatEnum getSelectedHabitat() {return selectedHabitat;}

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
