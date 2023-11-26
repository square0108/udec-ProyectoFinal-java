package Vista.Menu;

import Model.Enumerations.AnimalEnum;
import Model.Enumerations.BotonesEnum;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class BotonFlecha{
    final private int IMG_WIDTH = 50;
    final private int IMG_HEIGHT = 50;
    BufferedImage texture;
    private Point position;

    /**
     *
     * @param position posición de la esquina del panel
     * @param botonesEnum tipo de Flecha
     */
    public BotonFlecha(Point position,BotonesEnum botonesEnum){
        this.position = position;
        setTexture(botonesEnum);
    }

    /**
     *
     * @param g
     * @param imageObserver
     * @param x posicion dentro del limite
     * @param y posicion dentro del limite
     */
    public void draw(Graphics g, ImageObserver imageObserver, int x, int y){
        g.drawImage(texture,(int)(position.getX() + x),(int)(position.getY() + y),
                IMG_WIDTH,IMG_HEIGHT,imageObserver);
    }
    private void setTexture(BotonesEnum botonesEnum){
        // Se podria mejorar esto si Animales guardaces su path "animal.png"

        String texture_path = "src/main/java/resources/icons/" + botonesEnum.getDireccionImagen();
        try {
            this.texture = ImageIO.read(new File(texture_path));
        } catch (IOException e) {
            System.out.println("TEXTURA NO ENCONTRADA!!!!! (VistaAnimal)");
        }
    }

}
