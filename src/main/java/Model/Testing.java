package Model;

import Model.EntornosHabitat.Sabana;
import Model.Enumerations.EspeciesEnum;
import Model.Especies.*;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatIncompatibleException;
import Model.Exceptions.HabitatLlenoException;

public class Testing {
    public static void main(String[] args) throws HabitatLlenoException, AnimalesIncompatiblesException, HabitatIncompatibleException {
        Leon manuel = new Leon();
        System.out.println(CompatibleChecker.isCompatible(new Elefante(), new Jirafa()));
        System.out.println(CompatibleChecker.isCompatible(new Elefante(), new Leon()));

        Sabana sabana1 = new Sabana();
        sabana1.addAnimal(manuel);
        System.out.println(EspeciesEnum.classToEnum(manuel));
        System.out.println(sabana1.popAlimento());
    }
}
