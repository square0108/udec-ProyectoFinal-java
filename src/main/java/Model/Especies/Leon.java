package Model.Especies;

import Model.Animal;
import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.TerrenoEnum;
import static Model.Enumerations.EspeciesEnum.*;

public class Leon extends Animal {
    private static final EspeciesEnum[] animalesCompatiblesList = {LEON};
    public Leon() {
        super(TerrenoEnum.TERRESTRE,20, 10.0F, 35.0F);
    }

    @Override
    public EspeciesEnum[] animalesCompatibles() {
        return animalesCompatiblesList;
    }
}
