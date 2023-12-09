package Model.EntornosHabitat;

import Model.Habitat;
import Model.Enumerations.TerrenoEnum;

public class Artico extends Habitat {
    public Artico(){
        super(-20.0F,4, TerrenoEnum.TERRESTRE);
    }
}