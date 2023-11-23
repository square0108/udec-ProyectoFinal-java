package Model.Enumerations;

import Model.EntornosHabitat.*;
import Model.Habitat;

public enum HabitatEnum {
    SABANA(Sabana.class);

    Class<?> claseAsociada;

    HabitatEnum(Class<?> claseHabitat) {
        this.claseAsociada = claseHabitat;
    }

    /**
     * TRADUCE una clase de habitat especifico a su constante equivalente en este enum.
     * @param habitat instancia de una subclase de Habitat
     * @return constante equivalente/representante del habitat ingresado
     */
    public static AnimalEnum classToEnum(Habitat habitat) {
        for (int i = 0; i < AnimalEnum.values().length; i++) {
            if (habitat.getClass() == AnimalEnum.values()[i].claseAsociada) return AnimalEnum.values()[i];
        }
        // todo: exception handling
        System.out.println("Error en el metodo animalToEnum(); Input: " + habitat + "; Ninguna constante corresponde con esta clase.");
        return null;
    }
}
