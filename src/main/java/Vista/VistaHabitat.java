package Vista;

import Model.Enumerations.HabitatEnum;
import Model.Especies.Animal;
import Model.Especies.Elefante;
import Model.Especies.Jirafa;
import Model.Especies.Leon;
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
    private Point position;
    private int IMG_WIDTH;
    private int IMG_HEIGHT;
    private HabitatEnum tipoHabitat;
    private ArrayList<VistaAnimal> animales;
    private VistaAnimal animal;
    private BufferedImage texture;
    public VistaHabitat(HabitatEnum tipo, int xPos, int yPos){
        animales = new ArrayList<VistaAnimal>();
        animales.add(new VistaAnimal(new Elefante()));
        setTexture();

        tipoHabitat = tipo;

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

        //g.fillRect((int) (position.getX() + parquePosition.getX()),
          //      (int)(position.getY() + parquePosition.getY()),IMG_WIDTH,IMG_HEIGHT);

        // Aquí se deberian dibujar Los animalés

        if(!animales.isEmpty()){
            for(int i=0;i<animales.size(); i++){
                animales.get(i).draw(g,imageObserver,new Point((int) (position.getX() + parquePosition.getX())
                                , (int)(position.getY() + parquePosition.getY())),IMG_WIDTH,IMG_HEIGHT);
            }
        }
    }
    private void setTexture(){
        String texture_path;

        /*
        HAY QUE ARREGLAR ESTO, Y NO SE COMOOOOOOOOOOOOOOOOOOOO, LA CONCHA DE  LA LORAAAAaaaAAaaaaAAaaAaaaaAAaaaaa
         */

        if (tipoHabitat != null){
            texture_path = switch (tipoHabitat) {
                case JUNGLA -> "src/main/java/resources/habitats/jungla.jpg";
                case DESIERTO -> "src/main/java/resources/skins/error.png";
                default -> "src/main/java/resources/skins/error.png";
            };
        }else{
            texture_path = "src/main/java/resources/skins/error.png";
            System.out.println("ERROR, EL HABITAT POSEE UN tipoHABITAT NULO");
        }

        try {
            this.texture = ImageIO.read(new File(texture_path));
        } catch (IOException e) {
            System.out.println("TEXTURA NO ENCONTRADA!!!!! (VistaAnimal)");
        }
    }
    public void addAnimal(VistaAnimal animal){
        animales.add(animal);
    }
    public int getWidth(){
        return IMG_WIDTH;
    }
    public int getHeight(){
        return IMG_HEIGHT;
    }
}
