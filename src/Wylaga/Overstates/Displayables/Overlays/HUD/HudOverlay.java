package Wylaga.Overstates.Displayables.Overlays.HUD;

import Wylaga.Overstates.Displayables.SimpleDisplayable;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class HudOverlay extends SimpleDisplayable
{
    private Graphics2D g2d;
    private int width, height;

    public HudOverlay(Point position)
    {
        super(position, new BufferedImage(120, 20, BufferedImage.TYPE_INT_ARGB));
        BufferedImage image = super.getImage();
        this.g2d = image.createGraphics();
        g2d.setBackground(new Color(0, 0, 0, 0));
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    protected Graphics2D getGraphics() {return g2d;}

    public void update()
    {
        if(stateChanged())
        {
            g2d.clearRect(0, 0, width, height);
            drawImage();
        }
    }

    protected abstract boolean stateChanged();
    public abstract void drawImage();
}
