
//import all the stuff we need/might need
//i'm not really sure i use all these things, but i know
//during planning that i thought i might at one time or another
import javax.swing.*;
//import for events
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import for color
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
//mouse things
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//keyboard things
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import a timer
import java.util.Timer;
import java.util.TimerTask;

public class CellGUI extends JFrame implements ActionListener {
    //these are the colors ill be used
    //green for alive
    //black for dead

    private static Color green = Color.green;
    private static Color black = Color.black;
    private static Cell theGame;
    //the components of the frame
    private static JFrame frame;
    private static JLabel label;
    //a boolean to track if we have started
    private static boolean isStarted = false;
    //create the timer for the clock
    //this counts down time
    public static Timer timer = new Timer();
    public static TimerTask task = new TimerTask() {
        public void run() {
            if (isStarted) {
                theGame.Step();
                label.repaint();
            }
        }
    };

    //add all the buttons and labels to the display
    public static void addToPane(Container pane) {
        //add the label im going to draw all over
        label = new JLabel() {
            //now paint the darn label with appropriate graphics
            public void paintComponent(Graphics g) {
                int i = 0;
                int z = 0;
                while (i < theGame.cellArray.length) {
                    z = 0;
                    while (z < theGame.cellArray.length) {
                        g.setColor(Color.black);
                        if (theGame.isAlive(i, z)) {
                            g.setColor(green);
                            g.fillRect(i * 10, z * 10, 10, 10);
                        }
                        if (!theGame.isAlive(i, z)) {
                            g.setColor(black);
                            g.fillRect(i * 10, z * 10, 10, 10);
                        }
                        z++;
                    }
                    i++;
                }
            }
        };

        //now we add an actioneventlistener that will call repaint()
        //it will get event location, figure out which square, and flip()
        //then call repaint()
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int posX = e.getX();
                int posY = e.getY();
                theGame.Flip(posX / 10, posY / 10);
                label.repaint();
            }
        });


        //set size of panel to 750x750 aloting 5x5 pixel squares for eacch
        //array slot
        label.setPreferredSize(new Dimension(750, 750));
        frame.getContentPane().add(label);
    }

    //make the gui and make it look nice
    public static void makeGUI() {
        frame = new JFrame("Jon's New Game Of Life - Hit 's' To Start/Stop");
        theGame = new Cell();
        timer.schedule(task, 0, 500);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    isStarted=!isStarted;
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    //call everything to create the gui
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                makeGUI();
            }
        });
    }

    //add the event listener to listen
    //it wouldnt compile without this empty method so i had to
    //include it.
    public void actionPerformed(ActionEvent evt) {
    }
}
