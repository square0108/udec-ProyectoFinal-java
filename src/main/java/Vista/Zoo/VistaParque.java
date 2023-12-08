package Vista.Zoo;

import Controller.ZooController;
import Model.EntornosHabitat.Jungla;
import Model.Enumerations.EspeciesEnum;
import Model.Exceptions.AnimalNoExisteException;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatLlenoException;
import Model.Habitat;
import Vista.Enumerations.EnumCursor;
import Vista.Menu.PanelSeleccionHabitat;
import Vista.VistaPrincipal;

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
public class VistaParque extends JPanel implements MouseListener {
    private final int PANEL_WIDTH = 1400;
    private final int PANEL_HEIGTH = 900;
    private BufferedImage fondo;
    private final int IMG_WIDTH= 2800;
    private final int IMG_HEIGTH= 1800;
    private Point imageCorner;
    private Point previousPoint;
    private final ArrayList<VistaHabitat> VistaHabitatList;
    private final VistaPrincipal vistaPrincipal;

    /**
     * Cont
     */
    public VistaParque(VistaPrincipal parentFrame){
        this.vistaPrincipal = parentFrame;
        // Creamos habitat (cambiar más adelante)
        // Y cargamos imagen de fondo
        VistaHabitatList = new ArrayList<VistaHabitat>();
        this.addMouseListener(this);

        try {
            fondo = ImageIO.read(new File("src/main/java/resources/fondo_pasto.png"));
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


        // OJO: Este JPANEL ES PARA PINTAR EL RESTO DE COSAS, NO HAY QUE PONERLE MAS JPANELS
    }

    public void update() {
        this.repaint();
    }

    public void draw(Graphics g){
        // Dibujar fondo
        g.drawImage(fondo, (int) imageCorner.getX(), (int) imageCorner.getY(),IMG_WIDTH,IMG_HEIGTH, this);
        // Dibujamos los habitats
        if(!VistaHabitatList.isEmpty()){
            for (VistaHabitat habitatSprite : VistaHabitatList) {
                habitatSprite.draw(g, this, imageCorner);
            }
        }
        // Aquí deberiamos llamar a Draw, de distintas componentes de la cosita jajaj
        // Habitat.draw(g,xPos,yPos), cosas así
    }
    public void addHabitat(Habitat tipo, int x, int y){
        VistaHabitat habitat = new VistaHabitat(tipo,x,y, this);
        VistaHabitatList.add(habitat);
    }
    /*
    * Añade un Animal en el habitat con el Id utilizado en el metodo
    * TODO: Esto quizas deberia pedir una clase ANIMAL dentro, no un VistaAnimal
    * */
    public void addAnimal(int id, VistaAnimal animal) throws HabitatLlenoException, AnimalesIncompatiblesException {
        VistaHabitatList.get(id).addAnimalSprite(animal);
    }
    public ArrayList<VistaHabitat> getVistaHabitats(){
        return VistaHabitatList;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked VistaParque");
        // TODO: PRUEBA PARA CREAR HABITATS
        ZooController.nuevoHabitat(PanelSeleccionHabitat.getSelectedHabitat(),(int)(e.getX()-imageCorner.getX()),
                                                (int)(e.getY()-imageCorner.getY()));
        int index = 0;

        // TODO: Arreglar, solo funciona cuando los habitats los ponemos en orden, si no, falla. Quizas
        // seria mejor simplemente guardar los animales como un Array normal¿?
        for (Rectangle area : ZooController.getCoordshabitats()) {
            if (area.contains((int)(e.getX()-imageCorner.getX()), (int)(e.getY()-imageCorner.getY()))
                    && !ZooController.getHabitatUsability()[index]
                    && vistaPrincipal.getCursorState() == EnumCursor.ANADIR_ANIMAL) {
                System.out.println("colocaste habitat wuoouiuouo");
                try {
                    ZooController.nuevoAnimal(EspeciesEnum.ELEFANTE, index);
                } catch (AnimalNoExisteException ex) {
                    throw new RuntimeException(ex);
                }
            }
            index++;
        }
        index = 0;
        vistaPrincipal.setCursor(EnumCursor.DEFAULT);
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
            //TODO: BUSCAR OTRA FORMA DE HACER ESTO, el 200 corresponde al ancho del menu
            if (newX <= 0 && newX >= -(IMG_WIDTH - PANEL_WIDTH+200)) {
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
