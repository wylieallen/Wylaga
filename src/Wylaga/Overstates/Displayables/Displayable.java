package Wylaga.Overstates.Displayables;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Displayable
{
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
}
