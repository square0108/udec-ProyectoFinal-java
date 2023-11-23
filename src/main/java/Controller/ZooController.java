package Controller;

import Model.*;
import Model.Enumerations.HabitatEnum;
import Model.Especies.Animal;
import Vista.*;

import javax.swing.*;
import java.util.ArrayList;

/**
 * esta clase es un Mediator,
 * no deberia crear ningun metodo con logica propia, cualquier metodo en esta clase debe ser una llamada a un metodo ya
 * existente.
 */
public class ZooController {
    protected JPanel GUI; // PLACEHOLDER
    protected ArrayList<Habitat> zooHabitats;
    protected LogicThread logicThread;

    public ZooController() {
        this.zooHabitats = new ArrayList<>();
        this.logicThread = new LogicThread(zooHabitats);

        Thread mainThread = new Thread(logicThread);
        mainThread.start();
    }
    public void nuevoHabitat(Habitat tipo) {
        zooHabitats.add(tipo);
    }
    public void addAnimal(Animal animal, int habitatIndex) {
        zooHabitats.get(habitatIndex).addAnimal(animal);
    }
}
