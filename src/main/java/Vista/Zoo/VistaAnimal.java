package Vista.Zoo;

import Model.Enumerations.EspeciesEnum;
import Model.Animal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/*
TODO: Sistema de movimiento / animaciones Animales
TODO: hacer mas escalable las skins
 */

/**
 *  Clase que Representa de forma visual a la clase Animal
 */
public class VistaAnimal implements ActionListener {
    final private Animal animal;
    private int state; // Es por ahora nomas, tiene que cambiar por enum
    final private int animationSize = 250; // Pixeles que tiene en pixeles un frame de animación
    private int currentFrame;
    private int cantFrames;
    private int animalSize;
    private int Velocity = 1;
    private BufferedImage texture;
    private Timer timer;
    private Point position;
    private Point trackingPoint;
    private int timeremove;

    /**
     * Contructo de Metodo VistaAnimal, se le pone de input una instancia de algun animal, ese es el animal que sera
     * mostrado.
     * @param animal Animal a ser mostrado de forma grafica.
     */
    public VistaAnimal(Animal animal){
        // TODO prueba
        cantFrames = 4;
        state = 1;
        currentFrame = 0;
        //TODO ----
        timeremove =0;

        this.animal = animal;
        animalSize = 100;

        setTexture();
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

                    double ratio = Velocity / distance;
                    double moveX = ratio * deltaX;
                    double moveY = ratio * deltaY;

                    position.setLocation(Math.round(position.getX() + moveX), Math.round(position.getY() + moveY));
                }
                break;
            case 2: // Comer
                // SE queda quieto y come ???
                break;
            case 3: // Morir
                break;
            default:
                System.out.println("Se metio a default¿¿??, en AnimalVista");
                break;

        }
    }

    /**
     * Funcion usada por VistaAnimal, para configurar la textura que se usara en este.
     * Solo usada al ser inicializado.
     */
    private void setTexture(){
        // Se podria mejorar esto si Animales guardaces su path "animal.png"

        String texture_path = switch (EspeciesEnum.classToEnum(this.animal)) {
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

    /**
     * Metodo que cambia el estado de VistaAnimal, es utilizado para elegir el comportamiento de este
     * (Caminando, Comiendo, Muerto, Quieto)
     */
    public void changeState(){
        state = this.animal.getEstado().getNumber();
    }

    /**
     * Getter de animal dentro de VistaAnimal
     * @return referencia al animal guardado dentro
     */
    public Animal getAnimal(){
        return animal;
    }
    public int getTimeRemove(){
        return timeremove;
    }

    /**
     * Es usado para la actualización de la animación de VistaAnimal
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        changeState();
        currentFrame +=1;
        if (cantFrames-1 < currentFrame){
            currentFrame = 0;
        }

        if (state == 3 && timeremove<4) { // SI esta en estado muerto
            timeremove++;
        }
    }
}
