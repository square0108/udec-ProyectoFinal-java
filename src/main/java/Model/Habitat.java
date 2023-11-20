package Model;

import Model.Enumerations.TerrenoEnum;
import Model.Especies.Animal;

public abstract class Habitat {
    private final int poblacionMax;
    private final Alimento[] reservaAlimentos;
    private final Animal[] animales;
    private final TerrenoEnum tipoTerreno;
    private final float temperatura;

    public Habitat(float temperatura, int poblacionMax, TerrenoEnum tipoTerreno) {
        this.temperatura = temperatura;
        this.poblacionMax = poblacionMax;
        this.reservaAlimentos = new Alimento[20]; /* limite arbitrario de "sacos de comida" */
        this.animales = new Animal[poblacionMax];
        this.tipoTerreno = tipoTerreno;
    }

    public boolean isEmpty() {
        for (int i = 0; i < poblacionMax; i++) {
            if (animales[i] != null) return false;
        }
        return true;
    }

    public Animal[] getHabitantes() {return this.animales;}

    public void addAnimal(Animal nuevoAnimal) {
        /* TODO: Reemplazar algunos de los if con exception handling? */
        /* Checkear si el habitat ya se encuentra lleno. */
        if (!this.isEmpty()) {
            System.out.println("El habitat: " + this + " se encuentra lleno, no se pueden agregar mas animales.");
        }
        else {
            /* Checkear si este animal es compatible con el habitat */
            if (CompatibleChecker.isCompatible(nuevoAnimal, this) == false) {
                System.out.println("Este animal no es compatible con este habitat.");
            }
            else{
                for (int i = 0; i < poblacionMax; i++) {
                    /* Para cada animal presente en el habitat, checkear si este es compatible con el animal nuevo.
                     *  TODO: Esto se podria optimizar checkeando solo las especies presentes en vez de animales individuales.*/
                    if (animales[i] != null && CompatibleChecker.isCompatible(animales[i], nuevoAnimal) == false) {
                        System.out.println("Este animal es incompatible con: " + animales[i].getClass());
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

    public Animal removeAnimal() {
        for (int i = 0; i < poblacionMax; i++) {
            if (animales[i] != null) {
                Animal copy = animales[i];
                animales[i] = null;
                return copy;
            }
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
}
