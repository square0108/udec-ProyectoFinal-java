package Vista;

import Model.EntornosHabitat.Sabana;
import Model.Especies.Elefante;
import Model.Especies.Jirafa;
import Vista.Enumerations.EnumCursor;
import Vista.Menu.VistaMenu;
import Vista.Zoo.VistaAnimal;
import Vista.Zoo.VistaParque;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VistaPrincipal extends JFrame implements MouseListener {

    private VistaParque zoo;
    private VistaMenu menu;
    public VistaPrincipal() {
        zoo = new VistaParque();

        menu = new VistaMenu();
        setCursor(EnumCursor.DEFAULT);

        this.setPreferredSize(new Dimension(1600,900));
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(zoo,BorderLayout.CENTER);
        this.add(menu,BorderLayout.WEST);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public VistaParque getVistaParque(){
        return zoo;
    }
    public void setCursor(EnumCursor tipo){
        String cursor_path = "src/main/java/resources/icons/" + tipo.getImagePath();
        BufferedImage image;
        try {
            image = ImageIO.read(new File(cursor_path));
            setCursor(Toolkit.getDefaultToolkit().createCustomCursor(image.getScaledInstance(50,50,0),
                    new Point(0,0), "Custom Cursor"));
        } catch (IOException e) {
            System.out.println("NO CARGO LA IMAGEN DEL CURSOR AAAAA");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setCursor(EnumCursor.DEFAULT);
        System.out.println("HOLA!!!!");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
