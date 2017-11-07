package Wylaga;

import Wylaga.Control.KeyRole;
import Wylaga.Overstates.Game.GameState;
import Wylaga.Overstates.Menus.Buttons.Functions.ButtonFunction;
import Wylaga.Overstates.Overstate;
import Wylaga.Overstates.Menus.Menu;
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

public class InterfacePanel extends JPanel implements KeyListener {
    private BufferedImage image;
    private Renderer renderer;

    private Overstate activeOverstate;
    private Menu startMenu = MenuFactory.makeStartMenu(this);

    private javax.swing.Timer timer;

    private HashMap<Integer, KeyRole> keyMap;

    public InterfacePanel(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        activeOverstate = startMenu;

        renderer = new Renderer(image.createGraphics());

        initializeKeyMap();

        // 1 frame per 17 ms ~= 60 frames per second
        timer = new javax.swing.Timer(17, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                timer.stop();

                activeOverstate.update();
                renderer.drawOverstate(activeOverstate);
                repaint();

                timer.restart();
            }
        });

        timer.start();
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

    public void startGame()
    {
        activeOverstate = new GameState();
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
