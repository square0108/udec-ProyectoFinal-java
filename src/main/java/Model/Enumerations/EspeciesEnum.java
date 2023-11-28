package Model.Enumerations;

import Model.Especies.*;

public enum EspeciesEnum {
    LEON(Leon.class), JIRAFA(Jirafa.class), ELEFANTE(Elefante.class);
    Class<?> claseAsociada;

    EspeciesEnum(Class<?> claseEspecie) {
        this.claseAsociada = claseEspecie;
    }

    /**
     * TRADUCE una clase de una especie especifica (subclase de Animal) a su constante equivalente en este enum.
     * @param animal instancia de una especie
     * @return constante equivalente/representante de la especie ingresada
     */
    public static EspeciesEnum classToEnum(Animal animal) {
        for (int i = 0; i < EspeciesEnum.values().length; i++) {
            if (animal.getClass() == EspeciesEnum.values()[i].claseAsociada) return EspeciesEnum.values()[i];
        }
        // todo: exception handling
        System.out.println("Error en el metodo animalToEnum(); Input: " + animal + "; Ninguna constante corresponde con esta clase.");
        return null;
    }
    public Class<?> getClaseAsociada() {
        return this.claseAsociada;
    }
}
