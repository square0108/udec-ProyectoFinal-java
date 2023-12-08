package Model.Especies;

import Model.Animal;
import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.TerrenoEnum;

public class Elefante extends Animal {
    private static final EspeciesEnum[] animalesCompatiblesList = {EspeciesEnum.ELEFANTE, EspeciesEnum.JIRAFA};
    public Elefante() {super(TerrenoEnum.TERRESTRE,90, 18.0F, 42.0F);}

    @Override
    public EspeciesEnum[] animalesCompatibles() {
        return animalesCompatiblesList;
    }
}
