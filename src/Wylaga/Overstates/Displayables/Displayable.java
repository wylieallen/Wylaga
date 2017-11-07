package Wylaga.Overstates.Displayables;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Displayable
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

    public abstract void update();
}
