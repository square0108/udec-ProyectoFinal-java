package ModelTesting;

import Model.EntornosHabitat.Jungla;
import Model.Especies.Elefante;
import Model.Especies.Jirafa;
import Model.Especies.Leon;
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
}
