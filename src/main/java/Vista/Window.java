package Vista;

import Model.Enumerations.HabitatEnum;

import javax.swing.*;
import java.awt.*;

import static Model.Enumerations.HabitatEnum.JUNGLA;

public class Window extends JFrame {

    VistaParque zoo;

    public Window(){
        zoo = new VistaParque();
        zoo.addHabitat(JUNGLA,10,10);

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(zoo,BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
