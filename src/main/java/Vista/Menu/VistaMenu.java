package Vista.Menu;

import Model.Enumerations.ComidaEnum;
import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.HabitatEnum;
import Vista.Interface.ParentPanel;
import Vista.Interface.SubPanel;
import Vista.VistaPrincipal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Vista.Enumerations.EnumCursor.*;

/**
 * Es un panel el cual muestra el menu de opciones
 */
public class VistaMenu extends JPanel implements ParentPanel {
    final private int IMG_WIDTH = 400;
    final private int IMG_HEIGHT = 900;
    private BufferedImage fondo;
    private Point imageCorner;
    private static final PanelSeleccionAnimal panelAnimal = new PanelSeleccionAnimal(10, 250);
    private static final PanelSeleccionComida panelComida = new PanelSeleccionComida(10, 480);
    private static final PanelSeleccionHabitat panelHabitat = new PanelSeleccionHabitat(10,20);
    private final PanelAlertas panelAlertas;
    private final VistaPrincipal vistaPrincipal;
    public VistaMenu(VistaPrincipal parentFrame){
        this.setPreferredSize(new Dimension(IMG_WIDTH,IMG_HEIGHT));
        imageCorner = new Point(0,0);

        panelHabitat.parentPanel = this;
        panelAnimal.parentPanel = this;
        panelComida.parentPanel = this;

        panelAlertas = new PanelAlertas(10,710);

        this.vistaPrincipal = parentFrame;

        // Cargamos textura fondo
        try {
            this.fondo = ImageIO.read(new File("src/main/java/resources/icons/menubackgroud.png"));
        } catch (IOException e) {
            System.out.println("TEXTURA NO ENCONTRADA!!!!! (FondoPanelSelecciónAnimal)");
        }

        this.addMouseListener(panelHabitat.flechaDer);
        this.addMouseListener(panelHabitat.flechaIzq);

        this.addMouseMotionListener(panelHabitat.flechaIzq);
        this.addMouseMotionListener(panelHabitat.flechaDer);

        this.addMouseListener(panelHabitat);

        // Añadimos listener de Panel Animal

        this.addMouseListener(panelAnimal.flechaDer);
        this.addMouseListener(panelAnimal.flechaIzq);

        this.addMouseMotionListener(panelAnimal.flechaIzq);
        this.addMouseMotionListener(panelAnimal.flechaDer);

        this.addMouseListener(panelAnimal);

        // Añadimos listeners de Panel Comida

        this.addMouseListener(panelComida.flechaDer);
        this.addMouseListener(panelComida.flechaIzq);

        this.addMouseMotionListener(panelComida.flechaIzq);
        this.addMouseMotionListener(panelComida.flechaDer);

        this.addMouseListener(panelComida);

    }

    public static EspeciesEnum getSelectedAnimal() {return panelAnimal.getSelectedAnimal();}

    public static HabitatEnum getSelectedHabitat() {return panelHabitat.getSelectedHabitat();}

    public static ComidaEnum getSelectedFood() {return panelComida.getSelectedComida();}

    public void setPanelAlertasMessage(String message) {
        this.panelAlertas.changeText(message);
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.drawImage(fondo,0,0,IMG_WIDTH,IMG_HEIGHT,this);
        panelAnimal.draw(g,this);
        panelComida.draw(g,this);
        panelHabitat.draw(g,this);
        panelAlertas.draw(g,this);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        draw(g);
    }

    @Override
    public void contextualUpdate(SubPanel subPanel) {
        if (subPanel == panelAnimal) vistaPrincipal.setCursor(ANADIR_ANIMAL);
        else if (subPanel == panelComida) vistaPrincipal.setCursor(ANADIR_COMIDA);
        else if (subPanel == panelHabitat) vistaPrincipal.setCursor(ANADIR_HABITAT);
    }
}

