package Wylaga;

import Wylaga.Util.KeyRole;
import Wylaga.Overstates.GameState;
import Wylaga.Overstates.Overstate;
import Wylaga.Overstates.Menus.MenuFactory;
import Wylaga.Rendering.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class InterfacePanel extends JPanel implements KeyListener
{
    private BufferedImage image;
    private Renderer renderer;
    private javax.swing.Timer renderTimer;

    private Overstate activeOverstate;
    private Overstate mainMenu;
    private Overstate gameState;

    private Map<Integer, KeyRole> keyMap;

    private long prevTime;
    private long frameCount = 0;
    private double millisElapsed = 0;

    public InterfacePanel(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        activeOverstate = MenuFactory.makeStartMenu(this::deployMainMenu);
        mainMenu = MenuFactory.makeMainMenu(this::startGame);
        gameState = new GameState();

        renderer = new Renderer(image.createGraphics());

        initializeKeyMap();

        // 1 frame per 17 ms ~= 60 frames per second
        renderTimer = new javax.swing.Timer(17, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                renderTimer.stop();

                // FPS monitoring:
                /*
                long curTime = System.currentTimeMillis();
                long delta = curTime - prevTime;
                millisElapsed += delta;
                double msPerFrame = millisElapsed / ++frameCount;
                if(delta > 28)
                {
                    System.out.println("!");
                }
                System.out.printf(frameCount + " " + delta + " :  %.2f : %.2f \n", msPerFrame, 1000 / msPerFrame);
                prevTime = curTime;
                */

                activeOverstate.update();
                renderer.drawOverstate(activeOverstate);
                repaint();

                renderTimer.restart();
            }
        });

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

    public void deployMainMenu() { activeOverstate = mainMenu;}

    public void startGame()
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
        g.drawImage(image, 0, 0, null);
    }
}
