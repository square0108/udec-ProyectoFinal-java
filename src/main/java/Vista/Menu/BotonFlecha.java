package Vista.Menu;

import Vista.Enumerations.BotonesEnum;
import Vista.Enumerations.EnumEstadosBoton;
import Vista.Interface.BotonClickListener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class BotonFlecha implements MouseListener, MouseMotionListener {
    final private int IMG_WIDTH = 240;
    final private int IMG_HEIGHT = 180;
    final private int frameWidth = 80;
    private EnumEstadosBoton state;
    private BotonClickListener botonClickListener;
    BufferedImage texture;
    //private Point position;
    //private Point posReference;
    private Point posBorders;

    /**
     * @param posReference posición referencial para de la esquina del panel
     * @param x Posición x con respecto a la referencia
     * @param y Posición y con respecto a la referencia
     * @param botonesEnum tipo de Flecha
     */

    public BotonFlecha(Point posReference,int x ,int y, BotonesEnum botonesEnum){
        this.state = EnumEstadosBoton.DEFAULT;

        // Como los estoy sumando todo el tiempo quizas seria mejor sumarlos y contar solo esa variable


        //this.posReference = posReference;
        //this.position = new Point(x,y);

        this.posBorders = new Point((int)posReference.getX()+x, (int)posReference.getY() + y);
        setTexture(botonesEnum);
    }


    /**
     *
     * @param g
     * @param imageObserver
     */
    public void draw(Graphics g, ImageObserver imageObserver){
        BufferedImage subImage;

        if(state == EnumEstadosBoton.DEFAULT){
            subImage = texture.getSubimage(0,0,frameWidth,IMG_HEIGHT);
        } else if (state == EnumEstadosBoton.HOVER) {
            subImage = texture.getSubimage(frameWidth * 1,0,frameWidth,IMG_HEIGHT);
        } else if (state == EnumEstadosBoton.CLICK) {
            subImage = texture.getSubimage(frameWidth * 2,0,frameWidth,IMG_HEIGHT);
        }else{
            subImage = texture.getSubimage(frameWidth*state.getInt(),0,frameWidth,IMG_HEIGHT);
        }
        g.drawImage(subImage,(int)(posBorders.getX()),(int)(posBorders.getY()),
                frameWidth,IMG_HEIGHT,imageObserver);
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
    public void setBotonClickListener(BotonClickListener listener) {
        this.botonClickListener = listener;
    }
    private void notifyBotonClick() {
        if (botonClickListener != null) {
            botonClickListener.onBotonClick();
        }
    }
    public int getWIDTH(){
        return frameWidth;
    }
    public int getHEIGHT(){
        return IMG_HEIGHT;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if( ( posBorders.getX() < e.getX() && posBorders.getY() < e.getY() ) &&
                e.getX()< posBorders.getX() + frameWidth && e.getY()<posBorders.getY()+IMG_HEIGHT) {
            System.out.println("HA SIDO CLICADO" + this);

        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if ((posBorders.getX() < e.getX() && posBorders.getY() < e.getY()) &&
                e.getX() < posBorders.getX() + frameWidth && e.getY() < posBorders.getY() + IMG_HEIGHT) {
            this.state = EnumEstadosBoton.CLICK;
            notifyBotonClick(); // Agrega esta línea para notificar el clic
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if ((posBorders.getX() < e.getX() && posBorders.getY() < e.getY()) &&
                e.getX() < posBorders.getX() + frameWidth && e.getY() < posBorders.getY() + IMG_HEIGHT) {
            this.state = EnumEstadosBoton.DEFAULT;
            notifyBotonClick(); // Agrega esta línea para notificar el clic
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }


    // Funciona, pero la hitbox esta media rara
    @Override
    public void mouseMoved(MouseEvent e) {
        if( ( posBorders.getX() < e.getX() && posBorders.getY() < e.getY() ) &&
        e.getX()< posBorders.getX()+frameWidth && e.getY()<posBorders.getY()+IMG_HEIGHT){
            this.state = EnumEstadosBoton.HOVER;
        }
        else{
            this.state = EnumEstadosBoton.DEFAULT;
        }
    }

    public EnumEstadosBoton getState() {
        return state;
    }
}
