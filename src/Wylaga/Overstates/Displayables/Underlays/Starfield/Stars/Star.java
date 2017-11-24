package Wylaga.Overstates.Displayables.Underlays.Starfield.Stars;

import Wylaga.Overstates.Displayables.SimpleDisplayable;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public abstract class Star extends SimpleDisplayable
{
    public Star(Point2D.Double point, BufferedImage image)
    {
        super(point, image);
    }
}
