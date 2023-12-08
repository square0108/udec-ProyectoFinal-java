package Vista.Menu;

import Vista.VistaPrincipal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Es un panel el cual muestra el menu de opciones
 */
public class VistaMenu extends JPanel implements ActionListener {
    final private int IMG_WIDTH = 400;
    final private int IMG_HEIGHT = 900;
    private BufferedImage fondo;
    private Point imageCorner;
    private PanelSeleccionAnimal panelAnimal;
    private PanelSeleccionComida panelComida;
    private PanelSeleccionHabitat panelHabitat;
    private Timer timer;
    public VistaMenu(VistaPrincipal parentFrame){
        this.setPreferredSize(new Dimension(IMG_WIDTH,IMG_HEIGHT));
        imageCorner = new Point(0,0);

        panelHabitat = new PanelSeleccionHabitat(10,30);
        panelAnimal = new PanelSeleccionAnimal(10,260);

        panelComida = new PanelSeleccionComida(10, 490);

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

        this.timer = new Timer(50, this);
        this.timer.start();
    }
    public void draw(Graphics g){
        g.setColor(Color.red);
        g.drawImage(fondo,0,0,IMG_WIDTH,IMG_HEIGHT,this);
        panelAnimal.draw(g,this);
        panelComida.draw(g,this);
        panelHabitat.draw(g,this);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
