package ModelTesting;
import Model.Especies.*;

import Model.Especies.Leon;
import Model.EntornosHabitat.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    private Leon manuel = new Leon();
    private Jirafa pepe = new Jirafa();
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
    void MuertosNoSeMueven() {
        Leon L = new Leon();
        Sabana S = new Sabana();
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
