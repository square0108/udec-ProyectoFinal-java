package Vista.Zoo;

import Controller.ZooController;
import Model.Enumerations.EstadosEnum;
import Model.Exceptions.AnimalNoExisteException;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatLlenoException;
import Model.Habitat;
import Vista.Enumerations.EnumCursor;
import Vista.Menu.VistaMenu;
import Vista.VistaPrincipal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Panel que muestra de forma grafica el conjunto de elementos dentro del Parque.
 */
public class VistaParque extends JPanel implements MouseListener {
    private final int PANEL_WIDTH = 1200;
    private final int PANEL_HEIGTH = 900;
    private BufferedImage fondo;
    private final int IMG_WIDTH= 2800;
    private final int IMG_HEIGTH= 1800;
    private Point imageCorner;
    private Point previousPoint;
    private final VistaHabitat[] vistaHabitatList;
    private static Rectangle[] habitatCoords;
    private static boolean[] habitatUsability;
    private final VistaPrincipal vistaPrincipal;

    public VistaParque(VistaPrincipal parentFrame){

        this.vistaPrincipal = parentFrame;

        vistaHabitatList = new VistaHabitat[9];
        habitatCoords = new Rectangle[9];
        this.addMouseListener(this);

        try {
            fondo = ImageIO.read(new File("src/main/java/resources/fondo_pasto.png"));
        } catch (IOException e) {
            System.out.println("NO SE ENCUENTRA TEXTURA!!!(PARQUE)");
        }
        // Slots en el panel donde es posible colocar un habitat nuevo, no es posible en ninguna otra región
        habitatCoords[0] = (new Rectangle(249,78,400,400));
        habitatCoords[1] = (new Rectangle(829,278,400,400));
        habitatCoords[2] = (new Rectangle(1356,78,400,400));
        habitatCoords[3] = (new Rectangle(1906,278,400,400));
        habitatCoords[4] = (new Rectangle(2306,788,400,400));
        habitatCoords[5] = (new Rectangle(1935,1292,400,400));
        habitatCoords[6] = (new Rectangle(1355,1188,400,400));
        habitatCoords[7] = (new Rectangle(828,1292,400,400));
        habitatCoords[8] = (new Rectangle(302,1188,400,400));

        habitatUsability = new boolean[]{true,true,true,true,true,true,true,true,true};

        // Seteamos la posición inicial del fondo
        imageCorner = new Point(0,0);
        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);
        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGTH));
    }

    /**
     * Volvemos a pintar completamente la vista del parque.
     */
    public void update() {
        this.repaint();
    }

    public void draw(Graphics g){
        // Dibujar fondo
        g.drawImage(fondo, (int) imageCorner.getX(), (int) imageCorner.getY(),IMG_WIDTH,IMG_HEIGTH, this);
        // Dibujamos los habitats
        if(!habitatListIsEmpty()){
            for (VistaHabitat vistaHabitat : vistaHabitatList) {
                if (vistaHabitat != null) {
                    vistaHabitat.draw(g, this, imageCorner);
                }
            }
        }
        // Aquí deberiamos llamar a Draw, de distintas componentes de la cosita jajaj
        // Habitat.draw(g,xPos,yPos), cosas así
    }
    public void addHabitat(Habitat tipo, int habitatIndex){
        VistaHabitat habitat = new VistaHabitat(tipo,(int)habitatCoords[habitatIndex].getX(),(int)habitatCoords[habitatIndex].getY(), this);
        vistaHabitatList[habitatIndex] = habitat;
    }

    /**
     * Añade un Animal en el habitat con indice index.
     * @param index
     * @param animal
     * @throws HabitatLlenoException
     * @throws AnimalesIncompatiblesException
     */
    public void addAnimal(int index, VistaAnimal animal) throws HabitatLlenoException, AnimalesIncompatiblesException {
        vistaHabitatList[index].addAnimalSprite(animal);
    }

    /**
     * Verifica si no hay habitats construidos en el parque.
     * @return
     */
    private boolean habitatListIsEmpty() {
        for (VistaHabitat vistaHabitat : vistaHabitatList) {
            if (vistaHabitat != null) return false;
        }
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

    }

    /**
     * El click del mouse hace cosas dependiendo del modo de este. Las acciones segun los modos son las siguientes:
     * 1. Añadir habitat: crea un habitat si el slot esta vacio, si no, no hace nada.
     * 2. Añadir animal: si se hace click sobre un habitat añade el tipo de animal seleccionado en el menu.
     * 3. Añadir comida: agrega uno de comida al habitat seleccionado.
     * 4. Default: Si hay animales muertos en un habitat, remueves las tumbas del habitat seleccionado.
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        EnumCursor cursorState = vistaPrincipal.getCursorState();
        switch(cursorState) {
            case ANADIR_HABITAT:
                for (int i = 0; i < habitatCoords.length; i++) {
                    if (habitatCoords[i].contains((int)(e.getX()-imageCorner.getX()),(int)(e.getY()-imageCorner.getY()))
                            && habitatUsability[i]) {
                        ZooController.nuevoHabitat(VistaMenu.getSelectedHabitat(), i);
                        habitatUsability[i] = false;
                    }
                }
                break;
            case ANADIR_ANIMAL:
                // TODO: Arreglar, solo funciona cuando los habitats los ponemos en orden, si no, falla. Quizas
                for (int i = 0; i < habitatCoords.length; i++) {
                    if (habitatCoords[i].contains((int) (e.getX() - imageCorner.getX()), (int) (e.getY() - imageCorner.getY()))
                            && !habitatUsability[i]) {
                        try {
                            ZooController.nuevoAnimal(VistaMenu.getSelectedAnimal(), i);
                        } catch (AnimalNoExisteException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                break;
            case ANADIR_COMIDA:
                for (int i = 0; i < habitatCoords.length; i++) {
                    if (habitatCoords[i].contains((int) (e.getX() - imageCorner.getX()), (int) (e.getY() - imageCorner.getY())) && !habitatUsability[i]) {
                        ZooController.addAlimento(VistaMenu.getSelectedFood(), i);
                    }
                }
                break;
            case DEFAULT:
                for (int i = 0; i < habitatCoords.length; i++) {
                    if (habitatCoords[i].contains((int) (e.getX() - imageCorner.getX()), (int) (e.getY() - imageCorner.getY()))
                            && !habitatUsability[i]) {

                        for(int j=0; j<vistaHabitatList[i].getAnimalSprites().size(); j++){

                            if(vistaHabitatList[i].getAnimalSprites().get(j).getAnimal().getEstado() ==
                            EstadosEnum.MUERTO){
                                vistaHabitatList[i].getAnimalSprites().remove(j);
                            }
                        }

                    }
                }
            default:
                break;

        }

        vistaPrincipal.setCursor(EnumCursor.DEFAULT);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * Clase que se usa para poder hacer drag and click en el mapa
     */
    private class ClickListener extends MouseAdapter{
        public void mousePressed(MouseEvent e){
            previousPoint = e.getPoint();
        }
    }

    /**
     * Clase que se usa para poder hacer drag and click en el mapa
     */
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
