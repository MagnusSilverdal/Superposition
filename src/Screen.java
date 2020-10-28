import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * This is a class
 * Created 2020-10-28
 *
 * @author Magnus Silverdal
 */
public class Screen extends Canvas{
    private JFrame frame;
    private BufferedImage image;
    private int[] pixels;

    public Screen(int w, int h, int scale) {
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        Dimension size = new Dimension(scale*w, scale*h);
        setPreferredSize(size);
        frame = new JFrame();
    }

    private void draw() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    // For testing
    public static void main(String[] args) {
        Screen s = new Screen(320,200,2);
        s.frame.add(s);
        s.frame.pack();
        s.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        s.frame.setLocationRelativeTo(null);
        s.frame.setVisible(true);
        s.testScreen();
    }

    private void testScreen() {
        pixels[100*320+10] = 0xFF0000;
        pixels[100*320+11] = 0xFF0000;
        pixels[101*320+10] = 0xFF0000;
        pixels[101*320+11] = 0xFF0000;
        while (true) {
            draw();
        }
    }

}
