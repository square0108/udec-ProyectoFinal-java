package Model;

import Model.Enumerations.AnimalEnum;
import Model.Enumerations.TerrenoEnum;
import Model.Especies.Animal;

public abstract class Habitat {
    private final int poblacionMax;
    private final Alimento[] reservaAlimentos;
    private final Animal[] animales;
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
        this.reservaAlimentos = new Alimento[20]; /* limite arbitrario de "sacos de comida" */
        this.animales = new Animal[poblacionMax]; /* representa los animales que habitan este habitat */
        this.tipoTerreno = tipoTerreno;
    }

    /**
     * Actualiza el porcentajeComida de todos los animales presentes en este habitat, llamando el metodo ganarHambre()
     * de cada uno de ellos.
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
        for (int i = 0; i < animales.length; i++) {
            if (animales[i] == null) break;
            else if (animales[i].getPorcentajeComida() <= 0) {
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
        if (animalesLengthNotNull() == poblacionMax) {
            System.out.println("El habitat: " + this + " se encuentra lleno, no se pueden agregar mas animales.");
        }
        else {
            /* Checkear si este animal es compatible con el habitat */
            if (CompatibleChecker.isCompatible(nuevoAnimal, this) == false) {
                System.out.println("Error." + AnimalEnum.classToEnum(nuevoAnimal) + " no es compatible con este habitat.");
            }
            else{
                for (int i = 0; i < poblacionMax; i++) {
                    /* Para cada animal presente en el habitat, checkear si este es compatible con el animal nuevo.
                     *  TODO: Esto se podria optimizar checkeando solo las especies presentes en vez de animales individuales.*/
                    if (animales[i] != null && CompatibleChecker.isCompatible(animales[i], nuevoAnimal) == false) {
                        System.out.println("Error. " + AnimalEnum.classToEnum(nuevoAnimal) + " no es compatible con " + AnimalEnum.classToEnum(animales[i]));
                        break;
                    }
                    /* Finalmente, se ha verificado que:
                     * 1. el habitat no esta lleno
                     * 2. el animal nuevo es compatible con el habitat
                     * 3. el animal nuevo es compatible con todos los otros animales
                     * por lo tanto, se agrega al habitat. */
                    else if (animales[i] == null ) {
                        animales[i] = nuevoAnimal;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Remueve un animal del habitat en el indice especifico.
     * @param index indice del arreglo
     * @return Animal que se desea remover
     */
    public Animal removeAnimal(int index) {
        if (animales[index] != null) {
            Animal copy = animales[index];
            animales[index] = null;
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
        for (int i = 0; i < poblacionMax; i++) {
            if (animales[i] != null) return false;
        }
        return true;
    }

    private int animalesLengthNotNull() {
        int len = 0;
        for (int i = 0; i < animales.length; i++) {
            if (animales[i] != null) len++;
            else break;
        }
        return len;
    }
}
