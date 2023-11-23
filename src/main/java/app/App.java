package app;
import Controller.ZooController;
import Model.*;
import Model.EntornosHabitat.Sabana;
import Model.Especies.*;

public class App {
    public static void main(String[] args) {
        ZooController controller = new ZooController();
        Sabana hab = new Sabana();
        controller.nuevoHabitat(hab);
        controller.addAnimal(new Elefante(), 0);
        controller.addAnimal(new Leon(), 0);
        controller.addAnimal(new Jirafa(), 0);
    }
}