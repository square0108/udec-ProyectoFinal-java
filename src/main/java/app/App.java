package app;
import Controller.ZooController;
import Model.EntornosHabitat.Sabana;
import Model.Enumerations.EspeciesEnum;
import Model.Especies.*;
import Model.Exceptions.AnimalNoExisteException;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatIncompatibleException;
import Model.Exceptions.HabitatLlenoException;

public class App {
    public static void main(String[] args) throws HabitatLlenoException, AnimalesIncompatiblesException, HabitatIncompatibleException, AnimalNoExisteException {
        ZooController controller = new ZooController();
        controller.nuevoHabitat(new Sabana(), 10, 10);
        controller.nuevoAnimal(EspeciesEnum.ELEFANTE, 0);
        controller.nuevoAnimal(EspeciesEnum.ELEFANTE, 0);
        controller.nuevoAnimal(EspeciesEnum.ELEFANTE, 0);
        controller.nuevoAnimal(EspeciesEnum.ELEFANTE, 0);
        controller.nuevoAnimal(EspeciesEnum.ELEFANTE, 0);
    }
}