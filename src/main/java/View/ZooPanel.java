package View;

import javax.swing.*;
import java.awt.*;

public class ZooPanel extends JPanel {
    private JPanel slots[]; /* array fijo con las celdas para insertar habitats */

    public ZooPanel() {
        this.slots = new JPanel[6];
        this.setLayout(new GridLayout(2,3));

        for (int i = 0; i < slots.length; i++) {
            slots[i] = new HabitatPanel();
            this.add(slots[i]);
        }
    }
}
