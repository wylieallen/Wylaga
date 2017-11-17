package Wylaga.Overstates.Displayables.Overlays;

import Wylaga.Overstates.Displayables.Displayable;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Overlay extends Displayable
{
    private BufferedImage background;
    private Graphics2D g2d;

    public Overlay(Point position, BufferedImage background)
    {
        super(position, new BufferedImage(background.getWidth(), background.getHeight(), BufferedImage.TYPE_INT_ARGB));
        this.background = background;
        g2d = super.getImage().createGraphics();
    }

    protected void drawBackground()
    {
        g2d.drawImage(background, 0, 0, null);
    }

    protected Graphics2D getGraphics() {return g2d;}

    public void update()
    {
        if(stateChanged())
        {
            drawBackground();
            drawImage();
        }
    }

    protected abstract boolean stateChanged();

    public abstract void drawImage();
}
