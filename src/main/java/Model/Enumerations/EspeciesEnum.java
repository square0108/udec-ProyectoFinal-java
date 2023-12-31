package Model.Enumerations;

import Model.Animal;
import Model.Especies.*;

/**
 * Posee constantes representativas de algún objeto extensión de Animal con un mismo nombre. Utilizado para instanciación encapsulada, principalmente.
 */
public enum EspeciesEnum {
    LEON(Leon.class, "leon.png"),
    JIRAFA(Jirafa.class, "jirafa.png"),
    ELEFANTE(Elefante.class, "elefante.png"),
    CARPINCHO(Carpincho.class, "carpincho.png"),
    CANGREJO(Cangrejo.class, "cangrejo.png"),
    GORILA(Gorila.class, "gorila.png"),
    JABALI(Jabali.class, "jabali.png"),
    JAGUAR(Jaguar.class, "jaguar.png"),
    MONO(Mono.class, "mono.png"),
    PEZ(Pez.class, "pez.png"),
    PINGUINO(Pinguino.class, "pinguino.png"),
    POLAR(Polar.class, "polar.png"),
    PULPO(Pulpo.class, "pulpo.png");
    final Class<?> claseAsociada;
    final String texturePath;

    /**
     * Es requerido agregar un nuevo animal a esta enumeration para implementarlo correctamente.
     * @param claseEspecie .class de la subclase de Animal que representa esta constante
     * @param texturePath archivo textura .png de este animal
     */
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
        System.out.println("Error en el metodo animalToEnum(); Input: " + animal + "; Ninguna constante corresponde con esta clase.");
        return null;
    }

    public String getTexturePath() {
        return texturePath;
    }

    /* Estos métodos iteran sobre las texturas de cada animal. Utilizado por el panel selector de animales. */
    public EspeciesEnum siguiente() {
        int siguienteIndice = (this.ordinal() + 1) % EspeciesEnum.values().length;
        return EspeciesEnum.values()[siguienteIndice];
    }

    public EspeciesEnum anterior() {
        int anteriorIndice = (this.ordinal() - 1 + EspeciesEnum.values().length) % EspeciesEnum.values().length;
        return EspeciesEnum.values()[anteriorIndice];
    }
}
