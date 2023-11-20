package Model;

import Model.Enumerations.AnimalEnum;
import Model.Especies.Animal;

public class CompatibleChecker {
    public static boolean isCompatible(Animal animal1, Animal animal2) {
        AnimalEnum[] List = animal1.AnimalesCompatibles();

        for (int i = 0; i < List.length; i++) {
            if (List[i] == animal2.AnimalesCompatibles()[0]) return true;
        }

        return false;
    }
}
