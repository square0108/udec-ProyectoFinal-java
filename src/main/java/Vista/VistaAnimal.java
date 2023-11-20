package Vista;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.util.HashMap;

// Implements Drawable despues
public class VistaAnimal{
    int xVelocity = 1;
    int yVelocity = 1;
    int radiousWalk;
    Point position;
    public VistaAnimal(){
        radiousWalk = 50;
        position = new Point(60,10);
    }

    /**
     * @param habitatPosition Posición relativa al habitad
     */
    public void draw(Graphics g,Point habitatPosition,int habitatWidth, int habitatHeight) {
        updatePosition(habitatWidth,habitatHeight);
        g.fillOval((int)(habitatPosition.getX() + position.getX()),(int)(habitatPosition.getY() + position.getY())
                ,50,50);
    }

    public void updatePosition(int habitatWidth, int habitatHeight){
        if((int)position.getX()<0 || habitatWidth-50<(int)position.getX()){
            xVelocity = xVelocity * -1;
        }
        if((int)position.getY()<0 || habitatHeight-50<(int)position.getY()){
            yVelocity = yVelocity * -1;
        }
        position.setLocation(position.getX() + xVelocity, position.getY()+ yVelocity);
    }

}
