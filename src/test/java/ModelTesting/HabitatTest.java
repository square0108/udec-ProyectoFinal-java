package ModelTesting;

import Model.Animal;
import Model.EntornosHabitat.Jungla;
import Model.Enumerations.EspeciesEnum;
import Model.Enumerations.TerrenoEnum;
import Model.Especies.Elefante;
import Model.Especies.Jirafa;
import Model.Especies.Leon;
import Model.Exceptions.AnimalesIncompatiblesException;
import Model.Exceptions.HabitatIncompatibleException;
import Model.Exceptions.HabitatLlenoException;
import Model.Habitat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HabitatTest {
    @Test
    @DisplayName("Añadir Animales a habitat")
    void addAnimalTest(){
        Habitat habitat = new Jungla();

        try {
            habitat.addAnimal(new Elefante());
        } catch (Exception e) {
            Assertions.fail();
        }
        Assertions.assertEquals(1,habitat.getArrayAnimales().size());

        for(int i = 0 ; i<3; i++){
            try {
                habitat.addAnimal(new Jirafa());
            } catch (Exception e) {
                System.err.println(e);
                Assertions.fail();
            }
        }
        Assertions.assertEquals(4,habitat.getArrayAnimales().size());
        Assertions.assertEquals(true,habitat.isFull());

        Assertions.assertThrows(HabitatLlenoException.class, () -> {
            habitat.addAnimal(new Leon());
        });

        //TODO: AGREGAR LOS CASOS, ANIMAL INCOMPATIBLE. (ANIMALES CON LOS QUE NO SE PUEDE JUNTAR Y
        // HABITAT EN EL QUE NO PUEDE ESTAR)

    }
    @Test
    @DisplayName("Remover Animales a habitat")
    void removeAnimalTest(){
        Habitat habitat = new Jungla();

        for(int i = 0 ; i<4; i++){
            try {
                habitat.addAnimal(new Jirafa());
            } catch (Exception e) {
                System.err.println(e);
                Assertions.fail();
            }
        }
        Assertions.assertEquals(true,habitat.isFull());
        habitat.removeAnimal(0);
        Assertions.assertEquals(3,habitat.getArrayAnimales().size());
        Assertions.assertEquals(false,habitat.isFull());

        habitat.removeAnimal(0);
        habitat.removeAnimal(0);
        habitat.removeAnimal(0);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            habitat.removeAnimal(0);
        });

    }
    @Test
    @DisplayName("Remover Alimento del Habitat")
    void removeAlimentoTest(){
        Habitat habitat = new Jungla();

        Assertions.assertNotNull(habitat.popAlimento());
        Assertions.assertNotNull(habitat.popAlimento());
        Assertions.assertNotNull(habitat.popAlimento());
        Assertions.assertNull(habitat.popAlimento());
    }
    @Test
    @DisplayName("Habitat rechaza animales nuevos si está lleno")
    void habitatMaxPoblacionTest() {
        class MaxPopONE extends Habitat {
            MaxPopONE() {
                super(25.0F,1, TerrenoEnum.TERRESTRE);
            }
        }
        MaxPopONE habitat = new MaxPopONE();
        Assertions.assertThrows(HabitatLlenoException.class, () -> {
            habitat.addAnimal(new Elefante());
            habitat.addAnimal(new Elefante());
        });
    }
    @Test
    @DisplayName("Habitat rechaza animales incompatibles con temperatura")
    void habitatTemperaturaIncorrectaTest() {
        class HaceFrio extends Habitat {
            HaceFrio() {
                super(-273.5F,20,TerrenoEnum.TERRESTRE);
            }
        }
        Habitat habitat = new HaceFrio();
        Assertions.assertThrows(HabitatIncompatibleException.class, () -> {
            habitat.addAnimal(new Elefante());
        });
    }
    @Test
    @DisplayName("Habitat rechaza animales incompatibles con tipo de terreno")
    void habitatTerrenoIncorrectoTest() {
        class UniversalFish extends Animal {
            UniversalFish() {
                super(TerrenoEnum.ACUATICO,0,-300f,300f);
            }
            @Override
            public EspeciesEnum[] animalesCompatibles() {
                return new EspeciesEnum[0];
            }
        }
        Jungla habitat = new Jungla();
        Assertions.assertThrows(HabitatIncompatibleException.class, () -> {
            habitat.addAnimal(new UniversalFish());
        });
    }
    @Test
    @DisplayName("Habitat rechaza animal que es incompatible con otro animal")
    void habitatAnimalesIncompatiblesTest() {
        class AnimalSolitario extends Animal {
            AnimalSolitario() {
                super(TerrenoEnum.TERRESTRE, 0, -99f, 99f);
            }
            @Override
            public EspeciesEnum[] animalesCompatibles() {
                return new EspeciesEnum[0];
            }
        }
        Jungla habitat = new Jungla();
        Assertions.assertDoesNotThrow(() -> {
            habitat.addAnimal(new AnimalSolitario());
        });
        Assertions.assertThrows(AnimalesIncompatiblesException.class, () -> {
            habitat.addAnimal(new AnimalSolitario());
        });
    }

}
