package ModelTesting;
import Model.Animal;
import Model.Especies.*;

import Model.Especies.Leon;
import Model.EntornosHabitat.*;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatLlenoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnimalTest {
    private final Leon manuel = new Leon();
    private final Jirafa pepe = new Jirafa();
    void PrintMovimiento(Animal animal) {
        animal.intentarMovimiento();
        System.out.println(animal + ", " + animal.getEstado());
    }
    void PrintPorcentajeComida(Animal animal) {
        System.out.println(animal + " : " + animal.getPorcentajeComida());
    }
    void PrintEstado(Animal animal) {
        System.out.println(animal.getEstado());
    }

    @Test
    @DisplayName("Movimiento a traves del generador de numeros de Animal")
    void MovimientoTest() {
        for (int i = 0; i < 5; i++) {
            PrintMovimiento(manuel);
            PrintMovimiento(pepe);
        }
    }

    @Test
    void MuertosNoSeMueven() throws HabitatLlenoException, AnimalesIncompatiblesException {
        Leon L = new Leon();
        Jungla S = new Jungla();
        S.getReservaAlimentos().remove(0);
        S.getReservaAlimentos().remove(0);
        S.getReservaAlimentos().remove(0);
        S.addAnimal(L);
        for (int i = 0; i < 20; i++) {
            L.intentarMovimiento();
            PrintPorcentajeComida(L);
            PrintEstado(L);
            L.ganarHambre();
        }
    }
}
