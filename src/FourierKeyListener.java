import netscape.security.UserTarget;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This is a class
 * Created 2020-11-01
 *
 * @author Magnus Silverdal
 */
public class FourierKeyListener implements KeyListener {
    private FourierGraph fourierGraph;

    public FourierKeyListener(FourierGraph fourierGraph) {
        this.fourierGraph = fourierGraph;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_C) {
            fourierGraph.getState().setContinous();
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_U) {
            fourierGraph.getState().setDescrete();
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            fourierGraph.changeExpansion(fourierGraph.getState().getCurves()-1);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            fourierGraph.changeExpansion(fourierGraph.getState().getCurves()+1);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_1) {
            fourierGraph.changeExpansion(1);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_2) {
            fourierGraph.changeExpansion(2);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_3) {

        } else if (keyEvent.getKeyCode() == KeyEvent.VK_4) {

        } else if (keyEvent.getKeyCode() == KeyEvent.VK_5) {

        } else if (keyEvent.getKeyCode() == KeyEvent.VK_6) {

        } else if (keyEvent.getKeyCode() == KeyEvent.VK_7) {

        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }
}
