package View;

import javax.swing.*;
import java.awt.*;

public class HabitatPanel extends JPanel {
    private JPanel InfoPanel;
    private JPanel HabitatGrid;

    public HabitatPanel() {
        this.InfoPanel = new JPanel();
        this.HabitatGrid = new JPanel();
        JLabel info = new JLabel("Comida: 100/100 ; Poblacion: 0/5 ; Tipo: Jungla"); /* temp */

        this.setLayout(new BorderLayout());
        HabitatGrid.setLayout(new GridLayout(10,10));
        InfoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        HabitatGrid.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        InfoPanel.add(info);
        this.add(InfoPanel, BorderLayout.NORTH);
        this.add(HabitatGrid, BorderLayout.CENTER);
    }
}
