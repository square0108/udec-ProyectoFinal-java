package Model;

import Model.Enumerations.EspeciesEnum;

/**
 * Utility class con metodos para verificar compatibilidades entre animal-animal o animal-habitat
 */
public class CompatibleChecker {
    /**
     * Devuelve true o false dependiendo de si este par de animales individuales son compatibles para estar en un mismo
     * habitat. Notar que si un animal no se incluye a si mismo en su lista de compatibilidad entonces no es compatible
     * con si mismo por defecto.
     * @param animal1 primer animal
     * @param animal2 segundo animal
     * @return booleano que describe la relacion de compatibilidad
     */
    public static boolean isCompatible(Animal animal1, Animal animal2) {
        EspeciesEnum[] List1 = animal1.animalesCompatibles();
        EspeciesEnum[] List2 = animal2.animalesCompatibles();

        for (EspeciesEnum anEnum : List1) {
            if (anEnum == EspeciesEnum.classToEnum(animal2)) return true;
        }
        /* Checkea ambas listas *por si acaso*. ¿Será esto un error? */
        for (EspeciesEnum especiesEnum : List2) {
            if (especiesEnum == EspeciesEnum.classToEnum(animal1)) return true;
        }

        return false;
    }

    /**
     * Devuelve true o false dependiendo de si este animal es compatible con el habitat en cuestion.
     * @param animal animal que se desea agregar al habitat
     * @param habitat habitat en cuestion
     * @return booleano que describe la relacion de compatibilidad
     */
    public static boolean isCompatible(Animal animal, Habitat habitat) {
        float animalMinTemp = animal.getRangoTemperatura()[0];
        float animalMaxTemp = animal.getRangoTemperatura()[1];

        if (animal.getTipoTerreno() == habitat.getTipoTerreno() && (animalMinTemp < habitat.getTemperatura() && habitat.getTemperatura() < animalMaxTemp)) {
            return true;
        }
        else {
            return false;
        }
    }
}
