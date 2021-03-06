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
    private State state;
    private ScreenTools tools;
    private int[][] graphs;
    private int[] colors = {0xFF0000, 0x00FF00, 0x0000FF, 0xFFFF00, 0x00FFFF, 0xFF00FF,0x880000, 0x008800, 0x000088,
            0x888800, 0x008888, 0x880088};

    public FourierGraph(int w, int h) {
        this.grid = new Grid(w,h);
        this.state = new State();
        this.fe = new FourierExpansion(state.getCurves()+1);
        this.theta = grid.getxMin();
        this.tools = new ScreenTools(grid);
    }

    public void changeExpansion() {
        this.fe = new FourierExpansion(state.getCurves());
    }

    public void changeExpansion(int numCurves) {
        state.setCurves(numCurves);
        this.fe = new FourierExpansion(state.getCurves());
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

    public State getState() {
        return state;
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

    public void update(int[] pixels) {
        for (int i = 0 ; i < pixels.length ; i++) {
            pixels[i] = 0;
        }
        createAxis(pixels);
        int[][] curve = getCurve();

        for (int i = 0; i < grid.getWidth() - 1; i++) {
            if (state.drawContinous()) {
                tools.drawLine(pixels, curve[i], curve[i + 1], 0xFFFFFF);
            } else {
                pixels[curve[i][1]*grid.getWidth()+curve[i][0]] = 0xFFFFFF;
            }
        }

        if (!state.drawOnlyGraph()) {
            int numCurves = state.getCurves();
            if (numCurves > colors.length*2) {
                numCurves = colors.length*2;
            }
            int[][][] part = new int[numCurves][][];
            for (int i = 0 ; i < numCurves ; i++) {
                part[i] = getComponent(i);
            }
            for (int i = 0 ; i < grid.getWidth() - 1 ; i++) {
                if (state.drawContinous()) {
                    for (int j = 0 ; j < numCurves ; j++) {
                        tools.drawLine(pixels, part[j][i], part[j][i + 1], colors[j/2]);
                    }
                } else {
                    for (int j = 0 ; j < numCurves ; j++) {
                        pixels[part[j][i][1] * grid.getWidth() + part[j][i][0]] = colors[j/2];
                    }
                }
            }
        }
    }

    public void createAxis(int[] pixels) {
        for (int i = 0 ; i < pixels.length ; i++) {
            pixels[i] = grid.getAxis()[i];
        }
    }

    // for Testing
    public static void main(String[] args) {
        FourierGraph g = new FourierGraph(320,200);
        for (int i = 0 ; i < 320 ; i++) {
            int[] coord = g.getNextPoint();
            System.out.println(coord[0] + "," + coord[1]);
        }
    }
}
