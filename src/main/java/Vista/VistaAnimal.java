package Vista;

import Model.Especies.Animal;
import Model.Especies.Elefante;
import Model.Especies.Jirafa;
import Model.Especies.Leon;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/*
TODO: Sistema de movimiento / animaciones Animales
TODO: hacer mas escalable las skins
 */
// Implements Drawable despues
public class VistaAnimal implements ActionListener {
    final private Animal animal;
    private int xVelocity = 1;
    private int yVelocity = 1;
    private BufferedImage texture;
    private Timer timer;
    int radiousWalk;
    Point position;
    public VistaAnimal(Animal animal){
        this.animal = animal;
        setTexture();
        radiousWalk = 50;
        position = new Point(60,10);
    }

    /**
     * Dibuja en pantalla La posicion del animal, relativa al habitat. Este siempre va dentro de la funcion draw
     * de habitat.
     * @param habitatPosition Posición relativa al habitad
     * @param habitatHeight Alto del Habitat
     * @param habitatWidth Ancho del Habitat
     */
    public void draw(Graphics g, ImageObserver imageObserver, Point habitatPosition, int habitatWidth, int habitatHeight) {
        updatePosition(habitatWidth,habitatHeight);
        g.drawImage(texture,(int)(habitatPosition.getX() + position.getX()),(int)(habitatPosition.getY() + position.getY())
                ,50,50,imageObserver);
    }

    /**
     * Actualiza la posicion del animal con respecto al habitat
     * @param habitatWidth Ancho del habitat
     * @param habitatHeight Alto del habitat
     */
    public void updatePosition(int habitatWidth, int habitatHeight){
        if((int)position.getX()<0 || habitatWidth-50<(int)position.getX()){
            xVelocity = xVelocity * -1;
        }
        if((int)position.getY()<0 || habitatHeight-50<(int)position.getY()){
            yVelocity = yVelocity * -1;
        }
        position.setLocation(position.getX() + xVelocity, position.getY()+ yVelocity);
    }

    /**
     * Funcion usada por VistaAnimal, para configurar la textura que se usara en este.
     */
    private void setTexture(){
        String texture_path = "src/main/java/resources/skins/error.png";
        if(animal instanceof Elefante){
          texture_path = "src/main/java/resources/skins/elephant.png"; // cambiar luego
        } else if (animal instanceof Jirafa) {
            texture_path = "";
        } else if (animal instanceof Leon) {
            texture_path = "";
        }else {
            System.out.println("ANIMAL NO TIENE INSTANCIA");
        }
        try {
            this.texture = ImageIO.read(new File(texture_path));
        } catch (IOException e) {
            System.out.println("TEXTURA NO ENCONTRADA!!!!! (VistaAnimal)");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Aquí poner codigo que actualiza la posición del animal

    }
}
