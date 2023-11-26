package Vista.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Es un panel el cual muestra el menu de opciones
 */
public class VistaMenu extends JPanel {
    final private int IMG_WIDTH = 400;
    final private int IMG_HEIGHT = 900;
    private Point imageCorner;
    private PanelSeleccionAnimal panelAnimal;
    public VistaMenu(){
        this.setPreferredSize(new Dimension(IMG_WIDTH,IMG_HEIGHT));
        imageCorner = new Point(0,0);
        panelAnimal = new PanelSeleccionAnimal(10,10);
    }
    public void draw(Graphics g){
        g.setColor(Color.red);

        panelAnimal.draw(g,this);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        draw(g);
    }
}
