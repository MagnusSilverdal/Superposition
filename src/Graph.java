/**
 * This is a class
 * Created 2020-10-28
 *
 * @author Magnus Silverdal
 */
public class Graph {
    private FourierExpansion fe;
    private double theta;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private int width;
    private int height;

    public Graph(int w, int h) {
        this.width = w;
        this.height = h;
        this.fe = new FourierExpansion(10);
        this.theta = 0;
        this.xMin = 0;
        this.xMax = 4*Math.PI;
        this.yMin = 0.2;
        this.yMax = 1.5;
    }

    // Need to figure out scaling and range issues... Temporary fix
    public int[] getnextpoint() {
        int[] coord = new int[2];
        coord[0] = (int)(width*(theta/xMax));
        coord[1] = (int)(height*(yMin + (0.8*fe.getRealValue(theta)/yMax)));
        theta += xMax/width;
        if (theta >= xMax) {
            theta = 0;
        }
        return coord;
    }

    // for Testing
    public static void main(String[] args) {
        Graph g = new Graph(320,200);
        for (int i = 0 ; i < 320 ; i++) {
            int[] coord = g.getnextpoint();
            System.out.println(coord[0] + "," + coord[1]);
        }
    }
}
