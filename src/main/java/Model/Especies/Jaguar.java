package Model.Especies;

import Model.Animal;
import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.TerrenoEnum;
import static Model.Enumerations.EspeciesEnum.*;

public class Jaguar extends Animal {
    private static final EspeciesEnum[] animalesCompatiblesList = {JAGUAR};
    public Jaguar() {
        super(TerrenoEnum.TERRESTRE,4, 12.0F, 40.0F);
    }

    @Override
    public EspeciesEnum[] animalesCompatibles() {
        return animalesCompatiblesList;
    }
}
