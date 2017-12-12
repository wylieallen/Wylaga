package Wylaga.Overstates.Displayables.Overlays.HUD;

import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;

import java.awt.*;
import java.awt.geom.Point2D;

public class HealthHudOverlay extends HudOverlay
{
    private PlayerShip ship;
    private Graphics2D g2d;
    private int prevHealth;

    public HealthHudOverlay(Point2D.Double position, PlayerShip ship)
    {
        super(position);
        this.ship = ship;
    }

    /*
    public void drawImage()
    {
        g2d.setColor(Color.WHITE);
        String string = "SHIELD: " + ship.getHealth() + " / 100";
        g2d.drawString(string,5, 12);
    }
    */

    protected String makeString()
    {
        return "SHIELD: " + ship.getHealth() + " / 100";
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
