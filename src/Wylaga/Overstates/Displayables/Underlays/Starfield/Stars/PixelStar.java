package Wylaga.Overstates.Displayables.Underlays.Starfield.Stars;

import Wylaga.Util.Random;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class PixelStar extends Star
{
    private double velocity;

    public PixelStar(Point2D.Double point)
    {
        super(point, new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB));
        Graphics2D g2d = super.getImage().createGraphics();
        int roll = Random.rollInt(10);
        int size = 0;
        if (roll < 6)
        {
            size = 1;
        }
        else if (roll < 8)
        {
            size = 2;
        }
        else if (roll == 9)
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
            color = Color.YELLOW;
        }
        else if(roll < 94)
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

        if(size == 3)
        {
            int rgb = color.getRGB();
            BufferedImage image = super.getImage();
            if(Random.rollInt(2) == 0)
            {
                image.setRGB(1, 0, rgb);
                image.setRGB(0, 1, rgb);
                image.setRGB(1, 1, rgb);
                image.setRGB(2, 1, rgb);
                image.setRGB(1, 2, rgb);
            }
            else
            {
                image.setRGB(0, 0, rgb);
                image.setRGB(2, 0, rgb);
                image.setRGB(1, 1, rgb);
                image.setRGB(0, 2, rgb);
                image.setRGB(2, 2, rgb);
            }
        }
        else
        {
            g2d.setColor(color);
            g2d.fillRect(0, 0, size, size);
        }

        velocity = Random.rollDouble(5.99) + 0.01;
    }

    public void update()
    {
        super.getPosition().setLocation(super.getPosition().x, super.getPosition().y + velocity);
    }
}
