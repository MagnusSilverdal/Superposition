import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

/**
 * This is a class
 * Created 2020-11-06
 *
 * @author Magnus Silverdal
 */
public class ImageReader {
    private BufferedImage image;
    private int[] pixels;
    private int [] points;

    public ImageReader(String filename) {
        BufferedImage convertedImage = null;
        try{
            image = ImageIO.read(new File(filename));
            convertedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            convertedImage.getGraphics().drawImage(image, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int w = convertedImage.getWidth();
        int h = convertedImage.getHeight();
        pixels = ((DataBufferInt) convertedImage.getRaster().getDataBuffer()).getData();
        points = new int[w];
        for (int i = 0 ; i < w ; i++) {
            for (int j = 0 ; j < h ; j++) {
                if (pixels[j*w+i] == 0) {
                    points[i] = h-j;
                    break;
                }
            }
        }
    }

    public int[] getCurve() {
        return points;
    }

    public String toString() {
        String result = "";
        for (int i = 0 ; i < points.length ; i++) {
            result += i+", "+points[i] + "\n";
        }
        return result;
    }

    public static void main(String[] args) {
        ImageReader ir = new ImageReader("curve.png");
        System.out.println(ir.toString());
    }

}
