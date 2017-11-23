package Wylaga.Overstates.Displayables.Underlays.Starfield.Stars;

import Wylaga.Overstates.Displayables.SimpleDisplayable;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Star extends SimpleDisplayable
{
    public Star(Point point, BufferedImage image)
    {
        super(point, image);
    }
}
