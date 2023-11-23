package Vista;

import Model.Enumerations.HabitatEnum;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VistaParque extends JPanel implements ActionListener {
    private final int PANEL_WIDTH = 1280;
    private final int PANEL_HEIGTH = 720;
    private BufferedImage fondo;
    private final int IMG_WIDTH= 2356;
    private final int IMG_HEIGTH= 1118;
    private Timer timer;
    private Point imageCorner;
    private Point previousPoint;
    private ArrayList<VistaHabitat> habitats;
    public VistaParque(){
        // Creamos habitat (cambiar más adelante)
        // Y cargamos imagen de fondo
        habitats = new ArrayList<VistaHabitat>();

        try {
            fondo = ImageIO.read(new File("src/main/java/resources/fondo_pasto.jpg"));
        } catch (IOException e) {
            System.out.println("NO SE ENCUENTRA TEXTURA!!!(PARQUE)");
        }
        // Seteamos la posición inicial del fondo
        imageCorner = new Point(0,0);
        // Creamos y añadimos ClickListener
        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);
        // Creamos y añadimos DragListener
        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);
        // Configuramos el Tamaño del panel
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGTH));
        // Creamos el Timer y lo iniciamos (60 fps)
        timer = new Timer(17,this);
        timer.start();

        // OJO: Este JPANEL ES PARA PINTAR EL RESTO DE COSAS, NO HAY QUE PONERLE MAS JPANELS
    }

    public void draw(Graphics g){
        // Dibujar fondo
        g.drawImage(fondo, (int) imageCorner.getX(), (int) imageCorner.getY(),IMG_WIDTH,IMG_HEIGTH, this);
        // Dibujamos los habitats
        if(!habitats.isEmpty()){
            for(int i=0;i<habitats.size(); i++){
                habitats.get(i).draw(g,this,imageCorner);
            }
        }
        // Aquí deberiamos llamar a Draw, de distintas componentes de la cosita jajaj
        // Habitat.draw(g,xPos,yPos), cosas así
    }
    public void addHabitat(HabitatEnum tipo,int x, int y){
        VistaHabitat habitat = new VistaHabitat(tipo,x,y);
        habitats.add(habitat);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // Desde Aquí son cosas para el Mouse Drag
    private class ClickListener extends MouseAdapter{
        public void mousePressed(MouseEvent e){
            previousPoint = e.getPoint();
        }
    }
    private class DragListener extends MouseMotionAdapter{
        public void mouseDragged(MouseEvent e){

            Point currentPoint = e.getPoint();

            int newX = (int) (imageCorner.getX() + currentPoint.getX() - previousPoint.getX());
            int newY = (int) (imageCorner.getY() + currentPoint.getY() - previousPoint.getY());

            // Verificar los límites en X
            if (newX <= 0 && newX >= -(IMG_WIDTH - PANEL_WIDTH)) {
                imageCorner.setLocation(newX, imageCorner.getY());
            }

            // Verificar los límites en Y
            if (newY <= 0 && newY >= -(IMG_HEIGTH - PANEL_HEIGTH)) {
                imageCorner.setLocation(imageCorner.getX(), newY);
            }

            previousPoint = currentPoint;
        }
    }

}
