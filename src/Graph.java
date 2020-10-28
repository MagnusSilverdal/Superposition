/**
 * This is a class
 * Created 2020-10-28
 *
 * @author Magnus Silverdal
 */
public class Graph {
    private FourierExpansion fe;
    private double theta;
    private double xRange;
    private double yRange;
    private int width;
    private int height;

    public Graph(int w, int h) {
        this.width = w;
        this.height = h;
        this.fe = new FourierExpansion(10);
        this.theta = 0;
        this.xRange = 4*Math.PI;
        this.yRange = 2;
    }

    public int[] getnextpoint() {
        int[] coord = new int[2];
        coord[0] = (int)(width*(theta/xRange));
        coord[1] = height - (int)(height*(fe.getRealValue(theta)/yRange));
        theta += xRange/width;
        if (theta >= xRange) {
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
