package Model.Especies;

import Model.Enumerations.AnimalEnum;
import Model.Enumerations.TerrenoEnum;

public class Leon extends Animal {
    private static final AnimalEnum[] animalesCompatiblesList = {AnimalEnum.LEON};
    public Leon() {
        super(TerrenoEnum.TERRESTRE,20, 10.0F, 35.0F);
    }

    @Override
    public AnimalEnum[] animalesCompatibles() {
        return animalesCompatiblesList;
    }
}
