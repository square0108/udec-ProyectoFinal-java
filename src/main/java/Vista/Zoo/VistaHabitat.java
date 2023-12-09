package Vista.Zoo;

import Model.Enumerations.HabitatEnum;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatLlenoException;
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
    private final Habitat modelHabitat;
    private final Point position;
    private final int IMG_WIDTH;
    private final int IMG_HEIGHT;
    private final ArrayList<VistaAnimal> animalSprites;
    private BufferedImage texture;
    protected final VistaParque vistaParque;

    /**
     * Metodo constructor de VistaHabitat, en este se debe entregar el Habitat a ser mostrado de forma grafica y las
     * posiciones X e Y dentro del Parque del zoologico
     * @param modelHabitat Habitat a ser mostrado de forma grafica
     * @param xPos Posición del eje X con respecto al parque
     * @param yPos Posición del eje Y con respecto al parque
     */
    public VistaHabitat(Habitat modelHabitat, int xPos, int yPos, VistaParque parent){
        animalSprites = new ArrayList<VistaAnimal>();
        vistaParque = parent;
        // TODO: esto es para probar
        this.modelHabitat = modelHabitat;
        setTexture(modelHabitat);

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

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(Color.BLUE);

        g.drawImage(texture,(int) (position.getX() + parquePosition.getX()),
                (int)(position.getY() + parquePosition.getY()),IMG_WIDTH,IMG_HEIGHT,imageObserver);

        // Aquí se dibujan los
        if(!animalSprites.isEmpty()){
            for (VistaAnimal animalSprite : animalSprites) {
                animalSprite.draw(g, imageObserver, new Point((int) (position.getX() + parquePosition.getX()), (int) (position.getY() + parquePosition.getY())), IMG_WIDTH, IMG_HEIGHT);
            }
        }

        Font customFont = loadCustomFont("src/main/java/resources/Candy_Beans.otf", Font.PLAIN, 20);
        g.setFont(customFont);

        g2d.setColor(Color.WHITE);

        g2d.drawString("Cantidad de comida: " + modelHabitat.getCantidadAlimento(),
                (int)(position.getX() + parquePosition.getX()+10),
                (int)(position.getY() + parquePosition.getY())+ g.getFontMetrics().getHeight());
    }
    private Font loadCustomFont(String path, int style, int size) {
        try {
            // Carga la fuente desde el archivo .otf
            File fontFile = new File(path);
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            return baseFont.deriveFont(style, size);
        } catch (FontFormatException | IOException e) {
            System.out.println("NO SE PUDO CARGAR LA FUENTE CUSTOM");
            // Devuelve una fuente predeterminada en caso de error
            return new Font("Serif", Font.PLAIN, size);
        }
    }
    /**
     * Función usada por VistaHabitat, para configurar la textura que se usara en este.
     * Solo usada al ser inicializado.
     */
    private void setTexture(Habitat tipo){

        String texture_path = "src/main/java/resources/habitats/" +
                HabitatEnum.classToEnum(tipo).getTexturePath();
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

    public void addAnimalSprite(VistaAnimal vistaAnimal) throws HabitatLlenoException, AnimalesIncompatiblesException {
        animalSprites.add(vistaAnimal);
    }


    // GETTERS Y SETTERS
    public Habitat getModelHabitat(){
        return this.modelHabitat;
    }
    public int getWidth(){return IMG_WIDTH;
    }
    public int getHeight(){
        return IMG_HEIGHT;
    }
}
