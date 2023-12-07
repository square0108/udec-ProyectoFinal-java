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

import java.util.ArrayList;

/**
 * esta clase es un Mediator,
 * no deberia crear ningun metodo con logica propia, cualquier metodo en esta clase debe ser una llamada a un metodo ya
 * existente.
 */
public class ZooController {
    protected static VistaPrincipal GUI;
    protected ArrayList<Habitat> zooHabitats;
    public final int FRAMETIME_MS = 17; /* frecuencia con la cual LogicThread hace thread.sleep */

    public ZooController() {
        this.zooHabitats = new ArrayList<>();
        this.GUI = new VistaPrincipal();

        new Thread(new UpdaterThread(this)).start();
    }
    public void nuevoHabitat(Habitat habitat, int coordX, int coordY) {
        zooHabitats.add(habitat);
        GUI.getVistaParque().addHabitat(habitat, coordX, coordY);
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
    public static void changeCursor(EnumCursor tipo){
        GUI.setCursor(tipo);
        // setear dentro de controller una variable interna que muestre el estado del cursor, sirve mas que nada
        // cuando por ejemplo queremos añadir algo y no le achuntamos al habitat, que el cursor vuelva a la normalidad
        // y no deje añadir ningun animal.
    }
}
