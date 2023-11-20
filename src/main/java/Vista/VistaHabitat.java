package Vista;

import java.awt.*;

// Implements Drawable despues
public class VistaHabitat {
    private Point position;
    private int IMG_WIDTH;
    private int IMG_HEIGHT;
    private VistaAnimal animal;
    public VistaHabitat(int xPos, int yPos){
        animal = new VistaAnimal();
        IMG_WIDTH = 200;
        IMG_HEIGHT = 200;
        position = new Point(xPos,yPos);
    }
    /**
     * @param parquePosition Posición relativa al habitat
     */
    public void draw(Graphics g,Point parquePosition){
        g.setColor(Color.BLUE);

        g.fillRect((int) (position.getX() + parquePosition.getX()),
                (int)(position.getY() + parquePosition.getY()),IMG_WIDTH,IMG_HEIGHT);

        // Aquí se deberian dibujar Los animalés
        g.setColor(Color.RED);
        animal.draw(g,new Point((int) (position.getX() + parquePosition.getX()), (int)(position.getY() + parquePosition.getY()))
                ,IMG_WIDTH,IMG_HEIGHT);
    }
    public int getWidth(){
        return IMG_WIDTH;
    }
    public int getHeight(){
        return IMG_HEIGHT;
    }
}
