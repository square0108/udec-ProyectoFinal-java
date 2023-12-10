package Model;

import Model.EntornosHabitat.Jungla;
import Model.Enumerations.EspeciesEnum;
import Model.Especies.*;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatIncompatibleException;
import Model.Exceptions.HabitatLlenoException;

/**
 * Fue utilizada en fases iniciales del desarrollo para testear funcionalidad del back-end.
 */
@Deprecated
public class Testing {
    public static void main(String[] args) throws HabitatLlenoException, AnimalesIncompatiblesException, HabitatIncompatibleException {
        Leon manuel = new Leon();
        System.out.println(CompatibleChecker.isCompatible(new Elefante(), new Jirafa()));
        System.out.println(CompatibleChecker.isCompatible(new Elefante(), new Leon()));

        Jungla jungla1 = new Jungla();
        jungla1.addAnimal(manuel);
        System.out.println(EspeciesEnum.classToEnum(manuel));
        System.out.println(jungla1.popAlimento());
    }
}
