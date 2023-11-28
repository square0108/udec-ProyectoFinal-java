package Vista;

import Model.EntornosHabitat.Sabana;
import Vista.Menu.VistaMenu;
import Vista.Zoo.VistaParque;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VistaPrincipal extends JFrame {

    private VistaParque zoo;
    private VistaMenu menu;
    Timer timer;
    public VistaPrincipal() {
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

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src/main/java/resources/skins/error.png"));
        } catch (IOException e) {
            System.out.println("NO CARGO LA IMAGEN");
        }

        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image.getScaledInstance(50,50,0), new Point(0,0), "Custom Cursor");
        setCursor(cursor);
    }

    public VistaParque getZoo(){
        return zoo;
    }
}
