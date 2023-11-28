package Vista.Zoo;

import Model.Enumerations.HabitatEnum;
import Model.Especies.Elefante;
import Model.Habitat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// Implements Drawable despues
public class VistaHabitat {
    private Habitat habitat;
    private Point position;
    private int IMG_WIDTH;
    private int IMG_HEIGHT;
    private ArrayList<VistaAnimal> animales;
    private BufferedImage texture;
    public VistaHabitat(Habitat habitat, int xPos, int yPos){
        animales = new ArrayList<VistaAnimal>();
        // TODO: esto es para probar
        this.habitat = habitat;
        setTexture(habitat);

        IMG_WIDTH = 400;
        IMG_HEIGHT = 400;
        position = new Point(xPos,yPos);
    }
    /**
     * @param parquePosition Posición relativa al habitat
     */
    public void draw(Graphics g, ImageObserver imageObserver, Point parquePosition){
        g.setColor(Color.BLUE);

        g.drawImage(texture,(int) (position.getX() + parquePosition.getX()),
                (int)(position.getY() + parquePosition.getY()),IMG_WIDTH,IMG_HEIGHT,imageObserver);


        // Aquí se deberian dibujar Los animalés

        if(!animales.isEmpty()){
            for(int i=0;i<animales.size(); i++){
                animales.get(i).draw(g,imageObserver,new Point((int) (position.getX() + parquePosition.getX())
                                , (int)(position.getY() + parquePosition.getY())),IMG_WIDTH,IMG_HEIGHT);
            }
        }
    }
    private void setTexture(Habitat tipo){
        String texture_path;
        switch (HabitatEnum.classToEnum(tipo)){
            case SABANA:
                texture_path = "src/main/java/resources/habitats/jungla.jpg";
                break;
            default:
                texture_path = "src/main/java/resources/skins/error.png";
                break;
        }
        try {
            this.texture = ImageIO.read(new File(texture_path));
        } catch (IOException e) {
            System.out.println("TEXTURA NO ENCONTRADA!!!!! (VistaHabitat)");
        }

    }
    public void addAnimal(VistaAnimal animal){
        habitat.addAnimal(animal.getAnimal());
        animales.add(animal);
    }
    public Habitat getHabitat(){
        return this.habitat;
    }
    public int getWidth(){
        return IMG_WIDTH;
    }
    public int getHeight(){
        return IMG_HEIGHT;
    }
}
