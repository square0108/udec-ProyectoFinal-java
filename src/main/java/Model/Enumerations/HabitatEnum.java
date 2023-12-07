package Model.Enumerations;

import Model.EntornosHabitat.*;
import Model.Habitat;

public enum HabitatEnum {
    JUNGLA(Jungla.class, "sabana.png");
    final String texture_path;
    final Class<?> claseAsociada;

    HabitatEnum(Class<?> claseHabitat, String texture_path) {
        this.texture_path = texture_path;
        this.claseAsociada = claseHabitat;
    }

    /**
     * TRADUCE una clase de habitat especifico a su constante equivalente en este enum.
     * @param habitat instancia de una subclase de Habitat
     * @return constante equivalente/representante del habitat ingresado
     */
    public static HabitatEnum classToEnum(Habitat habitat) {
        for (int i = 0; i < HabitatEnum.values().length; i++) {
            if (habitat.getClass() == HabitatEnum.values()[i].claseAsociada) return HabitatEnum.values()[i];
        }
        // todo: exception handling
        System.out.println("Error en el metodo animalToEnum(); Input: " + habitat + "; Ninguna constante corresponde con esta clase.");
        return null;
    }
    public String getTexturePath() {
        return texture_path;
    }
    public HabitatEnum siguiente() {
        int siguienteIndice = (this.ordinal() + 1) % HabitatEnum.values().length;
        return HabitatEnum.values()[siguienteIndice];
    }

    public HabitatEnum anterior() {
        int anteriorIndice = (this.ordinal() - 1 + HabitatEnum.values().length) % HabitatEnum.values().length;
        return HabitatEnum.values()[anteriorIndice];
    }
}
