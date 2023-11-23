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
    private Timer HambreTimer;

    /**
     * Clase que simula las propiedades comunes a todos los animales del zoologico.
     * @param tipoTerreno Depende de si el animal es terrestre, acuatico, anfibio... (ver TerrenoEnum)
     * @param gananciaHambre Tasa de cambio representando que tan frecuentemente necesita alimentarse este animal,
     *                       o bien cuanta nutricion necesita. En la practica esto solo determina que tan rapido
     *                       desciende porcentajeHambre. Abstractamente, animales mas masivos necesitaran mas comida,
     *                       por lo tanto Ballena tendria mayor gananciaHambre que Gato.
     * @param limInferiorTemperatura Limite inferior de temperatura ambiente que necesita el animal.
     * @param limSuperiorTemperatura Limite superior de temperatura ambiente que necesita el animal.
     */
    public Animal(TerrenoEnum tipoTerreno, int gananciaHambre, float limInferiorTemperatura, float limSuperiorTemperatura) {
        this.tipoTerreno = tipoTerreno;
        this.rangoTemperatura = new float[]{limInferiorTemperatura, limSuperiorTemperatura};
        this.porcentajeComida = 100;
        this.gananciaHambre = gananciaHambre;
    }

    /**
     * Consume un Alimento, en la version actual los alimentos solo se consumen completamente o no se consumen.
     * @param alimento alimento a consumir
     */
    public void comer(Alimento alimento) {
        if (alimento != null && !alimento.estaConsumido()) {
            /* Alimentos siempre sacian el hambre completamente. */
            this.porcentajeComida = 100;
            alimento.setConsumidoStatus(true);
        }
    }

    /**
     * Metodo que hace al animal sentir hambre. La tasa de cambio es proporcional a gananciaHambre, una propiedad unica
     * a cada especie.
     */
    public void ganarHambre() {
        porcentajeComida = porcentajeComida - gananciaHambre;
        if (porcentajeComida <= 0) {
            System.out.println(this + " ha muerto de hambre. :(");

        }
        System.out.println("DEBUG: Animal " + this + " tiene " + porcentajeComida + "% hambre restante");
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
}

