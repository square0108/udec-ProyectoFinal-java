package Vista;

import Model.Enumerations.AnimalEnum;
import Model.Especies.Animal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/*
TODO: Sistema de movimiento / animaciones Animales
TODO: hacer mas escalable las skins
 */
// Implements Drawable despues
public class VistaAnimal implements ActionListener {
    final private int animationSize = 250;
    private int state; // Es por ahora nomas, tiene que cambiar por enum
    private int currentFrame;
    private int cantFrames;
    final private Animal animal;
    private int animalSize;
    private int xVelocity = 1;
    private int yVelocity = 1;
    private BufferedImage texture;
    private Timer timer;
    private int radiousWalk;
    private Point position;
    private Point trackingPoint;
    public VistaAnimal(Animal animal){
        // prueba
        cantFrames = 4;
        state = 1;
        currentFrame = 0;
        //----
        this.animal = animal;
        animalSize = 250;

        setTexture();
        radiousWalk = 100;
        trackingPoint = new Point(10,10);
        position = new Point(10,10);
        timer = new Timer(250,this);
        timer.start();
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
        int actualX = (int)(habitatPosition.getX() + position.getX());
        int actualY = (int)(habitatPosition.getY() + position.getY());

        g.drawImage(texture.getSubimage(currentFrame*animationSize, state * animationSize,animationSize,animationSize)
                ,actualX,actualY,animalSize,animalSize,imageObserver);
    }

    /**
     * Actualiza la posicion del animal con respecto al habitat
     * @param habitatWidth Ancho del habitat
     * @param habitatHeight Alto del habitat
     */
    public void updatePosition(int habitatWidth, int habitatHeight){

        switch (this.state){
            case 0: // Quieto
                System.out.println("Esta quieto!!!!");
                break;
            case 1: // Caminar
                // Primero asignamos Un punto para seguir ...
                if (position.distance(trackingPoint) < 1) {
                    do {
                        trackingPoint.setLocation(
                                (int) (Math.random() * (habitatWidth - animalSize)),
                                (int) (Math.random() * (habitatHeight - animalSize))
                        );
                    } while (
                            (trackingPoint.getX() < 0 || trackingPoint.getX() > habitatWidth - animalSize) &&
                                    (trackingPoint.getY() < 0 || trackingPoint.getY() > habitatHeight - animalSize)
                    );
                } else {
                    double deltaX = trackingPoint.getX() - position.getX();
                    double deltaY = trackingPoint.getY() - position.getY();
                    double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

                    double ratio = xVelocity / distance;
                    double moveX = ratio * deltaX;
                    double moveY = ratio * deltaY;

                    position.setLocation(Math.round(position.getX() + moveX), Math.round(position.getY() + moveY));
                }
                break;
            case 2: // Comer
                // SE queda quieto y come ???
                break;
            case 3:
                break; // Morir
            default:
                System.out.println("Se metio a default¿¿??, en AnimalVista");
                break;

        }
        /*
        if((int)position.getX()<0 || habitatWidth-50<(int)position.getX()){
            xVelocity = xVelocity * -1;
        }
        if((int)position.getY()<0 || habitatHeight-50<(int)position.getY()){
            yVelocity = yVelocity * -1;
        }
        position.setLocation(position.getX() + xVelocity, position.getY()+ yVelocity);*/
    }

    /**
     * Funcion usada por VistaAnimal, para configurar la textura que se usara en este.
     */
    private void setTexture(){
        String texture_path = switch (AnimalEnum.classToEnum(this.animal)) {
            case ELEFANTE -> "src/main/java/resources/skins/elephant.png";
            case LEON -> "src/main/java/resources/skins/animation_tile.png";
            case JIRAFA -> "src/main/java/resources/skins/animation_tile.png";
            default -> "src/main/java/resources/skins/animation_tile.png";
        };

        try {
            this.texture = ImageIO.read(new File(texture_path));
        } catch (IOException e) {
            System.out.println("TEXTURA NO ENCONTRADA!!!!! (VistaAnimal)");
        }
    }
    public void changeState(){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        currentFrame +=1;
        if (cantFrames-1 < currentFrame){
            currentFrame = 0;
        }
    }
}
