package Wylaga;

import javax.swing.*;
import java.awt.*;

public class WylagaMain
{
    static public final int WINDOW_WIDTH = 1280;
    static public final int WINDOW_HEIGHT = 720;

    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run() { createAndShowGUI(); }
        });
    }

    private static void createAndShowGUI()
    {
        JFrame frame = new JFrame();
        frame.setTitle("Wylaga v0.0");
        frame.setSize(WINDOW_WIDTH + 16, WINDOW_HEIGHT + 39);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        InterfacePanel panel = new InterfacePanel(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.addKeyListener(panel);

        frame.validate();
        // frame.setUndecorated(true);
        frame.setVisible(true);
    }

}
