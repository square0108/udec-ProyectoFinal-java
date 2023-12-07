package Controller;

import Model.*;
import Model.Enumerations.EspeciesEnum;
import Model.Animal;
import Model.Exceptions.AnimalNoExisteException;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatLlenoException;
import Model.Factories.AnimalHabitatFactory;
import Vista.*;
import Vista.Zoo.VistaAnimal;

import java.util.ArrayList;

/**
 * esta clase es un Mediator,
 * no deberia crear ningun metodo con logica propia, cualquier metodo en esta clase debe ser una llamada a un metodo ya
 * existente.
 */
public class ZooController {
    protected VistaPrincipal GUI;
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
    public void nuevoAnimal(EspeciesEnum animal, int habitatIndex) throws AnimalNoExisteException {
        try {
            Animal nuevoAnimal = AnimalHabitatFactory.newAnimalInstance(animal);
            GUI.getVistaParque().addAnimal(habitatIndex, new VistaAnimal(nuevoAnimal));

            System.out.println(this.zooHabitats.get(0).getCurrentPop());
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
        GUI.repaint();
    }
}
