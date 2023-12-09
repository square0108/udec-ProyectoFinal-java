package Model.Especies;

import Model.Animal;
import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.TerrenoEnum;
import static Model.Enumerations.EspeciesEnum.*;

public class Cangrejo extends Animal {
    private static final EspeciesEnum[] animalesCompatiblesList = {CANGREJO,PEZ};
    public Cangrejo() {
        super(TerrenoEnum.ACUATICO,1, -1.8F, 12.0F);
    }

    @Override
    public EspeciesEnum[] animalesCompatibles() {
        return animalesCompatiblesList;
    }
}
