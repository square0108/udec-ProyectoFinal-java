package ModelTesting;
import Model.Animal;
import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.EstadosEnum;
import Model.Enumerations.TerrenoEnum;
import Model.Especies.*;

import Model.Especies.Leon;
import Model.EntornosHabitat.*;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatIncompatibleException;
import Model.Exceptions.HabitatLlenoException;
import Model.Habitat;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("Animales se MUEREN")
    void AnimalMuerteTest() {
        class Instakill extends Animal {
            Instakill() {
                super(TerrenoEnum.TERRESTRE, 9999, 0f, 30f);
            }
            @Override
            public EspeciesEnum[] animalesCompatibles() {
                return new EspeciesEnum[0];
            }
        }
        Animal ded = new Instakill();
        ded.ganarHambre();
        Assertions.assertEquals(EstadosEnum.MUERTO, ded.getEstado());
    }
    @Test
    @DisplayName("Animal cambia a estado COMIENDO")
    void AnimalComiendoTest() throws HabitatLlenoException, AnimalesIncompatiblesException, HabitatIncompatibleException {
        Animal animal = new Jirafa();
        Habitat habitat = new Jungla();
        habitat.addAnimal(animal);
        assertEquals(animal.getEstado(), EstadosEnum.PASIVO);
        animal.comer();
        assertEquals(animal.getEstado(), EstadosEnum.COMIENDO);
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
    void MuertosNoSeMueven() throws HabitatLlenoException, AnimalesIncompatiblesException, HabitatIncompatibleException {
        Leon L = new Leon();
        Jungla S = new Jungla();
        S.getReservaAlimentos().remove(0);
        S.getReservaAlimentos().remove(0);
        S.getReservaAlimentos().remove(0);
        S.addAnimal(L);
        boolean success = true;
        for (int i = 0; i < 40; i++) {
            L.ganarHambre();
        }
        for (int i = 0; i < 40; i++) {
            L.intentarMovimiento();
            if (L.getEstado() != EstadosEnum.MUERTO) success = false;
        }
        assertTrue(success);
    }
}
