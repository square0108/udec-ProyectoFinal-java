package Model.Enumerations;

import Model.Especies.*;

public enum AnimalEnum {
    LEON(Leon.class, "lion.png"),
    JIRAFA(Jirafa.class, "giraffe.png"),
    ELEFANTE(Elefante.class, "elephant.png");

    Class<?> claseAsociada;
    String texturePath;

    AnimalEnum(Class<?> claseEspecie, String texturePath) {
        this.claseAsociada = claseEspecie;
        this.texturePath = texturePath;
    }

    /**
     * TRADUCE una clase de una especie especifica (subclase de Animal) a su constante equivalente en este enum.
     * @param animal instancia de una especie
     * @return constante equivalente/representante de la especie ingresada
     */
    public static AnimalEnum classToEnum(Animal animal) {
        for (int i = 0; i < AnimalEnum.values().length; i++) {
            if (animal.getClass() == AnimalEnum.values()[i].claseAsociada) return AnimalEnum.values()[i];
        }
        // todo: exception handling
        System.out.println("Error en el metodo animalToEnum(); Input: " + animal + "; Ninguna constante corresponde con esta clase.");
        return null;
    }

    //todo: MEtodos que hice yoo aaa

    public String getTexturePath() {
        return texturePath;
    }

    public AnimalEnum siguiente() {
        int siguienteIndice = (this.ordinal() + 1) % AnimalEnum.values().length;
        return AnimalEnum.values()[siguienteIndice];
    }

    public AnimalEnum anterior() {
        int anteriorIndice = (this.ordinal() - 1 + AnimalEnum.values().length) % AnimalEnum.values().length;
        return AnimalEnum.values()[anteriorIndice];
    }
}
