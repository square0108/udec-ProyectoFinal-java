package Controller;

import Model.*;
import Model.Enumerations.ComidaEnum;
import Model.Enumerations.EspeciesEnum;
import Model.Animal;
import Model.Enumerations.HabitatEnum;
import Model.Exceptions.AlimentoLimiteException;
import Model.Exceptions.AnimalNoExisteException;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatLlenoException;
import Model.Factories.ZooItemFactory;
import Vista.*;
import Vista.Menu.PanelAlertas;
import Vista.Menu.VistaMenu;
import Vista.Zoo.VistaAnimal;

/**
 * esta clase es un Mediator,
 * no deberia crear ningun metodo con logica propia, cualquier metodo en esta clase debe ser una llamada a un metodo ya
 * existente.
 */
public class ZooController {
    protected static VistaPrincipal GUI;
    protected static Habitat[] zooHabitats;
    protected static ZooItemFactory instanceFactory;
    public final int FRAMETIME_MS = 17; /* frecuencia con la cual LogicThread hace thread.sleep */
    // TODO: Aquí quizas poner el modo en el que se esta: Habitat, Animal, Comida. Para poder hacer o no hacer acciones.

    public ZooController() {
        zooHabitats = new Habitat[9];
        GUI = new VistaPrincipal(this);
        instanceFactory = new ZooItemFactory();
        new Thread(new UpdaterThread(this)).start();
    }

    public static void nuevoHabitat(HabitatEnum tipoHabitat, int habitatIndex) {
            Habitat nuevoHabitat = instanceFactory.newHabitatInstance(tipoHabitat);
            zooHabitats[habitatIndex] = nuevoHabitat;
            GUI.getVistaParque().addHabitat(nuevoHabitat, habitatIndex);
    }

    public static void nuevoAnimal(EspeciesEnum tipoAnimal, int habitatIndex) throws AnimalNoExisteException {
        try {
            Animal nuevoAnimal = instanceFactory.newAnimalInstance(tipoAnimal);
            zooHabitats[habitatIndex].addAnimal(nuevoAnimal);
            GUI.getVistaParque().addAnimal(habitatIndex, new VistaAnimal(nuevoAnimal));
            PanelAlertas.changeText("Se añadió un nuevo animal: " + VistaMenu.getSelectedAnimal().toString().toLowerCase());
            System.out.println("Poblacion del habitat N°" + habitatIndex + ": " + zooHabitats[habitatIndex].getCurrentPop());
        }
        catch (HabitatLlenoException e) {
            PanelAlertas.changeText("El habitat N°" + habitatIndex + " está lleno");
        }
        catch (AnimalesIncompatiblesException e) {
            PanelAlertas.changeText(tipoAnimal.name() + "tiene conflicto con uno de los animales!");
        }
    }

    public static void addAlimento(ComidaEnum tipoComida, int habitatIndex) {
        try {
            Alimento nuevoAlimento = instanceFactory.newAlimentoInstance(tipoComida);
            zooHabitats[habitatIndex].addAlimento(nuevoAlimento);
        } catch (AlimentoLimiteException e) {
            PanelAlertas.changeText("Este habitat (" + habitatIndex + ") tiene alimento máximo");
        }
    }

    public void updateHabitatsModelView() {
        for (Habitat habitat : zooHabitats) {
            if (habitat != null) habitat.update();
        }
        GUI.update();
    }

}