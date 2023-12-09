package Vista;

import Controller.ZooController;
import Vista.Enumerations.EnumCursor;
import Vista.Menu.VistaMenu;
import Vista.Zoo.VistaParque;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Vista principal es una clase que extiende el JFrame, desde aqui se muestra toda la parte visual del programa.
 */
public class VistaPrincipal extends JFrame {
    private final VistaParque zoo;
    private final VistaMenu menu;
    private EnumCursor cursorState;
    /* pondre una referencia a Controller, no se si sera necesaria.. */
    private ZooController parentController;
    public VistaPrincipal(ZooController parentController) {
        super("El Magnífico Zoológico de la Universidad de Concepción");
        zoo = new VistaParque(this);
        menu = new VistaMenu(this);
        this.parentController = parentController;
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
        this.cursorState = EnumCursor.DEFAULT;
    }

    /**
     * Actualizamos todas las vistas (repaint)
     */
    public void update() {
        this.zoo.update();
        this.menu.repaint();
        this.repaint();
    }

    public VistaParque getVistaParque(){
        return zoo;
    }

    public VistaMenu getMenu() {return menu;}

    public EnumCursor getCursorState() {return this.cursorState;}

    /**
     * Cambiamos la skin del cursor.
     * @param tipo Enumerador Cursor, aquí se selecciona el modo en el que esta el cursor.
     */
    public void setCursor(EnumCursor tipo){
        String cursor_path = "src/main/java/resources/icons/" + tipo.getImagePath();
        BufferedImage image;
        try {
            image = ImageIO.read(new File(cursor_path));
            setCursor(Toolkit.getDefaultToolkit().createCustomCursor(image.getScaledInstance(50,50,0),
                    new Point(0,0), "Custom Cursor"));
            cursorState = tipo;
        } catch (IOException e) {
            System.out.println("NO CARGO LA IMAGEN DEL CURSOR AAAAA");
        }
    }
}
