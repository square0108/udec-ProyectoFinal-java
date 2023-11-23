package Vista;

import Controller.ZooController;
import Model.EntornosHabitat.Sabana;
import Model.Enumerations.HabitatEnum;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private VistaParque zoo;
    private ZooController zoocontroller;
    public Window(){
        zoocontroller = new ZooController();

        zoo = new VistaParque();
        zoo.addHabitat(new Sabana(), 10,10);

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(zoo,BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
