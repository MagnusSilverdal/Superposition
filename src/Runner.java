import javax.swing.*;

/**
 * This is a class
 * Created 2020-10-28
 *
 * @author Magnus Silverdal
 */
public class Runner implements Runnable{
    private Screen screen;
    private Thread thread;
    private boolean running = false;
    private int width;
    private int height;
    private int scale;

    public Runner(int w, int h, int s) {
        this.width = w;
        this.height = h;
        this.scale = s;
        screen = new Screen(width,height, scale);
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
        while (running) {
        }
        stop();
    }

    // For testing
    public static void main(String[] args) {
        Runner program = new Runner(320, 200, 2);
        program.test();
        program.start();
    }

    private void test() {
        screen.testScreen();
    }
}
