package View;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {
    private JLabel MessageLog;

    public MessagePanel() {
        this.MessageLog = new JLabel("Mensajes de confirmacion y notificaciones.");
        this.setBackground(Color.BLACK);
        this.add(MessageLog);
        MessageLog.setForeground(Color.WHITE);
    }
}
