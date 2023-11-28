package Vista.Menu;

import Vista.Enumerations.BotonesEnum;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;

public class PanelSeleccionComida {
    final private int IMG_WIDTH = 380;
    final private int IMG_HEIGHT = 200;
    protected BotonFlecha flechaDer;
    protected BotonFlecha flechaIzq;
    private Point position;
    private Point mouse_pos;
    public PanelSeleccionComida(int x, int y){
        this.position = new Point(x,y);
        flechaDer = new BotonFlecha(this.position, IMG_WIDTH-90,10, BotonesEnum.FLECHADERECHA);
        flechaIzq = new BotonFlecha(this.position, 10,10, BotonesEnum.FLECHAIZQUIERDA);
    }
    public void draw(Graphics g, ImageObserver imageObserver){
        g.setColor(Color.RED);
        g.fillRect((int) this.position.getX(), (int) this.position.getY(), IMG_WIDTH, IMG_HEIGHT);
        flechaIzq.draw(g, imageObserver);
        flechaDer.draw(g, imageObserver);
    }
}
