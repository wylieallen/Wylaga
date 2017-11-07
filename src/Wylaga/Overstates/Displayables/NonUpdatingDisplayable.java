package Wylaga.Overstates.Displayables;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class NonUpdatingDisplayable extends Displayable
{
    public NonUpdatingDisplayable(Point position, BufferedImage image)
    {
        super(position, image);
    }

    public void update() {}
}
