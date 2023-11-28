package app;
import Controller.ZooController;
import Model.EntornosHabitat.Sabana;
import Model.Especies.*;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatIncompatibleException;
import Model.Exceptions.HabitatLlenoException;

public class App {
    public static void main(String[] args) throws HabitatLlenoException, AnimalesIncompatiblesException, HabitatIncompatibleException {
        ZooController controller = new ZooController();
        Sabana hab = new Sabana();
        controller.nuevoHabitat(hab);
        controller.addAnimal(new Elefante(), 0);
        controller.addAnimal(new Leon(), 0);
        controller.addAnimal(new Jirafa(), 0);
    }
}
