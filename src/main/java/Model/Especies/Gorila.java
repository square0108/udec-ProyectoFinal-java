package Model.Especies;

import Model.Animal;
import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.TerrenoEnum;
import static Model.Enumerations.EspeciesEnum.*;

public class Gorila extends Animal {
    private static final EspeciesEnum[] animalesCompatiblesList = {GORILA,JIRAFA, MONO};
    public Gorila() {
        super(TerrenoEnum.TERRESTRE,4, 15.0F, 38.0F);
    }

    @Override
    public EspeciesEnum[] animalesCompatibles() {
        return animalesCompatiblesList;
    }
}
