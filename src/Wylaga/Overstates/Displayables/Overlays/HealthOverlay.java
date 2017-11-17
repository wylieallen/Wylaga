package Wylaga.Overstates.Displayables.Overlays;

import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HealthOverlay extends Overlay
{
    private PlayerShip ship;
    private Graphics2D g2d;
    private int prevHealth;

    public HealthOverlay(Point position, PlayerShip ship)
    {
        super(position);
        this.ship = ship;
        g2d = super.getGraphics();
    }

    public void drawImage()
    {
        g2d.setColor(Color.WHITE);
        String string = "HP: " + ship.getHealth() + " / 100";
        g2d.drawString(string,5, 12);
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
