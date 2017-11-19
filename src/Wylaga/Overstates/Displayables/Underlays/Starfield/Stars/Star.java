package Wylaga.Overstates.Displayables.Underlays.Starfield.Stars;

import Wylaga.Overstates.Displayables.Displayable;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Star extends Displayable
{
    public Star(Point point, BufferedImage image)
    {
        super(point, image);
    }
}
