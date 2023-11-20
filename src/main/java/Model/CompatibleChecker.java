package Model;

import Model.Enumerations.AnimalEnum;
import Model.Especies.Animal;

public class CompatibleChecker {
    /* TODO: implementar compatibilidad entre animales tal que este metodo no requiera que animalesCompatibles[0] sea
     *   siempre una constante especifica. */
    public static boolean isCompatible(Animal animal1, Animal animal2) {
        AnimalEnum[] List = animal1.animalesCompatibles();

        for (int i = 0; i < List.length; i++) {
            if (List[i] == animal2.animalesCompatibles()[0]) return true;
        }

        return false;
    }
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
