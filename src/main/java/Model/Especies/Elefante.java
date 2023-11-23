package Model.Especies;

import Model.Enumerations.AnimalEnum;
import Model.Enumerations.TerrenoEnum;

public class Elefante extends Animal {
    private static final AnimalEnum[] animalesCompatiblesList = {AnimalEnum.ELEFANTE, AnimalEnum.JIRAFA};
    public Elefante() {super(TerrenoEnum.TERRESTRE,5, 18.0F, 42.0F);}

    @Override
    public AnimalEnum[] animalesCompatibles() {
        return animalesCompatiblesList;
    }
}
