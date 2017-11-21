package Wylaga.Overstates.Displayables;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Displayable
{
    public final static Displayable nullDisplayable = new Displayable(new Point(0, 0), new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));


    private Point position;
    private BufferedImage image;

    public Displayable(Point position, BufferedImage image)
    {
        this.position = position;
        this.image = image;
    }

    protected void setImage(BufferedImage image) {this.image = image;}

    public BufferedImage getImage() {return image;}
    public Point getPosition() {return position;}

    // By default, Displayables don't do anything when updated.
    // update() should be overridden by subclasses that want to take advantage of it.
    public void update() {};

    // By default, Displayables don't expire.
    // expired() should be overridden by subclasses that want to take advantage of it.
    public boolean expired() {return false;}
}
