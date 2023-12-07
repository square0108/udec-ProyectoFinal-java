package Model;

import Model.Alimento;
import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.EstadosEnum;
import Model.Enumerations.TerrenoEnum;
import Model.Habitat;

import java.util.Random;

public abstract class Animal {
    private final TerrenoEnum tipoTerreno;
    private final float rangoTemperatura[]; // celsius
    private Habitat habitatHogar;
    private int porcentajeComida;
    private final int gananciaHambre;
    private EstadosEnum estado;

    /* Constantes */
    public static final int MINIMO_PARA_ALIMENTARSE = 50;
    public static final int PORCENTAJE_CHANCE_MOVIMIENTO = 50;
    private static final Random rand = new Random(); /* generador de numeros aleatorios */

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
        this.habitatHogar = null;
        this.rangoTemperatura = new float[]{limInferiorTemperatura, limSuperiorTemperatura};
        this.porcentajeComida = 100;
        this.gananciaHambre = gananciaHambre;
        this.estado = EstadosEnum.PASIVO;
    }

    public void update() {
        this.ganarHambre();
        this.intentarMovimiento();
    }

    /**
     * Consume un Alimento, en la version actual los alimentos solo se consumen completamente o no se consumen.
     * Animales muertos no pueden comer.
     */
    public void comer() {
        /* Siempre revisar que el animal no este muerto antes de efectuar alguna operacion tipo "write" sobre Habitat
        *  Podría incluir estado == COMIENDO también, pero no debería ser necesario? */
        if (this.estado == EstadosEnum.MUERTO) {
            return;
        }

        /* Si el alimento recibido es nulo (lo cual significa que no queda alimento), el animal simplemente no consume
        * nada.*/
        Alimento alimento = this.habitatHogar.popAlimento();
        if (alimento != null) {
            /* Alimentos siempre sacian el hambre completamente. */
            this.porcentajeComida = 100;
            this.estado = EstadosEnum.COMIENDO;
            alimento.setConsumidoStatus(true);
        }
    }

    /**
     * Animal pierde porcentajeComida proporcionalmente a la tasa de cambio gananciaHambre, la cual es inicializada
     * de forma única por cada especie subclase de Animal.
     * La cadena lógica es:
     * 1. revisar si el animal está muerto, en este caso no gana hambre
     * 2. revisar si el animal está comiendo, si es así ocurre una llamda de thread.sleep sin que gane hambre
     * 3. si el animal está pasivo o moviéndose, pierde porcentajeComida.
     * 4. si esta variable bajó de 0, el animal muere.
     * 5. si esta variable
     */
    public void ganarHambre() {
        /* Siempre revisar que el animal no este muerto.
        *  Si el animal esta comiendo, se demora 1 tick de LogicThread y no gana hambre durante este periodo. */
        switch (getEstado()) {
            case MUERTO:
                return;
            case COMIENDO:
                System.out.println("DEBUG: Animal " + this + " esta comiendo... ");
                this.estado = EstadosEnum.PASIVO;
                return;
            default:
                break;
        }

        /* Incrementar hambre de acuerdo con la tasa de cambio de la especie*/
        porcentajeComida = porcentajeComida - gananciaHambre;

        /* Matar al animal si el cambio anterior causo que porcentajeComida bajara de 0 */
        if (porcentajeComida <= 0) {
            System.out.println(this + " ha muerto de hambre. :(");
            this.estado = EstadosEnum.MUERTO;
            return;
        }

        /* Si porcentajeComida desciende del umbral de hambre, el animal intenta alimentarse */
        if (porcentajeComida < MINIMO_PARA_ALIMENTARSE) {
            System.out.println("DEBUG: Animal " + this + " tiene hambre y quiere comer... ");
            comer();
        }
        // debugging prints
        System.out.println("Hambre: " + this.porcentajeComida + ", Animal: " + this);
    }

    /**
     * el LogicThread utiliza este metodo para aleatoriamente "mover" al animal, aunque solo de forma simulada.
     * Bajo estos dos estados el animal no puede cambiar su estado de movimiento:
     * 1. si el animal esta muerto
     * 2. si el animal esta alimentandose
     */
    public void intentarMovimiento() {
        switch (this.getEstado()) {
            case PASIVO -> {
                if (rand.nextInt(101) < PORCENTAJE_CHANCE_MOVIMIENTO) {
                    this.estado = EstadosEnum.MOVIENDO;
                }
            }
            case MOVIENDO ->
                    this.estado = EstadosEnum.PASIVO;
            default -> {
                break;
            }
        }
    }

    /**
     * @return array de AnimalEnum conteniendo solo las constantes que representan los animales compatibles con este animal.
     */
    public abstract EspeciesEnum[] animalesCompatibles();

    public TerrenoEnum getTipoTerreno() {
        return this.tipoTerreno;
    }

    public float[] getRangoTemperatura() {
        return rangoTemperatura;
    }

    public int getPorcentajeComida() {
        return this.porcentajeComida;
    }

    public EstadosEnum getEstado() {return this.estado;}

    public void setHabitatHogar(Habitat h) {this.habitatHogar = h;}
}

