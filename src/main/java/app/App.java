package app;
import Controller.ZooController;
import Model.Exceptions.AnimalNoExisteException;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatIncompatibleException;
import Model.Exceptions.HabitatLlenoException;

/**
 * Launcher de la App, desde aquí se ejecuta la aplicación.
 */
public class App {
    public static void main(String[] args) throws HabitatLlenoException, AnimalesIncompatiblesException, HabitatIncompatibleException, AnimalNoExisteException {
    ZooController controller = new ZooController();
    }
}