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

/**
 *  Clase que Representa de forma visual a la clase Habitat
 */
public class VistaHabitat {
    /* TODO: sera necesario que VistaHabitat agregue animales directamente a model.Habitat? -martin
    *   si se me olvida que quise decir con esto mañana, me corto tres cuartos de coco */
    private Habitat habitat;
    private Point position;
    private int IMG_WIDTH;
    private int IMG_HEIGHT;
    private ArrayList<VistaAnimal> animales;
    private BufferedImage texture;

    /**
     * Metodo constructor de VistaHabitat, en este se debe entregar el Habitat a ser mostrado de forma grafica y las
     * posiciones X e Y dentro del Parque del zoologico
     * @param habitat Habitat a ser mostrado de forma grafica
     * @param xPos Posición del eje X con respecto al parque
     * @param yPos Posición del eje Y con respecto al parque
     */
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
     * Dibuja en pantalla el Habitat, la posición es relativa al parque. Este siempre va dentro de la funcion draw
     * de VistaParque.
     * @param g Graphics de paint en VistaParque
     * @param imageObserver ImageObserver de VistaParque
     * @param parquePosition Posición relativa al habitat
     */
    public void draw(Graphics g, ImageObserver imageObserver, Point parquePosition){
        g.setColor(Color.BLUE);

        g.drawImage(texture,(int) (position.getX() + parquePosition.getX()),
                (int)(position.getY() + parquePosition.getY()),IMG_WIDTH,IMG_HEIGHT,imageObserver);

        // remover animales
        this.removeDeadAnimals();

        // Aquí se dibujan los
        if(!animales.isEmpty()){
            for(int i=0;i<animales.size(); i++){
                animales.get(i).draw(g,imageObserver,new Point((int) (position.getX() + parquePosition.getX())
                                , (int)(position.getY() + parquePosition.getY())),IMG_WIDTH,IMG_HEIGHT);
            }
        }
    }
    /**
     * Función usada por VistaHabitat, para configurar la textura que se usara en este.
     * Solo usada al ser inicializado.
     */
    private void setTexture(Habitat tipo){
        // TODO: REFACTOR DE ESTO
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

    /**
     * Añade un animal vista a la lista de animales de la intancia de Habitat dentro de VistaHabitat  y ademas
     * Crea añade un Habitat dentro de el array de VistaAnimal.
     * @param vistaAnimal Animal a ser añadido a VistaHabitat, debe ser del tipo VistaAnimal
     */
    public void addAnimal(VistaAnimal vistaAnimal){
        if(!habitat.isFull()){
            habitat.addAnimal(vistaAnimal.getAnimal());
            animales.add(vistaAnimal);
        }
        else{
            System.out.println("El habitat esta lleno!!!!");
        }

    }
    // GETTERS Y SETTERS
    public Habitat getHabitat(){
        return this.habitat;
    }
    public int getWidth(){
        return IMG_WIDTH;
    }
    public int getHeight(){
        return IMG_HEIGHT;
    }

    /**
     * 1. Ve si el animal en Array animales (VistaAnimal) y el array en habitat (Habitat) son iguales
     * 2. Si es así ve si el vista animal tiene El timer de muerto hasta el 9, si es así lo elimina.
     * TODO: igualmente quizas revisar que para si aelimina el animal, como adquiere su estado.
     */
    public void removeDeadAnimals(){
        // Medio confuso, pero creo que se entiende
        for(int i=0; i<habitat.getArrayAnimales().size(); i++){
            for(int j=0; j<animales.size();j++){
                if(( habitat.getArrayAnimales().get(i) == animales.get(j).getAnimal() ) &&
                        animales.get(j).getTimeRemove()>3){

                    System.out.println("Esta removiendo el animal");
                    habitat.getArrayAnimales().remove(animales.get(j).getAnimal());
                    animales.remove(animales.get(j));

                }
            }
        }
    }
}
