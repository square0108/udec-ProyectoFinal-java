package Vista;

import Controller.ZooController;
import Model.EntornosHabitat.Sabana;
import Model.Especies.Elefante;
import Model.Especies.Jirafa;
import Model.Especies.Leon;

public class Test {
    public static void main(String[] args) {
        Thread hilo;
        hilo = new HebraVisual();
        hilo.start();
    }
}
