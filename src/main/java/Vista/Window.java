package Vista;

import Model.EntornosHabitat.Sabana;
import Vista.Menu.VistaMenu;
import Vista.Zoo.VistaParque;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private VistaParque zoo;
    private VistaMenu menu;
    public Window(){

        zoo = new VistaParque();
        zoo.addHabitat(new Sabana(), 10,10);
        menu = new VistaMenu();

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(zoo,BorderLayout.CENTER);
        this.add(menu,BorderLayout.WEST);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
