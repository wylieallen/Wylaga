package Wylaga.Overstates.Displayables.Underlays.Stars;

import Wylaga.Util.Random;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PixelStar extends Star
{
    public PixelStar(Point point)
    {
        super(point, new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB));
        Graphics2D g2d = super.getImage().createGraphics();
        int roll = Random.rollInt(10);
        int size = 0;
        if (roll < 4)
        {
            size = 1;
        }
        else if (roll < 8)
        {
            size = 2;
        }
        else if (roll <= 9)
        {
            size = 3;
        }

        Color color = Color.GRAY;
        roll = Random.rollInt(100);
        if (roll < 5)
        {
            color = Color.WHITE;
        }
        else if(roll < 85)
        {
            color = Color.LIGHT_GRAY;
        }
        else if(roll < 90)
        {
            color = Color.GRAY;
        }
        else if(roll < 93)
        {
            color = Color.YELLOW;
        }
        else if(roll < 95)
        {
            color = Color.CYAN;
        }
        else if(roll < 97)
        {
            color = Color.RED;
        }
        else if (roll <= 99)
        {
            color = Color.MAGENTA;
        }

        g2d.setColor(color);
        g2d.fillRect(0, 0, size, size);
    }
}
