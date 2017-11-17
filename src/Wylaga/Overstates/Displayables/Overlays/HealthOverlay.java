package Wylaga.Overstates.Displayables.Overlays;

import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HealthOverlay extends Overlay
{
    private static BufferedImage background = makeBackground();

    private PlayerShip ship;
    private Graphics2D g2d;
    private int prevHealth;

    public HealthOverlay(Point position, PlayerShip ship)
    {
        super(position, background);
        this.ship = ship;
        g2d = super.getGraphics();
    }

    public void drawImage()
    {
        g2d.setColor(Color.BLACK);
        String string = "HP: " + ship.getHealth() + " / 100";
        g2d.drawString(string,5, 12);
    }

    private static BufferedImage makeBackground()
    {
        BufferedImage background = new BufferedImage(100, 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D backg2d = background.createGraphics();
        backg2d.setColor(Color.WHITE);
        backg2d.fillRect(0, 0, 100, 20);
        backg2d.setColor(Color.GRAY);
        backg2d.drawRect(0, 0, 99, 19);
        return background;
    }

    protected boolean stateChanged()
    {
        if(ship.getHealth() != prevHealth)
        {
            prevHealth = ship.getHealth();
            return true;
        }
        else
        {
            return false;
        }
    }
}
