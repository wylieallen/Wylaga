package Wylaga.Overstates.Displayables.Overlays.HUD;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.SimpleDisplayable;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public abstract class HudOverlay implements Displayable
{
    private Point2D.Double position;
    private Dimension dimension;

    private String string;
    private Color color;

    public HudOverlay(Point2D.Double position)
    {
        //super(position, new BufferedImage(120, 20, BufferedImage.TYPE_INT_ARGB));
        this.position = position;
        this.dimension = new Dimension(120, 20);
        this.color = Color.WHITE;
        //this.string = makeString();
    }

    public void update()
    {
        if(stateChanged())
        {
            string = makeString();
        }
    }

    public void draw(Graphics2D g2d)
    {
        Color prevColor = g2d.getColor();

        g2d.setColor(color);
        g2d.drawString(string, (int) position.x, (int) position.y);

        g2d.setColor(prevColor);
    }

    public void drawWithOffset(Graphics2D g2d, Point2D.Double offset)
    {
        Color prevColor = g2d.getColor();

        g2d.setColor(color);
        g2d.drawString(string, (int) (position.x + offset.x), (int) (position.y + offset.y));

        g2d.setColor(prevColor);
    }

    protected abstract String makeString();
    protected abstract boolean stateChanged();

    public Point2D.Double getPosition()
    {
        return position;
    }

    public Dimension getSize()
    {
        return dimension;
    }
}
