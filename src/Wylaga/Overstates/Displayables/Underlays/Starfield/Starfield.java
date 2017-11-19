package Wylaga.Overstates.Displayables.Underlays.Starfield;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.Underlays.Starfield.Stars.PixelStar;
import Wylaga.Overstates.Displayables.Underlays.Starfield.Stars.Star;
import Wylaga.Util.Random;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class Starfield extends Displayable
{
    private static Starfield instance = new Starfield();

    private Graphics2D g2d;
    private Set<Star> stars;

    private Starfield()
    {
        super(new Point(0, 0), new BufferedImage(1280, 720, BufferedImage.TYPE_INT_ARGB));
        g2d = super.getImage().createGraphics();

        stars = new HashSet<>();

        g2d.setBackground(Color.BLACK);
        g2d.clearRect(0, 0, 1280, 720);

        for(int i = 0; i < 1280; i++)
        {
            for(int j = 0; j < 720; j++)
            {
                if(Random.rollInt(1500) == 0)
                {
                    System.out.println("Adding star at " + i + "," + j);
                    stars.add(new PixelStar(new Point(i, j)));
                }
            }
        }
    }

    public void update()
    {
        Set<Star> expiredStars = new HashSet<>();

        g2d.clearRect(0, 0, 1280, 720);
        for(Star star : stars)
        {
            star.update();
            Point position = star.getPosition();
            g2d.drawImage(star.getImage(), position.x, position.y, null);

            if(position.y >= 720)
            {
                expiredStars.add(star);
            }
        }

        stars.removeAll(expiredStars);

        for(int i = 0; i < 1280; i++)
        {
            if(Random.rollInt(700) == 0)
            {
                stars.add(new PixelStar(new Point(i, -3)));
            }
        }
    }

    public static Starfield getInstance() {return instance;}
}
