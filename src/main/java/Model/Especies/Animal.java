package Model.Especies;

import Model.Alimento;
import Model.Enumerations.AnimalEnum;
import Model.Enumerations.TerrenoEnum;

import java.util.Timer;

public abstract class Animal {
    private final TerrenoEnum tipoTerreno;
    private int porcentajeComida;
    private final int gananciaHambre;
    private final float rangoTemperatura[]; // celsius
    private Timer hambreTimer;

    public Animal(TerrenoEnum tipoTerreno, int gananciaHambre, float limInferiorTemperatura, float limSuperiorTemperatura) {
        this.tipoTerreno = tipoTerreno;
        this.rangoTemperatura = new float[]{limInferiorTemperatura, limSuperiorTemperatura};
        this.porcentajeComida = 100;
        this.gananciaHambre = gananciaHambre;

        this.hambreTimer = new Timer();
    }

    public void comer(Alimento alimento) {
        if (alimento != null && !alimento.estaConsumido()) {
            /* Alimentos siempre sacian el hambre completamente. */
            this.porcentajeComida = 100;
            alimento.setConsumidoStatus(true);
        }
    }

    public void ganarHambre() {
        porcentajeComida = porcentajeComida - gananciaHambre;
    }

    /**
     * Regla importante para el funcionamiento del compatibility handling: todas las implementaciones de este metodo,
     * deben tener a la misma subclase como la primera constante (indice 0) del arreglo retornado.
     * @return array de AnimalEnum conteniendo solo las constantes que representan los animales compatibles con este animal.
     */
    public abstract AnimalEnum[] animalesCompatibles();

    public TerrenoEnum getTipoTerreno() {
        return this.tipoTerreno;
    }

    public float[] getRangoTemperatura() {
        return rangoTemperatura;
    }
    /* TODO: void movement()
     */
}

