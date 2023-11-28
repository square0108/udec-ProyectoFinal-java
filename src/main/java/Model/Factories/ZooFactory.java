package Model.Factories;

import Model.Enumerations.*;
import Model.Especies.*;
import Model.EntornosHabitat.*;
import Model.Exceptions.AnimalNoExisteException;
import Model.Habitat;

public class ZooFactory {
    public Animal newAnimal(EspeciesEnum animal) throws AnimalNoExisteException {
        return switch (animal) {
            case JIRAFA -> new Jirafa();
            case ELEFANTE -> new Elefante();
            case LEON -> new Leon();
            default -> throw new AnimalNoExisteException();
        };
    }
}
