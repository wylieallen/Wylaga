package Wylaga.Overstates.Displayables.Underlays;

import Wylaga.Overstates.Displayables.CompositeDisplayable;
import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Rendering.ImageFactory;
import Wylaga.Util.Random;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedHashSet;
import java.util.Set;

public class Starfield extends CompositeDisplayable
{
    private static Starfield instance = new Starfield();

    private static final int density = 2;

    private Starfield()
    {
        super(new Point2D.Double(0, 0), makeStars(1280, 720), new Dimension(1280, 720));
    }

    private static Set<Displayable> makeStars(int width, int height)
    {
        Set<Displayable> stars = new LinkedHashSet<>();

        stars.add(new SimpleDisplayable(new Point2D.Double(0, 0), ImageFactory.makeBlackRect(width, height)));

        for(int j = 0; j < height; j++)
        {
            for(int i = 0; i < density; i++)
                stars.add(new Star(new Point2D.Double(Random.rollInt(width), j)));
        }

        return stars;
    }

    public void update()
    {
        super.update();
        for(int i = 0; i < density; i++)
            super.add(new Star(new Point2D.Double(Random.rollInt(1280), -3)));
        //super.add(new Star(new Point2D.Double(Random.rollInt(1280), -3)));
    }

    public static Starfield getInstance() {return instance;}

    private static class Star extends SimpleDisplayable
    {
        private double velocity;

        public Star(Point2D.Double point)
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

        @Override
        public void update()
        {
            super.getPosition().setLocation(super.getPosition().x, super.getPosition().y + velocity);
        }

        @Override
        public boolean expired()
        {
            return super.getPosition().y > 720;
        }
    }
}
