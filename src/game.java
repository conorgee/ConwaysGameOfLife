import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Iterator;

public class game extends JFrame implements Runnable, MouseListener {



    // member data
    public static final Dimension WindowSize = new Dimension(800, 800); // this is now public so that 
    private BufferStrategy strategy;
    private Graphics offscreenBuffer;
    private boolean isInitialised = false;
    private static String workingDirectory;
    private boolean[][] arr = new boolean[40][40];
    public game() {
        //Display the window, centred on the screen
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width / 2 - WindowSize.width / 2;
        int y = screensize.height / 2 - WindowSize.height / 2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);
        this.setTitle("Conways Game Of Life");


        // load images from disk. Make sure you have the path right!

        // create and start our animation thread
        Thread t = new Thread(this);
        t.start();

        // send keyboard events arriving into this JFrame back to its own event handlers
        addMouseListener(this);

        // initialise double-buffering
        createBufferStrategy(2);
        strategy = getBufferStrategy();
        offscreenBuffer = strategy.getDrawGraphics();
        isInitialised = true;
    }

    // thread's entry point
    public void run() {
        while (1 == 1) {
            // 1: sleep for 1/50 sec
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {}
            // 2: animate game objects, if the game state is 'in progress'




            this.repaint();
        }
    }
    // Three Keyboard Event-Handler methods



    // application's paint method
    public void paint(Graphics g) {
        if (!isInitialised)
            return;
        g = offscreenBuffer; // draw to offscreen buffer
        // clear the canvas with a big black rectangle
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WindowSize.width, WindowSize.height);
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length; col++) {
                if (arr[row][col] == true) {
                    int v = row * 20;
                    int c = col * 20;
                    if (v >= 800) {
                        v = 780;
                    }
                    if (c >= 800) {
                        c = 780;
                    }
                    g.setColor(Color.WHITE);
                    g.fillRect(v, c, 20, 20);


                }
            }
        }

        // flip the buffers
        strategy.show();
    }
    // application entry point

    @Override
    public void mouseClicked(MouseEvent e) {
        int n = e.getClickCount();
        int x = ((int) Math.floor(e.getX())) / 20;
        int y = ((int) Math.floor(e.getY())) / 20;
        if (arr[x][y] == true) {
            if (n % 2 == 0) {

            } else {

                arr[x][y] = false;

            }
        }
        if (arr[x][y] == false) {
            if (n % 2 == 0) {

            } else {

                arr[x][y] = true;

            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) {
        workingDirectory = System.getProperty("user.dir");
        System.out.println("Working Directory = " + workingDirectory);
        game w = new game();
    }



}
