package Model.Especies;

import Model.Animal;
import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.TerrenoEnum;
import static Model.Enumerations.EspeciesEnum.*;

public class Elefante extends Animal {
    private static final EspeciesEnum[] animalesCompatiblesList = {ELEFANTE,JIRAFA,MONO};
    public Elefante() {super(TerrenoEnum.TERRESTRE,5, 18.0F, 42.0F);}

    @Override
    public EspeciesEnum[] animalesCompatibles() {
        return animalesCompatiblesList;
    }
}
