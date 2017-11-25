package Wylaga.Overstates.Displayables;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CompositeDisplayable implements Displayable
{
    private Point2D.Double position;
    private Set<Displayable> components;

    protected CompositeDisplayable(Point2D.Double position, Set<Displayable> components)
    {
        this.position = position;
        this.components = components;
    }

    public void update()
    {
        Set<Displayable> expiredComponents = new HashSet<>();
        for(Displayable component : components)
        {
            component.update();
            if(component.expired())
            {
                expiredComponents.add(component);
            }
        }
        components.removeAll(expiredComponents);
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
        return null;
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
