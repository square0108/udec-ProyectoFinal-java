package Model.Especies;

import Model.Animal;
import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.TerrenoEnum;
import static Model.Enumerations.EspeciesEnum.*;

public class Pinguino extends Animal {
    private static final EspeciesEnum[] animalesCompatiblesList = {PINGUINO};
    public Pinguino() {
        super(TerrenoEnum.TERRESTRE,2, -38.0F, 4.0F);
    }

    @Override
    public EspeciesEnum[] animalesCompatibles() {
        return animalesCompatiblesList;
    }
}
