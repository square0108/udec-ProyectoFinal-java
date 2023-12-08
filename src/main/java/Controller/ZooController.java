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
import Vista.Zoo.VistaAnimal;

/**
 * esta clase es un Mediator,
 * no deberia crear ningun metodo con logica propia, cualquier metodo en esta clase debe ser una llamada a un metodo ya
 * existente.
 */
public class ZooController {
    protected static VistaPrincipal GUI;
    protected static Habitat[] zooHabitats;
    public final int FRAMETIME_MS = 17; /* frecuencia con la cual LogicThread hace thread.sleep */
    // TODO: Aquí quizas poner el modo en el que se esta: Habitat, Animal, Comida. Para poder hacer o no hacer acciones.

    public ZooController() {
        zooHabitats = new Habitat[9];
        GUI = new VistaPrincipal(this);
        new Thread(new UpdaterThread(this)).start();
    }

    public static void nuevoHabitat(HabitatEnum tipoHabitat, int habitatIndex) {
            Habitat nuevoHabitat = AnimalHabitatFactory.newHabitatInstance(tipoHabitat);

            zooHabitats[habitatIndex] = nuevoHabitat;
            GUI.getVistaParque().addHabitat(nuevoHabitat, habitatIndex);
    }

    public static void nuevoAnimal(EspeciesEnum tipoAnimal, int habitatIndex) throws AnimalNoExisteException {
        try {
            Animal nuevoAnimal = AnimalHabitatFactory.newAnimalInstance(tipoAnimal);

            zooHabitats[habitatIndex].addAnimal(nuevoAnimal);
            GUI.getVistaParque().addAnimal(habitatIndex, new VistaAnimal(nuevoAnimal));
            System.out.println("Poblacion del habitat N°" + habitatIndex + ": " + zooHabitats[habitatIndex].getCurrentPop());
        }
        catch (HabitatLlenoException e) {
            System.out.println("El habitat de indice " + habitatIndex + " se encuentra lleno.");
        }
        catch (AnimalesIncompatiblesException e) {
            System.out.println("El animal que trataste de ingresar: " + tipoAnimal + ", no es compatible con alguno de los presentes!");
        }
    }

    public void updateHabitatsModelView() {
        for (Habitat habitat : zooHabitats) {
            if (habitat != null) habitat.update();
        }
        GUI.update();
    }

}