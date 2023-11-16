package Model;

public abstract class Habitat {
    private float temperatura;
    private int poblacionMax;
    private Alimento[] reservaAlimentos;
    private Animal[] animales;
    private TerrenoEnum tipoTerreno;

    public Habitat(float temperatura, int poblacionMax) {
        this.temperatura = temperatura;
        this.poblacionMax = poblacionMax;
        this.reservaAlimentos = new Alimento[20]; /* limite arbitrario de "sacos de comida" */
        this.animales = new Animal[poblacionMax];
    }
    private boolean IsEmpty() {
        for (int i = 0; i < poblacionMax; i++) {
            if (animales[i] != null) return false;
        }
        return true;
    }
    public void addAnimal(Animal animal) {
        if (!this.IsEmpty()) {
            for (int i = 0; i < poblacionMax; i++) {
                if (animales[i] == null) {
                    animales[i] = animal;
                    break;
                }
            }
        }
        else {
            System.out.println("Error, este recinto se encunetra lleno.");
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
