package Model.Especies;

import Model.Animal;
import Model.TerrenoEnum;

public class Leon extends Animal {
    private final float rangoTempCelsius[];
    public Leon() {
        super(20);
        this.rangoTempCelsius = new float[]{10, 30};
    }
}
