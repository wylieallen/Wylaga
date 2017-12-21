package Wylaga.Overstates.Displayables.Underlays;

import Wylaga.Overstates.Displayables.CompositeDisplayable;
import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Util.ImageFactory;
import Wylaga.Util.Random.Random;
import Wylaga.WylagaApp;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedHashSet;
import java.util.Set;

public class Starfield extends CompositeDisplayable
{
    private static Starfield instance = new Starfield();

    private static final int density = 1;

    private Starfield()
    {
        super(new Point2D.Double(0, 0), makeStarLayers(WylagaApp.WIDTH, WylagaApp.HEIGHT), new Dimension(WylagaApp.WIDTH, WylagaApp.HEIGHT));
    }


    private static Set<Displayable> makeStarLayers(int width, int height)
    {
        Set<Displayable> starlayers = new LinkedHashSet<>();

        starlayers.add(new SimpleDisplayable(new Point2D.Double(0, 0), ImageFactory.makeBlackRect(WylagaApp.WIDTH, WylagaApp.HEIGHT)));

        for(double vel = 1; vel <= 5; vel++)
        {
            starlayers.add(new StarLayer(new Point2D.Double(0, -WylagaApp.HEIGHT), vel));
            starlayers.add(new StarLayer(new Point2D.Double(0, 0), vel));
        }

        return starlayers;
    }

    public static Starfield getInstance() {return instance;}

    private static class StarLayer extends CompositeDisplayable {
        private double velocity;

        public StarLayer(Point2D.Double origin, double velocity) {
            super(origin, new LinkedHashSet<>(), new Dimension(WylagaApp.WIDTH, WylagaApp.HEIGHT));
            this.velocity = velocity;

            //super.add(new SimpleDisplayable(new Point2D.Double(0, 0), ImageFactory.makeBorderedRect(new Dimension(WylagaApp.WIDTH, WylagaApp.HEIGHT), new Color(0, 0, 0, 0))));

            for(int y = 0; y < WylagaApp.HEIGHT; y += 10)
            {
                super.add(new Star(new Point2D.Double(Random.rollInt(1920), y)));
            }
        }

        @Override
        public void update()
        {
            Point2D.Double position = super.getPosition();
            if(position.y > WylagaApp.HEIGHT)
            {
                position.setLocation(position.x, -WylagaApp.HEIGHT);
            }
            else
            {
                position.setLocation(position.x, position.y + velocity);
            }

        }
    }

    private static class Star extends SimpleDisplayable
    {
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
        }
    }
}
