package Wylaga;

import javax.swing.*;
import java.awt.*;

public class WylagaApp
{
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;

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
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        InterfacePanel panel = new InterfacePanel(WIDTH, HEIGHT);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.addKeyListener(panel);

        frame.validate();
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

}
