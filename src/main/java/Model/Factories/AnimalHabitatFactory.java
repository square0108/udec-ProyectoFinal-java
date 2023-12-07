package Model.Factories;

import Model.Animal;
import Model.EntornosHabitat.Jungla;
import Model.Enumerations.*;
import Model.Especies.*;
import Model.Exceptions.AnimalNoExisteException;
import Model.Habitat;

public class AnimalHabitatFactory {
    public static Animal newAnimalInstance(EspeciesEnum animal) throws AnimalNoExisteException {
        return switch (animal) {
            case JIRAFA -> new Jirafa();
            case ELEFANTE -> new Elefante();
            case LEON -> new Leon();
            default -> throw new AnimalNoExisteException();
        };
    }
    public static Habitat newHabitatInstance(HabitatEnum hab) {
        return switch (hab) {
            case JUNGLA -> new Jungla();
            default -> null;
        };
    }
}
