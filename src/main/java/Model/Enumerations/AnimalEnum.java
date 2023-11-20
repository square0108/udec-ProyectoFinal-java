package Model.Enumerations;

import Model.Especies.*;

public enum AnimalEnum {
    LEON(Leon.class), JIRAFA(Jirafa.class), ELEFANTE(Elefante.class);

    Class<?> claseAsociada;

    AnimalEnum(Class<?> claseEspecie) {
        this.claseAsociada = claseEspecie;
    }

    AnimalEnum classToEnum(Class<?> c) {
        for (int i = 0; i < AnimalEnum.values().length; i++) {
            if (c == AnimalEnum.values()[i].claseAsociada) return AnimalEnum.values()[i];
        }
        // todo: exception handling, o bien, por favor no usar este metodo es horrible
        System.out.println("Ningun enum corresponde con esta clase.");
        return null;
    }
}
