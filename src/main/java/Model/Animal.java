package Model;

import Model.Enumerations.AnimalEnum;

public abstract class Animal {
    private int porcentajeComida;
    private final int gananciaHambre;
    private final float rangoTemperatura[]; // celsius

    public Animal(int gananciaHambre, float limInferiorTemperatura, float limSuperiorTemperatura) {
        this.rangoTemperatura = new float[]{limInferiorTemperatura, limSuperiorTemperatura};
        this.porcentajeComida = 100;
        this.gananciaHambre = gananciaHambre;
    }
    public void comer(Alimento alimento) {
        if (alimento != null && alimento.getConsumidoStatus() == false) {
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
    public abstract AnimalEnum[] AnimalesCompatibles();

    /* TODO: void movement()
    */
}
