package Model;

import Model.Enumerations.TerrenoEnum;
import Model.Especies.Animal;

public abstract class Habitat {
    private float temperatura;
    private final int poblacionMax;
    private final Alimento[] reservaAlimentos;
    private final Animal[] animales;
    private TerrenoEnum tipoTerreno;

    public Habitat(float temperatura, int poblacionMax, TerrenoEnum tipoTerreno) {
        this.temperatura = temperatura;
        this.poblacionMax = poblacionMax;
        this.reservaAlimentos = new Alimento[20]; /* limite arbitrario de "sacos de comida" */
        this.animales = new Animal[poblacionMax];
        this.tipoTerreno = tipoTerreno;
    }

    public boolean IsEmpty() {
        for (int i = 0; i < poblacionMax; i++) {
            if (animales[i] != null) return false;
        }
        return true;
    }

    public Animal[] getAnimalesHabitantes() {return this.animales;}

    public void addAnimal(Animal animal) {
        if (!this.IsEmpty()) {
            System.out.println("El habitat: " + this + "\nse encuentra lleno. Remueve algun animal.");
        }
        else {
            for (int i = 0; i < poblacionMax; i++) {
                if (animales[i] == null) {
                    animales[i] = animal;
                    break;
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
        System.out.println("Error, este recinto se encuentra vacio.");
        return null;
    }
}
