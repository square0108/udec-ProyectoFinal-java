package Vista;

import Vista.Enumerations.EnumCursor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControllerTest {
    private static VistaPrincipal principal;
    private static int cursor_state;

    public ControllerTest(VistaPrincipal vistaPrincipal) { principal = vistaPrincipal;
    }

    public static void addAnimal(){
    }
    public static void changeCursor(EnumCursor tipo){
        principal.setCursor(tipo);
        // setear dentro de controller una variable interna que muestre el estado del cursor, sirve mas que nada
        // cuando por ejemplo queremos añadir algo y no le achuntamos al habitat, que el cursor vuelva a la normalidad
        // y no deje añadir ningun animal.
    }

}
