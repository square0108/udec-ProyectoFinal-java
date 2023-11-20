package Model.Especies;

import Model.Enumerations.AnimalEnum;

public class Leon extends Animal {
    public Leon() {
        super(20, 10.0F, 35.0F);
    }

    @Override
    public AnimalEnum[] AnimalesCompatibles() {
        return new AnimalEnum[]
                {AnimalEnum.LEON};
    }
}
