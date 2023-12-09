package Model.Especies;

import Model.Animal;
import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.TerrenoEnum;

public class Cangrejo extends Animal {
    private static final EspeciesEnum[] animalesCompatiblesList = {EspeciesEnum.JIRAFA, EspeciesEnum.ELEFANTE};
    public Cangrejo() {
        super(TerrenoEnum.TERRESTRE,10, 18.0F, 32.0F);
    }

    @Override
    public EspeciesEnum[] animalesCompatibles() {
        return animalesCompatiblesList;
    }
}
