package app;
import Controller.ZooController;
import Model.EntornosHabitat.Jungla;
import Model.Enumerations.EspeciesEnum;
import Model.Exceptions.AnimalNoExisteException;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatIncompatibleException;
import Model.Exceptions.HabitatLlenoException;

public class App {
    public static void main(String[] args) throws HabitatLlenoException, AnimalesIncompatiblesException, HabitatIncompatibleException, AnimalNoExisteException {
        ZooController controller = new ZooController();

        controller.nuevoHabitat(new Jungla(), 259, 88);
        controller.nuevoAnimal(EspeciesEnum.ELEFANTE, 0);
        controller.nuevoAnimal(EspeciesEnum.ELEFANTE, 0);
    }

}