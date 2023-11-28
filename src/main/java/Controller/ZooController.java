package Controller;

import Model.*;
import Model.Especies.Animal;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatIncompatibleException;
import Model.Exceptions.HabitatLlenoException;
import Vista.*;

import javax.swing.*;
import java.util.ArrayList;

/**
 * esta clase es un Mediator,
 * no deberia crear ningun metodo con logica propia, cualquier metodo en esta clase debe ser una llamada a un metodo ya
 * existente.
 */
public class ZooController {
    protected JFrame GUI; // PLACEHOLDER
    protected ArrayList<Habitat> zooHabitats;
    protected LogicThread logicThread;
    public static final int LOGIC_TICKRATE_MS = 1000; /* frecuencia con la cual LogicThread hace thread.sleep */

    public ZooController() {
        this.zooHabitats = new ArrayList<>();
        this.logicThread = new LogicThread(this);

        Thread mainThread = new Thread(logicThread);
        mainThread.start();
    }
    public void nuevoHabitat(Habitat tipo) {
        zooHabitats.add(tipo);
    }
    public void addAnimal(Animal animal, int habitatIndex) throws HabitatLlenoException, AnimalesIncompatiblesException, HabitatIncompatibleException {
        zooHabitats.get(habitatIndex).addAnimal(animal);
    }
    public void actualizarHambreTodos() {
        for (Habitat habitat : zooHabitats) {
            habitat.actualizarHambreAnimales();
        }
    }
    public void removerTodosLosMuertos() {
        for (Habitat habitat : zooHabitats) {
            habitat.removerAnimalesMuertos();
        }
    }
    public void simularMovimiento() {
        for (Habitat habitat : zooHabitats) {
            habitat.intentarMoverAnimales();
        }
    }
}
