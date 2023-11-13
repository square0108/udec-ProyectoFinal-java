package View;

import javax.swing.*;
import java.awt.*;

public class MainJFrame extends JFrame {
    public MainJFrame() {
        this.setLayout(new BorderLayout());
        this.setSize(1280,720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.ORANGE);

        this.add(new MessagePanel(), BorderLayout.SOUTH);
        this.add(new ZooPanel(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(), BorderLayout.WEST);
        JPanel NorthBorder = new JPanel();
        NorthBorder.add(new JLabel(" "));
        NorthBorder.setBackground(Color.BLACK);
        this.add(NorthBorder, BorderLayout.NORTH);

        this.setVisible(true);
    }
}
