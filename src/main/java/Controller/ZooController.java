package Controller;

import Model.*;
import Model.Enumerations.EspeciesEnum;
import Model.Animal;
import Model.Exceptions.AnimalNoExisteException;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatIncompatibleException;
import Model.Exceptions.HabitatLlenoException;
import Model.Factories.AnimalHabitatFactory;
import Vista.*;
import Vista.Enumerations.EnumCursor;
import Vista.Zoo.VistaAnimal;

import java.awt.*;
import java.util.ArrayList;

/**
 * esta clase es un Mediator,
 * no deberia crear ningun metodo con logica propia, cualquier metodo en esta clase debe ser una llamada a un metodo ya
 * existente.
 */
public class ZooController {
    protected static VistaPrincipal GUI;
    protected static ArrayList<Habitat> zooHabitats;
    private static ArrayList<Rectangle> coordshabitats;
    private static boolean[] habitatUsability;
    public final int FRAMETIME_MS = 17; /* frecuencia con la cual LogicThread hace thread.sleep */
    // TODO: Aquí quizas poner el modo en el que se esta: Habitat, Animal, Comida. Para poder hacer o no hacer acciones.

    public ZooController() {
        this.zooHabitats = new ArrayList<>();
        this.coordshabitats = new ArrayList<>();
        this.GUI = new VistaPrincipal();
        setHabitatCoordinates();

        new Thread(new UpdaterThread(this)).start();
    }
    public static void nuevoHabitat(Habitat habitat, int coordX, int coordY) {
        // TODO: Aqui habria que poner restriccion con modo de cursor
        for(int i = 0; i< coordshabitats.size(); i++){
            if(coordshabitats.get(i).getX()<coordX && coordX < coordshabitats.get(i).getX() + coordshabitats.get(i).getWidth() &&
                    coordshabitats.get(i).getY()<coordY && coordY < coordshabitats.get(i).getY() + coordshabitats.get(i).getWidth() &&
                    habitatUsability[i]){

                habitatUsability[i] = false;
                zooHabitats.add(habitat);
                GUI.getVistaParque().addHabitat(habitat,(int) coordshabitats.get(i).getX(),(int) coordshabitats.get(i).getY());
            }
        }
    }
    public void nuevoAnimal(EspeciesEnum animal, int habitatIndex) throws HabitatLlenoException, AnimalesIncompatiblesException, HabitatIncompatibleException, AnimalNoExisteException {
        Animal nuevoAnimal = AnimalHabitatFactory.newAnimalInstance(animal);
        GUI.getVistaParque().addAnimal(habitatIndex, new VistaAnimal(nuevoAnimal));

        System.out.println(this.zooHabitats.get(0).getCurrentPop());
    }
    public void updateHabitatsModelView() {
        for (Habitat habitat : zooHabitats) {
            habitat.update();
        }
        GUI.repaint();
    }

    /**
     * Aquí se crean las coordenadas posibles para cada habitat, ya que no se pueden poner en cualquier
     * lado.
     */
    private void setHabitatCoordinates(){
        coordshabitats.add(new Rectangle(249,78,400,400));
        coordshabitats.add(new Rectangle(829,278,400,400));
        coordshabitats.add(new Rectangle(1356,78,400,400));
        coordshabitats.add(new Rectangle(1906,278,400,400));
        coordshabitats.add(new Rectangle(2306,788,400,400));
        coordshabitats.add(new Rectangle(1935,1292,400,400));
        coordshabitats.add(new Rectangle(1355,1188,400,400));
        coordshabitats.add(new Rectangle(828,1292,400,400));
        coordshabitats.add(new Rectangle(302,1188,400,400));

        habitatUsability = new boolean[]{true,true,true,true,true,true,true,true,true};
    }
    public static void changeCursor(EnumCursor tipo){
        GUI.setCursor(tipo);
        // setear dentro de controller una variable interna que muestre el estado del cursor, sirve mas que nada
        // cuando por ejemplo queremos añadir algo y no le achuntamos al habitat, que el cursor vuelva a la normalidad
        // y no deje añadir ningun animal.
    }
}