package Model;

import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.EstadosEnum;
import Model.Enumerations.TerrenoEnum;
import Model.Exceptions.AnimalesIncompatiblesException;
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
    private static final int ALIMENTO_MAX = 20;
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
     * Actualiza el porcentajeComida de todos los animales presentes en este habitat, llamando el metodo ganarHambre()
     * de cada uno de ellos.
     */
    public void actualizarHambreAnimales() {
        /* todo: poner esta alerta en otra parte */
        if (!this.hayAlimento) System.out.println("!!! " + this + " no tiene alimento !!!");
        for (Animal animal : this.animalesCercados) {
            if (animal == null) break; /* con objetivo de no causar NullPointerException (se llamarian metodos en espacios null del arreglo */
            else {
                animal.ganarHambre();
            }
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
     * Llama el método interno de animales "intentarMovimiento()" el cual rollea una chance para cambiar el
     * estado de los animales desde PASIVO -> MOVIENDO. Si ya están moviéndose, los cambia desde MOVIENDO -> PASIVO.
     * Bajo cualquier otro estado (COMIENDO, MUERTO) no pueden comenzar a moverse.
     */
    public void intentarMoverAnimales() {
        for (Animal animal : animalesCercados) {
            animal.intentarMovimiento();
        }
    }

    /**
     * Agrega un nuevo animal a este habitat. Debe pasar por varios checks logicos de compatibilidad.
     * @param nuevoAnimal Animal nuevo a agregar
     */
    public void addAnimal(Animal nuevoAnimal) throws AnimalesIncompatiblesException, HabitatLlenoException {
            /* Checkear si el habitat ya se encuentra lleno. */
            if (animalesCercados.size() >= poblacionMax) {
                throw new HabitatLlenoException();
            }

            /* Checkear si este animal es compatible con el habitat */
            if (CompatibleChecker.isCompatible(nuevoAnimal, this) == false) {
                /*TODO: QUizas seria bueno poner un error aquí para atraparlo en vista y mostrar un
                *  mensaje cuando no se pueda poner un animal*/
                System.out.println("Error." + EspeciesEnum.classToEnum(nuevoAnimal) + " no es compatible con este habitat.");
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
        /* TODO: ME DA ERROR ESTE CATCH POR ALGUNA RAZON
        catch (HabitatIncompatibleException exc) {
            System.out.println("El animal: " + nuevoAnimal + "no es compatible con el habitat: " + HabitatEnum.classToEnum(this));
        }*/

    /**
     * Remueve un animal del habitat en el indice especifico.
     * @param index indice del arreglo
     */
    public void removeAnimal(int index) {
        if (animalesCercados.get(index) != null) {
            animalesCercados.get(index).setHabitatHogar(null);
            animalesCercados.remove(index);
        }
        System.out.println("DEBUG: Se ha intentado llamar removeAnimal() en " + this + ", pero este habitat se encuentra vacio.");
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

    public void addAlimento(@NotNull Alimento a) {
        if (reservaAlimentos.size() > ALIMENTO_MAX) {
            System.out.println("El habitat " + this + " no acepta mas alimento");
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
