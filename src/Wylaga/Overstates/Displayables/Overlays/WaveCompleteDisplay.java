package Wylaga.Overstates.Displayables.Overlays;

import Wylaga.Overstates.Displayables.Displayable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WaveCompleteDisplay extends Displayable
{
    private static BufferedImage img = getImg();

    public WaveCompleteDisplay(Point pos)
    {
        super(pos, img);
    }

    private static BufferedImage getImg()
    {
        BufferedImage img = new BufferedImage(384, 64, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.RED);
        Font font = new Font("Courier New", 1, 64);
        g2d.setFont(font);
        g2d.drawString("WAVE OVER", 0, 64);
        return img;
    }
}
