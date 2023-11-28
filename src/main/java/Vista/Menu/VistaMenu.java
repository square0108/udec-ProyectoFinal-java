package Vista.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Es un panel el cual muestra el menu de opciones
 */
public class VistaMenu extends JPanel implements ActionListener {
    final private int IMG_WIDTH = 400;
    final private int IMG_HEIGHT = 900;
    private Point imageCorner;
    private PanelSeleccionAnimal panelAnimal;
    private PanelSeleccionComida panelComida;
    private Timer timer;
    public VistaMenu(){
        this.setPreferredSize(new Dimension(IMG_WIDTH,IMG_HEIGHT));
        imageCorner = new Point(0,0);
        panelAnimal = new PanelSeleccionAnimal(10,30);

        panelComida = new PanelSeleccionComida(10, 280);

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

        this.timer = new Timer(50, this);
        this.timer.start();
    }
    public void draw(Graphics g){
        g.setColor(Color.red);
        panelAnimal.draw(g,this);
        panelComida.draw(g,this);

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
