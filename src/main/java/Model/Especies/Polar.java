package Model.Especies;

import Model.Animal;
import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.TerrenoEnum;
import static Model.Enumerations.EspeciesEnum.*;

public class Polar extends Animal {
    private static final EspeciesEnum[] animalesCompatiblesList = {POLAR};
    public Polar() {
        super(TerrenoEnum.TERRESTRE,5, -50.0F, 4.0F);
    }

    @Override
    public EspeciesEnum[] animalesCompatibles() {
        return animalesCompatiblesList;
    }
}
