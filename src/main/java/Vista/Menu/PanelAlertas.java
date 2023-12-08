package Vista.Menu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PanelAlertas {
    private final int WIDTH = 380;
    private final int HEIGHT = 180;
    private BufferedImage fondo;
    private Point position;
    private static String frase;

    public PanelAlertas(int x, int y){
        this.position = new Point(x,y);
        try {
            fondo = ImageIO.read(new File("src/main/java/resources/icons/panelsbackgroud.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la Textura de ALERTAS");
        }
        this.frase = "Zoo Simulator";


    }
    public static void changeText(String string){
        frase = string;
    }

    /**
     * Divide un string en distintas líneas, esto para que al momento de imprimir frases el texto sea legible.
     * @param font Fuente a utilizar.
     * @param maxWidth Cantidad de píxeles en los cuales se debe mostrar la frase.
     * @return Lista con frases separadas en frases más pequeñas.
     */
    private java.util.List<String> getLines(Font font, int maxWidth) {
        List<String> lines = new ArrayList<>();
        String[] words = frase.split("\\s");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            String testLine = currentLine + word + " ";
            if (font.getStringBounds(testLine, new FontRenderContext(null, true, true)).getBounds().getWidth() > maxWidth) {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word + " ");
            } else {
                currentLine = new StringBuilder(testLine);
            }
        }

        lines.add(currentLine.toString());
        return lines;
    }

    /**
     * Carga una fuente guardada de forma interna.
     * @param path Dirección de la fuente dentro de la carpeta del proyecto.
     * @param style Estilo que la fuente usara, puede ser PLAIN, BOLD, ITALIC, ETC.
     * @param size Tamaño en el que se mostrara la fuente.
     * @return fuente, esta se guarda en el tipo "Font".
     */
    private Font loadCustomFont(String path, int style, int size) {
        try {
            // Carga la fuente desde el archivo .otf
            File fontFile = new File(path);
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            return baseFont.deriveFont(style, size);
        } catch (FontFormatException | IOException e) {
            System.out.println("NO SE PUDO CARGAR LA FUENTE CUSTOM");
            // Devuelve una fuente predeterminada en caso de error
            return new Font("Serif", Font.PLAIN, size);
        }
    }

    /**
     * Dibuja los elementos de panel alertas dadas unas coordenadas
     * @param g
     * @param imageObserver
     */
    public void draw(Graphics g, ImageObserver imageObserver){
        Graphics2D g2 = (Graphics2D) g;

        Font customFont = loadCustomFont("src/main/java/resources/Candy_Beans.otf", Font.PLAIN, 25);
        g2.setFont(customFont);

        g2.setColor(Color.WHITE);

        // Divide la frase con la función getLines
        List<String> lines = getLines(customFont, WIDTH -20);

        // Calcula la altura total del texto
        int totalHeight = lines.size() * g2.getFontMetrics().getHeight();

        // Se calcula la altura total del texto
        int y = (HEIGHT - totalHeight) / 2 + g2.getFontMetrics().getAscent() + position.y;

        // Dibujamos el fondo
        g2.drawImage(fondo,position.x, position.y,WIDTH,HEIGHT, imageObserver);

        // Dibuja cada línea del texto
        for (String line : lines) {
            int textWidth = g2.getFontMetrics().stringWidth(line);
            int x = (WIDTH - textWidth) / 2 + position.x;
            g2.drawString(line, x, y);
            y += g2.getFontMetrics().getHeight();
        }

    }
}
