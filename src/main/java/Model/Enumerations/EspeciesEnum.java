package Model.Enumerations;

import Model.Animal;
import Model.Especies.*;

public enum EspeciesEnum {
    LEON(Leon.class, "lion.png"),
    JIRAFA(Jirafa.class, "giraffe.png"),
    ELEFANTE(Elefante.class, "elephant.png");
    final Class<?> claseAsociada;
    final String texturePath;

    EspeciesEnum(Class<?> claseEspecie, String texturePath) {
        this.claseAsociada = claseEspecie;
        this.texturePath = texturePath;
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

    //todo: MEtodos que hice yoo aaa

    public String getTexturePath() {
        return texturePath;
    }

    public EspeciesEnum siguiente() {
        int siguienteIndice = (this.ordinal() + 1) % EspeciesEnum.values().length;
        return EspeciesEnum.values()[siguienteIndice];
    }

    public EspeciesEnum anterior() {
        int anteriorIndice = (this.ordinal() - 1 + EspeciesEnum.values().length) % EspeciesEnum.values().length;
        return EspeciesEnum.values()[anteriorIndice];
    }
}
