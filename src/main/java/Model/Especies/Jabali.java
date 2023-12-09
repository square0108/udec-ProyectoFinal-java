package Model.Especies;

import Model.Animal;
import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.TerrenoEnum;
import static Model.Enumerations.EspeciesEnum.*;

public class Jabali extends Animal {
    private static final EspeciesEnum[] animalesCompatiblesList = {JABALI};
    public Jabali() {
        super(TerrenoEnum.TERRESTRE,3, 10.0F, 32.0F);
    }

    @Override
    public EspeciesEnum[] animalesCompatibles() {
        return animalesCompatiblesList;
    }
}

