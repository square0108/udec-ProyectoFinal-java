package Model.Factories;

import Model.Alimento;
import Model.Animal;
import Model.EntornosHabitat.Acuatico;
import Model.EntornosHabitat.Artico;
import Model.EntornosHabitat.Jungla;
import Model.EntornosHabitat.Sabana;
import Model.Enumerations.*;
import Model.Especies.*;
import Model.Exceptions.AnimalNoExisteException;
import Model.Habitat;

/**
 * Factory para encapsular la creación de nuevas instancias de los tres elementos principales del zoológico: animales, hábitats y alimentos.
 */
public class ZooItemFactory {
    public Animal newAnimalInstance(EspeciesEnum animal) throws AnimalNoExisteException {
        return switch (animal) {
            case JIRAFA -> new Jirafa();
            case ELEFANTE -> new Elefante();
            case LEON -> new Leon();
            case CARPINCHO -> new Carpincho();
            case PEZ -> new Pez();
            case MONO -> new Mono();
            case POLAR -> new Polar();
            case PULPO -> new Pulpo();
            case GORILA -> new Gorila();
            case JABALI -> new Jabali();
            case JAGUAR -> new Jaguar();
            case CANGREJO -> new Cangrejo();
            case PINGUINO -> new Pinguino();
            default -> throw new AnimalNoExisteException();
        };
    }

    public Habitat newHabitatInstance(HabitatEnum hab) {
        return switch (hab) {
            case JUNGLA -> new Jungla();
            case SABANA -> new Sabana();
            case ARTICO -> new Artico();
            case ACUATICO -> new Acuatico();
            default -> null;
        };
    }

    /**
     * En un principio se pensaba tener distintos tipos de alimento, decidimos no hacer esto, pero se dejó con un enum en caso de querer implementarlo a futuro.
     * @param tipoComida dieta de comida a crear
     * @return nueva instancia de Alimento usable en Model y View
     */
    public Alimento newAlimentoInstance(ComidaEnum tipoComida) {
        return switch (tipoComida) {
            case COMIDA -> new Alimento();
            default -> null;
        };
    }
}
