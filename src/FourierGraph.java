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
    private ScreenTools tools;
    private int[][] graphs;

    public FourierGraph(int w, int h) {
        this.fe = new FourierExpansion(20);
        grid = new Grid(w,h);
        this.theta = grid.getxMin();
        this.tools = new ScreenTools(grid);
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
        phi -= 2*delta;
        if (phi >= grid.getxMax()) {
            phi = grid.getxMin();
        }
        return points;
    }


    public int[] getNextComponentPoint(int k) {
        int[] coord = getNextComponentPoint(k,theta,0);
        theta += grid.getXRange()/grid.getWidth();
        if (theta >= grid.getxMax()) {
            theta = grid.getxMin();
        }
        return coord;
    }

    public int[] getNextComponentPoint(int k, double theta, double phi) {
         int[] coord = new int[2];
        coord[0] = grid.convertX(theta);
        coord[1] = grid.convertY(fe.getComponentRealValue(k,theta + phi));
        return coord;
    }


    public int[][] getComponent(int k) {
        int points[][] = new int[grid.getWidth()][2];
        double theta = grid.getxMin();
        double delta = grid.getXRange()/grid.getWidth();
        for (int i = 0 ; i < grid.getWidth() ; i++) {
            points[i] = getNextComponentPoint(k,theta,phi);
            theta += delta;
        }
        return points;
    }

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
        int[][] part1 = getComponent(1);
        int[][] part2 = getComponent(3);
        int[][] part3 = getComponent(4);
        int[][] part4 = getComponent(7);
        int[][] part5 = getComponent(9);
        int[][] part6 = getComponent(11);
        for (int i = 0 ; i <  grid.getWidth()-1 ; i++) {
            tools.drawLine(pixels, curve[i], curve[i+1],0xFFFFFF);
            tools.drawLine(pixels, part1[i], part1[i+1],0xFF0000);
            tools.drawLine(pixels, part2[i], part2[i+1],0x00FF00);
            tools.drawLine(pixels, part3[i], part3[i+1],0x0000FF);
            tools.drawLine(pixels, part4[i], part4[i+1],0xFFFF00);
            tools.drawLine(pixels, part5[i], part5[i+1],0x00FFFF);
            tools.drawLine(pixels, part6[i], part6[i+1],0xFF00FF);
            //pixels[curve[i][1]*grid.getWidth()+curve[i][0]] = 0xFFFFFF;
        }
    }

    public void createAxis(int[] pixels) {
        for (int i = 0 ; i < pixels.length ; i++) {
            pixels[i] = grid.getAxis()[i];
        }
    }
}
