/**
 * This is a class
 * Created 2020-10-28
 *
 * @author Magnus Silverdal
 */
public class FourierGraph {
    private FourierExpansion fe;
    private double theta;
    private double phi;

    private Grid grid;
    private int[][] graphs;

    public FourierGraph(int w, int h) {
        this.fe = new FourierExpansion(20);
        grid = new Grid(w,h);
        this.theta = grid.getxMin();
    }

    public int[] getNextPoint() {
        int[] coord = getNextPoint(theta,0);
        theta += grid.getXRange()/grid.getWidth();
        if (theta >= grid.getxMax()) {
            theta = grid.getxMin();
        }
        return coord;
    }

    public int[] getNextPoint(double theta, double phi) {
        int[] coord = new int[2];
        coord[0] = grid.convertX(theta);
        coord[1] = grid.convertY(fe.getRealValue(theta + phi));
        return coord;
    }

    public int[][] getCurve() {
        int points[][] = new int[grid.getWidth()][2];
        double theta = grid.getxMin();
        double delta = grid.getXRange()/grid.getWidth();
        for (int i = 0 ; i < grid.getWidth() ; i++) {
            points[i] = getNextPoint(theta,phi);
            theta += delta;
        }
        phi += delta;
        if (phi >= grid.getxMax()) {
            phi = grid.getxMin();
        }
        return points;
    }

    /*public int[] getComponent(int k) {
        int[] coord = new int[2];
        coord[0] = (int)(width*(theta/xMax));
        coord[1] = (int)(height*(yMin + (0.8*fe.getComponent(k, theta)/yMax)));
        return coord;
    }*/

    // for Testing
    public static void main(String[] args) {
        FourierGraph g = new FourierGraph(320,200);
        for (int i = 0 ; i < 320 ; i++) {
            int[] coord = g.getNextPoint();
            System.out.println(coord[0] + "," + coord[1]);
        }
    }

    public void update(int[] pixels) {
        //int[] point = getNextPoint();
        //pixels[point[1]*grid.getWidth()+point[0]] = 0xFFFFFF;
        for (int i = 0 ; i < pixels.length ; i++) {
            pixels[i] = 0;
        }
        createAxis(pixels);
        int[][] curve = getCurve();
        for (int i = 0 ; i <  grid.getWidth() ; i++) {
            pixels[curve[i][1]*grid.getWidth()+curve[i][0]] = 0xFFFFFF;
        }
    }

    public void createAxis(int[] pixels) {
        for (int i = 0 ; i < pixels.length ; i++) {
            pixels[i] = grid.getAxis()[i];
        }
    }
}
