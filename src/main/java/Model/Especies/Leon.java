package Model.Especies;

import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.TerrenoEnum;

public class Leon extends Animal {
    private static final EspeciesEnum[] animalesCompatiblesList = {EspeciesEnum.LEON};
    public Leon() {
        super(TerrenoEnum.TERRESTRE,20, 10.0F, 35.0F);
    }

    @Override
    public EspeciesEnum[] animalesCompatibles() {
        return animalesCompatiblesList;
    }
}
