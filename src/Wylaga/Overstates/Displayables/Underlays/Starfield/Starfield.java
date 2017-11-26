package Wylaga.Overstates.Displayables.Underlays.Starfield;

import Wylaga.Overstates.Displayables.CompositeDisplayable;
import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Overstates.Displayables.Underlays.Starfield.Stars.PixelStar;
import Wylaga.Overstates.Displayables.Underlays.Starfield.Stars.Star;
import Wylaga.Rendering.ImageFactory;
import Wylaga.Util.Random;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class Starfield extends CompositeDisplayable
{
    private static Starfield instance = new Starfield();

    private Starfield()
    {
        super(new Point2D.Double(0, 0), makeStars(1280, 720));
    }

    private static Set<Displayable> makeStars(int width, int height)
    {
        Set<Displayable> stars = new HashSet<>();

        stars.add(new SimpleDisplayable(new Point2D.Double(0, 0), ImageFactory.makeBlackRect(width, height)));

        for(int j = 0; j < height; j++)
        {
            stars.add(new PixelStar(new Point2D.Double(Random.rollInt(width), j)));
            stars.add(new PixelStar(new Point2D.Double(Random.rollInt(width), j)));
        }

        return stars;
    }

    public void update()
    {
        super.update();
        super.add(new PixelStar(new Point2D.Double(Random.rollInt(1280), -3)));
        super.add(new PixelStar(new Point2D.Double(Random.rollInt(1280), -3)));
    }

    public static Starfield getInstance() {return instance;}
}
