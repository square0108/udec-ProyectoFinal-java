package Model;

import Model.Enumerations.EspeciesEnum;
import Model.Especies.Animal;

/**
 * utility class con metodos para verificar compatibilidades entre animal-animal o animal-habitat
 */
public class CompatibleChecker {
    /**
     * Devuelve true o false dependiendo de si este par de animales individuales son compatibles para estar en un mismo
     * habitat. Este metodo DEPENDE de que el primer elemento en la lista de compatibilidad en cualquier animal sea
     * siempre el animal mismo. (es decir, que el primer elemento de la lista de compatibilidad de Leon sea LEON).
     * @param animal1 primer animal
     * @param animal2 segundo animal
     * @return booleano que describe la relacion de compatibilidad
     */
    public static boolean isCompatible(Animal animal1, Animal animal2) {
        /* TODO: devolver todos los animales que no son compatibles */
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
