/**
 * This is a class
 * Created 2020-11-01
 *
 * @author Magnus Silverdal
 */
public class ScreenTools {
    private Grid grid;

    public ScreenTools(Grid g) {
        this.grid = g;
    }
    /**
     * Method to draw the line on the screen. This is an implementation of the <a href="https://en.wikipedia.org/wiki/Digital_differential_analyzer_(graphics_algorithm)">DDA algorithm</a>
     * @see <a href="https://en.wikipedia.org/wiki/Digital_differential_analyzer_(graphics_algorithm)">DDA algorithm</a>
     */
    public void drawLine(int[] pixels, int[] start,int[] stop, int col) {
        int dx,dy,k;
        double xc,yc,x,y,steps;
        dx=stop[0]-start[0];
        dy=stop[1]-start[1];
        if(Math.abs(dx)>Math.abs(dy))
            steps=Math.abs(dx);
        else
            steps=Math.abs(dy);
        xc=(dx/steps);
        yc=(dy/steps);
        x=start[0];
        y=start[1];
        for(k=1;k<=steps;k++) {
            x=x+xc;
            y=y+yc;
            drawPoint(pixels, (int)x, (int)y, col);
        }
    }

    public void drawPoint(int[] pixels,int x, int y, int col) {
        pixels[y*grid.getWidth() + x] = col;
    }
}
