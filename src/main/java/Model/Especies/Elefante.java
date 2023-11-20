package Model.Especies;

import Model.Enumerations.AnimalEnum;

public class Elefante extends Animal {
    public Elefante() {
        super(25, 18.0F, 42.0F);
    }

    @Override
    public AnimalEnum[] AnimalesCompatibles() {
        return new AnimalEnum[]
                {AnimalEnum.ELEFANTE, AnimalEnum.JIRAFA};
    }
}
