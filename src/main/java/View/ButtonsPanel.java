package View;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    private JButton HabitatButton;
    private JButton AnimalButton;
    private JButton FoodButton;

    public ButtonsPanel() {
        this.HabitatButton = new JButton("Insertar Habitat");
        this.AnimalButton = new JButton("Agregar animal a habitat");
        this.FoodButton = new JButton("Agregar comida a habitat");
        this.setLayout(new GridLayout(4,1));

        this.add(HabitatButton);
        this.add(AnimalButton);
        this.add(FoodButton);
    }
}
