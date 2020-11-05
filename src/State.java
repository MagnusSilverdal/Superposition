/**
 * This is a class
 * Created 2020-11-01
 *
 * @author Magnus Silverdal
 */
public class State {
    private boolean continous = false;
    private boolean onlyGraph = false;
    private int numCurves = 5;

    public int getCurves() {
        return numCurves;
    }

    public void setCurves(int numCurves) {
        this.numCurves= numCurves;
    }

    public void setContinous() {
        this.continous = true;
    }

    public void setDescrete() {
        this.continous = false;
    }

    public void setOnlyGraph() {
        this.onlyGraph = true;
    }

    public void setAllCurves() {
        this.onlyGraph = false;
    }

    public boolean drawOnlyGraph() {
        return onlyGraph;
    }

    public boolean drawContinous() {
        return continous;
    }


}


