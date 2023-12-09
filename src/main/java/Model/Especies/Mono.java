package Model.Especies;

import Model.Animal;
import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.TerrenoEnum;
import static Model.Enumerations.EspeciesEnum.*;

public class Mono extends Animal {
    private static final EspeciesEnum[] animalesCompatiblesList = {MONO, GORILA};
    public Mono() {
        super(TerrenoEnum.TERRESTRE,3, 18.0F, 32.0F);
    }

    @Override
    public EspeciesEnum[] animalesCompatibles() {
        return animalesCompatiblesList;
    }
}
