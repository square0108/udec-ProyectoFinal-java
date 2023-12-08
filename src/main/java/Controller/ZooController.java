package Controller;

import Model.*;
import Model.Enumerations.EspeciesEnum;
import Model.Animal;
import Model.Enumerations.HabitatEnum;
import Model.Exceptions.AnimalNoExisteException;
import Model.Exceptions.AnimalesIncompatiblesException;
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
        zooHabitats = new ArrayList<>();
        coordshabitats = new ArrayList<>();
        GUI = new VistaPrincipal(this);
        setHabitatCoordinates();
        new Thread(new UpdaterThread(this)).start();
    }
    public static void nuevoHabitat(HabitatEnum habitat, int coordX, int coordY) {
        // TODO: Aqui habria que poner restriccion con modo de cursor
        if (GUI.getCursorState() != EnumCursor.ANADIR_HABITAT) return;
        for(int i = 0; i< coordshabitats.size(); i++){
            if(coordshabitats.get(i).getX()<coordX && coordX < coordshabitats.get(i).getX() + coordshabitats.get(i).getWidth() &&
                    coordshabitats.get(i).getY()<coordY && coordY < coordshabitats.get(i).getY() + coordshabitats.get(i).getWidth() &&
                    habitatUsability[i]){

                habitatUsability[i] = false;
                Habitat newinstance = AnimalHabitatFactory.newHabitatInstance(habitat);
                System.out.println("DEBUG: Nuevo habitat agregado: " + newinstance);
                zooHabitats.add(newinstance);
                GUI.getVistaParque().addHabitat(newinstance,(int) coordshabitats.get(i).getX(),(int) coordshabitats.get(i).getY());
            }
        }
    }
    public static void nuevoAnimal(EspeciesEnum animal, int habitatIndex) throws AnimalNoExisteException {
        try {
            Animal nuevoAnimal = AnimalHabitatFactory.newAnimalInstance(animal);
            zooHabitats.get(habitatIndex).addAnimal(nuevoAnimal);
            GUI.getVistaParque().addAnimal(habitatIndex, new VistaAnimal(nuevoAnimal));

            System.out.println("Poblacion del habitat N°" + habitatIndex + ": " + zooHabitats.get(0).getCurrentPop());
        }
        catch (HabitatLlenoException e) {
            System.out.println("El habitat de indice " + habitatIndex + " se encuentra lleno.");
        }
        catch (AnimalesIncompatiblesException e) {
            System.out.println("El animal que trataste de ingresar: " + animal + ", no es compatible con alguno de los presentes!");
        }
    }
    public void updateHabitatsModelView() {
        for (Habitat habitat : zooHabitats) {
            habitat.update();
        }
        GUI.update();
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

    public static ArrayList<Rectangle> getCoordshabitats() {
        return coordshabitats;
    }

    public static boolean[] getHabitatUsability() {
        return habitatUsability;
    }
}