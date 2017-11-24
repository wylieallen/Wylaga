package Wylaga.Overstates.Displayables;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class CompositeDisplayable implements Displayable
{
    private Point2D.Double position;
    private Displayable[] components;

    public CompositeDisplayable(Point2D.Double position, Displayable... components)
    {
        this.position = position;
        this.components = components;
    }

    public void update()
    {
        for(Displayable component : components)
        {
            component.update();
        }
    }

    public void draw(Graphics2D g2d)
    {
        for(Displayable component : components)
        {
            component.drawWithOffset(g2d, position);
        }
    }

    public void drawWithOffset(Graphics2D g2d, Point2D.Double offset)
    {
        Point2D.Double offsetPos = new Point2D.Double((int) position.x + offset.x, (int) position.y + offset.y);
        for(Displayable component : components)
        {
            component.drawWithOffset(g2d, offsetPos);
        }
    }

    public BufferedImage getImage()
    {
        return components[0].getImage();
    }

    public Point2D.Double getPosition()
    {
        return position;
    }

    public boolean expired()
    {
        return false;
    }
}
