package Wylaga.Overstates.Displayables;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CompositeDisplayable implements Displayable
{
    private Point position;
    private Displayable[] components;

    public CompositeDisplayable(Point position, Displayable... components)
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

    public void drawWithOffset(Graphics2D g2d, Point offset)
    {
        Point offsetPos = new Point(position.x + offset.x, position.y + offset.y);
        for(Displayable component : components)
        {
            component.drawWithOffset(g2d, offsetPos);
        }
    }

    public BufferedImage getImage()
    {
        return components[0].getImage();
    }

    public Point getPosition()
    {
        return position;
    }

    public boolean expired()
    {
        return false;
    }
}
