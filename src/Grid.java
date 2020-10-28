/**
 * This is a class
 * Created 2020-10-28
 *
 * @author Magnus Silverdal
 */
public class Grid {
    private int width;
    private int height;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private int[] axis;

    public Grid(int w, int h) {
        this.width = w;
        this.height = h;
        this.xMin = 0;
        this.xMax = 2*Math.PI;
        this.yMin = -0.2;
        this.yMax = 1.2;
        axis = new int[width*height];
        drawAxis();
    }

    private void drawAxis() {
        int y0 = convertY(0);
        int x0 = convertX(0);

        for (int i = 0 ; i < width ; i++) {
            axis[y0*width+i] = 0xFFFF00;
        }
        for (int j = 0 ; j < height ; j++) {
            axis[j*width+x0] = 0xFF00FF;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getxMin() {
        return xMin;
    }

    public double getxMax() {
        return xMax;
    }

    public double getyMin() {
        return yMin;
    }

    public double getyMax() {
        return yMax;
    }

    public int[] getAxis() {
        return axis;
    }

    public double getXRange() {
        return xMax-xMin;
    }

    public double getYRange() {
        return yMax-yMin;
    }

    public int convertX(double x) {
        return (int)(width*((x - xMin)/(xMax-xMin)));
    }

    public int convertY(double y) {
        return height - (int)(height*((y - yMin)/(yMax - yMin)));
    }
}
