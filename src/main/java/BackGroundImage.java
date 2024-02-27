import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BackGroundImage extends JPanel {

    private Image backgroundImage;

    // Constructor to set the image as background
    public BackGroundImage(String filename) {
        try {
            backgroundImage = ImageIO.read(new File(filename));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
