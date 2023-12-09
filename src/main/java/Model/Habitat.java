package Model;

import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.EstadosEnum;
import Model.Enumerations.TerrenoEnum;
import Model.Exceptions.AlimentoLimiteException;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatIncompatibleException;
import Model.Exceptions.HabitatLlenoException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class Habitat {
    /* variables relacionadas con animales */
    private final int poblacionMax;
    private final ArrayList<Animal> animalesCercados;

    /* variables relacionadas con alimentos */
    private final ArrayList<Alimento> reservaAlimentos;
    private boolean hayAlimento;

    /* variables relacionadas con ambiente */
    private final TerrenoEnum tipoTerreno;
    private final float temperatura;

    /* constantes */
    private static final int ALIMENTO_MAX = 10;
    private static final int ALIMENTO_INICIAL = 3;
    protected static final int UPDATE_TICK_RATE = 60; // llamadas de run() necesarias para actualizar el backend
    private int tickCounter;

    /**
     * Clase abstracta que simula las propiedades comunes a todos los Habitat unicos
     * @param temperatura Temperatura ambiente del habitat. Para que un animal viva aqui, la temperatura ambiente del
     *                    habitat debe estar dentro del rango de temperatura aceptable del animal.
     * @param poblacionMax Poblacion maxima de animales que acepta este habitat.
     *                     TODO: Variar el espacio que ocupan diferentes especies? Puede ser complicado.
     * @param tipoTerreno Determina el tipo de animales que pueden habitar aqui. Animales terrestres solo con terrenos
     *                    terrestres, acuaticos solo con terrenos acuaticos, etc.
     */
    public Habitat(float temperatura, int poblacionMax, TerrenoEnum tipoTerreno) {
        this.poblacionMax = poblacionMax;
        this.animalesCercados = new ArrayList<>();
        this.reservaAlimentos = new ArrayList<>();
        this.tipoTerreno = tipoTerreno;
        this.temperatura = temperatura;
        this.tickCounter = 0;

        /* cualquier habitat es inicializado con una cantidad de comida inicial "gratis" por default */
        for (int i = 0; i < ALIMENTO_INICIAL; i++) {
            reservaAlimentos.add(new Alimento());
        }

        this.hayAlimento = true;
    }

    public void update() {
        if (this.tickCounter == UPDATE_TICK_RATE) {
            removerAnimalesMuertos();
            for (Animal animal : animalesCercados) {
                animal.update();
            }
            this.tickCounter = 0;
        }
        else {
            this.tickCounter++;
        }
    }

    /**
     * El nombre es bastante descriptivo.
     */
    public void removerAnimalesMuertos() {
        for (int i = 0; i < animalesCercados.size(); i++) {
            if (animalesCercados.get(i) == null) break;
            else if (animalesCercados.get(i).getEstado() == EstadosEnum.MUERTO) {
                System.out.println("removed: " + animalesCercados.get(i));
                removeAnimal(i);
            }
        }
    }

    /**
     * Agrega un nuevo animal a este habitat. Debe pasar por varios checks logicos de compatibilidad.
     * @param nuevoAnimal Animal nuevo a agregar
     */
    public void addAnimal(Animal nuevoAnimal) throws AnimalesIncompatiblesException, HabitatLlenoException, HabitatIncompatibleException {
            /* Checkear si el habitat ya se encuentra lleno. */
            if (animalesCercados.size() >= poblacionMax) {
                throw new HabitatLlenoException();
            }

            /* Checkear si este animal es compatible con el habitat */
            if (CompatibleChecker.isCompatible(nuevoAnimal, this) == false) {
                System.out.println("Error." + EspeciesEnum.classToEnum(nuevoAnimal) + " no es compatible con este habitat.");
                throw new HabitatIncompatibleException();
            }

            for (Animal animal : animalesCercados) {
                /* Para cada animal presente en el habitat, checkear si este es compatible con el animal nuevo.
                 *  TODO: Esto se podria optimizar checkeando solo las especies presentes en vez de animales individuales.*/
                if (animal != null && !CompatibleChecker.isCompatible(animal, nuevoAnimal)) {
                    throw new AnimalesIncompatiblesException();
                }
            }

            /* Finalmente, se ha verificado que:
             * 1. el habitat no esta lleno
             * 2. el animal nuevo es compatible con el habitat
             * 3. el animal nuevo es compatible con todos los otros animales
             * por lo tanto, se agrega al habitat. */
            animalesCercados.add(nuevoAnimal);
            nuevoAnimal.setHabitatHogar(this);
        }
    /**
     * Remueve un animal específico y además setea su habitatHogar a NULL, de esta forma el resto del programa sabe que el animal no pertenece a algún habitat.
     * @param index indice del arreglo
     */
    public void removeAnimal(int index) {
        if (animalesCercados.get(index) != null) {
            animalesCercados.get(index).setHabitatHogar(null);
            animalesCercados.remove(index);
        } else {
            System.out.println("DEBUG: Se ha intentado llamar removeAnimal() en " + this + ", pero este habitat se encuentra vacio.");
        }
    }

    public TerrenoEnum getTipoTerreno() {
        return tipoTerreno;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public Alimento popAlimento() {
        /* si es que la reserva ya estaba vacia */
        if (reservaAlimentos.isEmpty()) {
            hayAlimento = false;
            return null;
        }

        Alimento alimentoOut = reservaAlimentos.remove(0);

        /* si el alimento consumido fue el ultimo, setear hayAlimento = false */
        if (reservaAlimentos.size() == 0) hayAlimento = false;

        return alimentoOut;
    }

    public void addAlimento(@NotNull Alimento a) throws AlimentoLimiteException {
        if (reservaAlimentos.size() >= ALIMENTO_MAX) {
            throw new AlimentoLimiteException();
        }
        else {
            this.reservaAlimentos.add(a);
            hayAlimento = true;
        }
    }

    /**
     * usar con cuidado
     * @return reserva de alimentos del habitat
     */
    public ArrayList<Alimento> getReservaAlimentos() {return reservaAlimentos;}
    public int getCantidadAlimento(){return reservaAlimentos.size();}
    public int getCurrentPop() {return this.animalesCercados.size();}
    public ArrayList<Animal> getArrayAnimales(){
        return animalesCercados;
    }
    public boolean isFull(){
        return poblacionMax == animalesCercados.size();
    }
}
