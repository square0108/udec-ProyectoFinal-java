package Model;

import Model.EntornosHabitat.Sabana;
import Model.Especies.*;

public class Testing {
    public static void main(String[] args) {
        Leon manuel = new Leon();
        System.out.println(CompatibleChecker.isCompatible(new Elefante(), new Jirafa()));
        System.out.println(CompatibleChecker.isCompatible(new Elefante(), new Leon()));

        Sabana sabana1 = new Sabana();
        System.out.println(sabana1.isEmpty());
        sabana1.addAnimal(manuel);
        System.out.println(sabana1.isEmpty());
    }
}
