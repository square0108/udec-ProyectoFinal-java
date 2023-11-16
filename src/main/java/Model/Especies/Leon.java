package Model.Especies;

import Model.Carnivoro;
import Model.TerrenoEnum;

public class Leon extends Carnivoro {
    private final int tasaCambioHambre;
    private final float rangoTempCelsius[];
    public Leon() {
        super();
        this.tasaCambioHambre = 15;
        this.rangoTempCelsius = new float[]{10, 30};
    }

    @Override
    public void hambreUp() {
        restarPorcentajeComida(tasaCambioHambre);
    }
    @Override
    public TerrenoEnum cualTerreno() {
        return TerrenoEnum.TERRESTRE;
    }
}
