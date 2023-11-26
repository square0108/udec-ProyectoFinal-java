package Vista.Menu;

import Model.Enumerations.BotonesEnum;

import java.awt.*;
import java.awt.image.ImageObserver;

public class PanelSeleccionAnimal {
    final private int IMG_WIDTH = 380;
    final private int IMG_HEIGHT = 200;
    private BotonFlecha flechaDer;
    private BotonFlecha flechaIzq;
    private Point position;
    public PanelSeleccionAnimal(int x, int y){
        this.position = new Point(x,y);
        flechaDer = new BotonFlecha(this.position, BotonesEnum.FLECHADERECHA);
        flechaIzq = new BotonFlecha(this.position, BotonesEnum.FLECHAIZQUIERDA);
    }
    public void draw(Graphics g, ImageObserver imageObserver){
        g.setColor(Color.BLUE);
        g.fillRect((int) this.position.getX(), (int) this.position.getY(), IMG_WIDTH, IMG_HEIGHT);
        flechaIzq.draw(g, imageObserver, 10,100);
        flechaDer.draw(g, imageObserver, IMG_WIDTH-60,100);
    }

}
