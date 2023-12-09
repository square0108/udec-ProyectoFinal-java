package Model.Especies;

import Model.Animal;
import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.TerrenoEnum;
import static Model.Enumerations.EspeciesEnum.*;

public class Carpincho extends Animal {
    private static final EspeciesEnum[] animalesCompatiblesList = EspeciesEnum.values(); // el animal más amigable!
    public Carpincho() {
        super(TerrenoEnum.TERRESTRE,2, 14.0F, 32.0F);
    }

    @Override
    public EspeciesEnum[] animalesCompatibles() {
        return animalesCompatiblesList;
    }
}