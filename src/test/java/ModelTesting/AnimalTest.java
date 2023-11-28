package ModelTesting;
import Model.Especies.*;

import Model.Especies.Leon;
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

    @Test
    @DisplayName("Movimiento a traves del generador de numeros de Animal")
    void MovimientoTest() {
        for (int i = 0; i < 5; i++) {
            PrintMovimiento(manuel);
            PrintMovimiento(pepe);
        }
    }
}
