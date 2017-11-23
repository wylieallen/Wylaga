package Wylaga.Overstates.Displayables.Overlays;


import Wylaga.Overstates.Displayables.SimpleDisplayable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameOverDisplay extends SimpleDisplayable
{
    private static BufferedImage img = getGameOverImg();

    public GameOverDisplay(Point pos)
    {
        super(pos, img);
    }

    private static BufferedImage getGameOverImg()
    {
        BufferedImage img = new BufferedImage(384, 64, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.RED);
        Font font = new Font("Courier New", 1, 64);
        g2d.setFont(font);
        g2d.drawString("GAME OVER", 0, 64);
        return img;
    }
}
