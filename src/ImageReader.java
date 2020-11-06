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
        System.out.println("Curve");
        System.out.println(ir.toString());
        int[] data = ir.getCurve();
        // Transform need real and complex part even if ours i purely real
        int[] complexData = new int[data.length];
        System.out.println("transform:");
        double[][] transform = DFT.transform(data,complexData);
        for (int i = 0 ; i < data.length ; i++) {
            System.out.println(transform[0][i] + ", " + transform[1][i]);
        }
        System.out.println("recreated curve");
        for (int x = 0 ; x < 1024 ; x++) {
            double y = 0;
            for (int i = 0 ; i < 1024 ; i++) {
                y +=  transform[0][i] * Math.cos(2*Math.PI*i*x/1024) + transform[1][i] * Math.sin(2*Math.PI*i*x/1024);
            }
            System.out.println(x + ", " + y);
        }

    }

}
