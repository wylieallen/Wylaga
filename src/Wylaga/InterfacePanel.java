package Wylaga;

import Wylaga.Util.KeyRole;
import Wylaga.Overstates.Game.GameState;
import Wylaga.Overstates.Overstate;
import Wylaga.Overstates.Menus.MenuFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class InterfacePanel extends JPanel implements KeyListener
{
    private javax.swing.Timer renderTimer;
    private java.util.Timer gameTimer;

    private Overstate activeOverstate;
    private Overstate mainMenu;
    private Overstate gameState;

    private Map<Integer, KeyRole> keyMap;

    private long prevTime;
    private long frameCount = 0;
    private double millisElapsed = 0;

    public InterfacePanel(int width, int height) {

        activeOverstate = MenuFactory.makeStartMenu(this::deployMainMenu);
        mainMenu = MenuFactory.makeMainMenu(this::startGame);
        gameState = new GameState(this::returnToMainMenu);

        initializeKeyMap();

        // 1 frame per 17 ms ~= 60 frames per second

        int targetDelay = 17;

        renderTimer = new javax.swing.Timer(targetDelay, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                renderTimer.stop();

                // Framerate monitoring:

                long curTime = System.currentTimeMillis();
                long delta = curTime - prevTime;
                millisElapsed += delta;
                double msPerFrame = millisElapsed / ++frameCount;
                if(delta > 28)
                {
                    System.out.println("!");
                }
                System.out.printf(frameCount + " " + renderTimer.getDelay() + "d " + delta + " :  %.2f : %.2f \n", msPerFrame, 1000 / msPerFrame);
                prevTime = curTime;


                activeOverstate.updateView();
                repaint();

                renderTimer.restart();
            }
        });


        gameTimer = new java.util.Timer();


        gameTimer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                activeOverstate.updateModel();
            }
        }, 0, 20);

        prevTime = System.currentTimeMillis();

        renderTimer.start();
    }

    private void initializeKeyMap() {
        keyMap = new HashMap<>();
        keyMap.put(KeyEvent.VK_UP, KeyRole.UP);
        keyMap.put(KeyEvent.VK_DOWN, KeyRole.DOWN);
        keyMap.put(KeyEvent.VK_LEFT, KeyRole.LEFT);
        keyMap.put(KeyEvent.VK_RIGHT, KeyRole.RIGHT);

        keyMap.put(KeyEvent.VK_W, KeyRole.UP);
        keyMap.put(KeyEvent.VK_A, KeyRole.LEFT);
        keyMap.put(KeyEvent.VK_S, KeyRole.DOWN);
        keyMap.put(KeyEvent.VK_D, KeyRole.RIGHT);

        keyMap.put(KeyEvent.VK_SPACE, KeyRole.ACTION);
        keyMap.put(KeyEvent.VK_CONTROL, KeyRole.SPECIAL);
        keyMap.put(KeyEvent.VK_SHIFT, KeyRole.SPECIAL);

        keyMap.put(KeyEvent.VK_ENTER, KeyRole.SELECT);
        keyMap.put(KeyEvent.VK_ESCAPE, KeyRole.PAUSE);
    }

    private void returnToMainMenu()
    {
        activeOverstate = mainMenu;
        gameState = new GameState(this::returnToMainMenu);
    }

    private void deployMainMenu() { activeOverstate = mainMenu;}

    private void startGame()
    {
        activeOverstate = gameState;
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        activeOverstate.parseKeyPress(keyMap.getOrDefault(e.getKeyCode(), KeyRole.NONE));
    }

    public void keyReleased(KeyEvent e) {
        activeOverstate.parseKeyRelease(keyMap.getOrDefault(e.getKeyCode(), KeyRole.NONE));
    }

    public void paintComponent( Graphics g ) {
        super.paintComponent(g);
        //g.drawImage(image, 0, 0, null);
        Graphics2D g2d = (Graphics2D) g;
        //AffineTransform t = g2d.getTransform();
        //g2d.scale(1.5, 1.5);
        activeOverstate.draw(g2d);
        //g2d.setTransform(t);
    }
}
