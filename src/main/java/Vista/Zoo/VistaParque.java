package Vista.Zoo;

import Model.Habitat;
import Vista.ControllerTest;
import Vista.Enumerations.EnumCursor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Panel que muestra de forma grafica el conjunto de elementos dentro del Parque
 */
public class VistaParque extends JPanel implements ActionListener, MouseListener {
    private final int PANEL_WIDTH = 1400;
    private final int PANEL_HEIGTH = 900;
    private BufferedImage fondo;
    private final int IMG_WIDTH= 2356;
    private final int IMG_HEIGTH= 1118;
    private Point imageCorner;
    private Point previousPoint;
    private ArrayList<VistaHabitat> habitats;

    /**
     * Cont
     */
    public VistaParque(){
        // Creamos habitat (cambiar más adelante)
        // Y cargamos imagen de fondo
        habitats = new ArrayList<VistaHabitat>();
        this.addMouseListener(this);

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

        addMouseListener(this);


        // OJO: Este JPANEL ES PARA PINTAR EL RESTO DE COSAS, NO HAY QUE PONERLE MAS JPANELS
    }

    public void update() {
        this.repaint();
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
    public void addHabitat(Habitat tipo, int x, int y){
        VistaHabitat habitat = new VistaHabitat(tipo,x,y);
        habitats.add(habitat);
    }
    /*
    * Añade un Animal en el habitat con el Id utilizado en el metodo
    * TODO: Esto quizas deberia pedir una clase ANIMAL dentro, no un VistaAnimal
    * */
    public void addAnimal(int id, VistaAnimal animal) {
        habitats.get(id).addAnimal(animal);
    }
    public ArrayList<VistaHabitat> getVistaHabitats(){
        return habitats;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.update();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO, MANEJAR SI SE PRESIONO DENTRO DE UN LUGAR PARA PONER HABITAT, DISTINTAS COSAS
        System.out.println("LOL");
        ControllerTest.changeCursor(EnumCursor.DEFAULT);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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
