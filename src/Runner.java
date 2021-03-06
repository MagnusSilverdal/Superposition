import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This is a class
 * Created 2020-10-28
 *
 * @author Magnus Silverdal
 */
public class Runner implements Runnable {
    private String title = "Program";
    private FourierGraph fourierGraph;
    private Screen screen;
    private Thread thread;
    private boolean running = false;
    private int width;
    private int height;
    private int scale;
    private int fps = 60;
    private int ups = 60;

    public Runner(int w, int h, int s) {
        this.width = w;
        this.height = h;
        this.scale = s;
        screen = new Screen(width,height, scale);
        fourierGraph = new FourierGraph(w,h);
        screen.getFrame().addKeyListener(new FourierKeyListener(fourierGraph));
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        double fpsInterval = 1000000000.0 / fps;
        double upsInterval = 1000000000.0 / ups;
        double deltaRender = 0;
        double deltaUpdate = 0;
        int frames = 0;
        int updates = 0;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        fourierGraph.createAxis(screen.getPixels());
        while (running) {
            long now = System.nanoTime();
            deltaRender += (now - lastTime) / fpsInterval;
            deltaUpdate += (now - lastTime) / upsInterval;
            lastTime = now;

            while(deltaUpdate >= 1) {
                fourierGraph.update(screen.getPixels());
                updates++;
                deltaUpdate--;
            }

            while(deltaRender >= 1) {
                screen.draw();
                frames++;
                deltaRender--;
            }

            if(System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                screen.getFrame().setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    // For testing
    public static void main(String[] args) {
        Runner program = new Runner(1200, 800, 1);
        program.start();
    }
}
