package Model;

import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.TerrenoEnum;
import Model.Especies.Animal;
import java.util.ArrayList;

public abstract class Habitat {
    private final int poblacionMax;
    private final ArrayList<Alimento> reservaAlimentos;
    private final ArrayList<Animal> animales;
    private final TerrenoEnum tipoTerreno;
    private final float temperatura;

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
        this.temperatura = temperatura;
        this.poblacionMax = poblacionMax;
        this.reservaAlimentos = new ArrayList<>(); /* se le puede setear un limite, opcionalmente */
        this.animales = new ArrayList<>(); /* representa los animales que habitan este habitat */
        this.tipoTerreno = tipoTerreno;
    }

    /**
     * Actualiza el porcentajeComida de todos los animales presentes en este habitat, llamando el metodo ganarHambre()
     * de cada uno de ellos.
     * TODO: Los animales deberian actualizar su estado a "intentar consumir alimento" dentro de este mismo metodo?
     */
    public void actualizarHambreAnimales() {
        for (Animal animal : this.animales) {
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
        for (int i = 0; i < animales.size(); i++) {
            if (animales.get(i) == null) break;
            else if (animales.get(i).getPorcentajeComida() <= 0) {
                System.out.println("removed: " + animales.get(i));
                removeAnimal(i);
            }
        }
    }

    /**
     * Agrega un nuevo animal a este habitat. Debe pasar por varios checks logicos de compatibilidad.
     * @param nuevoAnimal Animal nuevo a agregar
     */
    public void addAnimal(Animal nuevoAnimal) {
        /* TODO: Reemplazar algunos de los if con exception handling? */
        /* Checkear si el habitat ya se encuentra lleno. */
        if (animales.size() == poblacionMax) {
            System.out.println("El habitat: " + this + " se encuentra lleno, no se pueden agregar mas animales.");
        }
        else {
            /* Checkear si este animal es compatible con el habitat */
            if (CompatibleChecker.isCompatible(nuevoAnimal, this) == false) {
                System.out.println("Error." + EspeciesEnum.classToEnum(nuevoAnimal) + " no es compatible con este habitat.");
            }
            else{
                for (int i = 0; i < animales.size(); i++) {
                    /* Para cada animal presente en el habitat, checkear si este es compatible con el animal nuevo.
                     *  TODO: Esto se podria optimizar checkeando solo las especies presentes en vez de animales individuales.*/
                    if (animales.get(i) != null && CompatibleChecker.isCompatible(animales.get(i), nuevoAnimal) == false) {
                        System.out.println("Error. " + EspeciesEnum.classToEnum(nuevoAnimal) + " no es compatible con " + EspeciesEnum.classToEnum(animales.get(i)));
                        return;
                    }
                }
                /* Finalmente, se ha verificado que:
                 * 1. el habitat no esta lleno
                 * 2. el animal nuevo es compatible con el habitat
                 * 3. el animal nuevo es compatible con todos los otros animales
                 * por lo tanto, se agrega al habitat. */
                animales.add(nuevoAnimal);
            }
        }
    }

    /**
     * Remueve un animal del habitat en el indice especifico.
     * @param index indice del arreglo
     * @return Animal que se desea remover
     */
    public Animal removeAnimal(int index) {
        if (animales.get(index) != null) {
            Animal copy = animales.get(index);
            animales.remove(index);
            return copy;
        }
        System.out.println("Error, este habitat se encuentra vacio.");
        return null;
    }

    public TerrenoEnum getTipoTerreno() {
        return tipoTerreno;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public boolean isEmpty() {
        return animales.isEmpty();
    }

    private int animalesLengthNotNull() {
        int len = 0;
        for (int i = 0; i < animales.size(); i++) {
            if (animales.get(i) != null) len++;
            else break;
        }
        return len;
    }
}
