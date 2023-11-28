package Vista.Menu;

import Model.Enumerations.AnimalEnum;
import Vista.Enumerations.BotonesEnum;
import Vista.Enumerations.EnumEstadosBoton;
import Vista.Interface.BotonClickListener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;


public class PanelSeleccionAnimal implements BotonClickListener {
    private final int WIDTH = 380;
    private final int HEIGHT = 200;
    private AnimalEnum animal = AnimalEnum.ELEFANTE;
    private BufferedImage animalIcon;
    protected BotonFlecha flechaDer;
    protected BotonFlecha flechaIzq;
    private Point position;

    public PanelSeleccionAnimal(int x, int y) {
        this.position = new Point(x, y);
        flechaDer = new BotonFlecha(this.position, WIDTH - 90, 10, BotonesEnum.FLECHADERECHA);
        flechaIzq = new BotonFlecha(this.position, 10, 10, BotonesEnum.FLECHAIZQUIERDA);

        flechaDer.setBotonClickListener(this);
        flechaIzq.setBotonClickListener(this);

        setAnimalIcon();
    }

    public void draw(Graphics g, ImageObserver imageObserver) {
        g.setColor(Color.GRAY);
        g.fillRect((int) this.position.getX(), (int) this.position.getY(), WIDTH, HEIGHT);
        flechaIzq.draw(g, imageObserver);
        flechaDer.draw(g, imageObserver);

        // Dibuja el panel intermedio directamente
        g.drawImage(animalIcon, (int) (this.position.getX() + WIDTH)/2-50, (int) this.position.getY() + 10,
                100, 100, imageObserver);
    }

    public void cambiarSiguienteAnimal() {
        animal = animal.siguiente();
        setAnimalIcon();
    }
    public void cambiarAnteriorAnimal() {
        animal = animal.anterior();
        setAnimalIcon();
    }

    @Override
    public void onBotonClick() {
        if (flechaDer.getState() == EnumEstadosBoton.CLICK) {
            cambiarSiguienteAnimal();
        } else if (flechaIzq.getState() == EnumEstadosBoton.CLICK) {
            cambiarAnteriorAnimal();
        }
    }

    public void setAnimalIcon() {
        String texture_path = "src/main/java/resources/icons/" + animal.getTexturePath();
        try {
            this.animalIcon = ImageIO.read(new File(texture_path));
        } catch (IOException e) {
            System.out.println("TEXTURA NO ENCONTRADA!!!!! (PanelSeleccionAnimal)");
        }
    }
}
