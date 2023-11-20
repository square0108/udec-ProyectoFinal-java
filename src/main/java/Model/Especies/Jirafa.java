package Model.Especies;

import Model.Enumerations.AnimalEnum;
import Model.Enumerations.TerrenoEnum;

public class Jirafa extends Animal {
    private static final AnimalEnum[] animalesCompatiblesList = {AnimalEnum.JIRAFA, AnimalEnum.ELEFANTE};
    public Jirafa() {
        super(TerrenoEnum.TERRESTRE,5, 18.0F, 32.0F);
    }

    @Override
    public AnimalEnum[] animalesCompatibles() {
        return animalesCompatiblesList;
    }
}
