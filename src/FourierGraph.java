/**
 * This is a class
 * Created 2020-10-28
 *
 * @author Magnus Silverdal
 */
public class FourierGraph {
    private FourierExpansion fe;
    private double theta;
    private Grid grid;
    private int[][] graphs;

    public FourierGraph(int w, int h) {
        this.fe = new FourierExpansion(20);
        grid = new Grid(w,h);
        this.theta = grid.getxMin();
    }

    // Need to figure out scaling and range issues... Temporary fix
    public int[] getnextpoint() {
        int[] coord = new int[2];
        coord[0] = grid.convertX(theta);
        coord[1] = grid.convertY(fe.getRealValue(theta));
        theta += grid.getXRange()/grid.getWidth();
        if (theta >= grid.getxMax()) {
            theta = grid.getxMin();
        }
        return coord;
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
            int[] coord = g.getnextpoint();
            System.out.println(coord[0] + "," + coord[1]);
        }
    }

    public void update(int[] pixels) {
        int[] point = getnextpoint();
        pixels[point[1]*grid.getWidth()+point[0]] = 0xFFFFFF;
    }

    public void createAxis(int[] pixels) {
        for (int i = 0 ; i < pixels.length ; i++) {
            pixels[i] = grid.getAxis()[i];
        }
    }
}
