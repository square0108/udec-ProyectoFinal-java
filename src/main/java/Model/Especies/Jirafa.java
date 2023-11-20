package Model.Especies;

import Model.Enumerations.AnimalEnum;

public class Jirafa extends Animal {
    public Jirafa() {
        super(5, 18.0F, 32.0F);
    }

    @Override
    public AnimalEnum[] AnimalesCompatibles() {
        return new AnimalEnum[]
                {AnimalEnum.JIRAFA, AnimalEnum.ELEFANTE};
    }
}
