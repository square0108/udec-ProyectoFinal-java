package Vista;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    VistaParque zoo;

    public Window(){
        zoo = new VistaParque();
        zoo.addHabitat(10,10);

        zoo.addHabitat(600,40);

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(zoo,BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
