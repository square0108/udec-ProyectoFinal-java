package Vista;

import Model.Enumerations.HabitatEnum;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    VistaParque zoo;

    public Window(){
        zoo = new VistaParque();

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(zoo,BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
