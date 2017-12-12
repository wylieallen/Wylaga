package Wylaga.Overstates.Displayables.Overlays.HUD;

import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;

import java.awt.*;
import java.awt.geom.Point2D;

public class FuelHudOverlay extends HudOverlay
{
    private PlayerShip ship;
    private int prevFuel;

    public FuelHudOverlay(Point2D.Double position, PlayerShip ship)
    {
        super(position);
        this.ship = ship;
    }

    /*
    public void drawImage()
    {
        g2d.setColor(Color.WHITE);
        String string = "POWER: " + ship.getCurFuel() + " / " + ship.getMaxFuel();
        g2d.drawString(string,5, 12);
    }
    */

    protected String makeString()
    {
        return "POWER: " + ship.getCurFuel() + " / " + ship.getMaxFuel();
    }

    protected boolean stateChanged()
    {
        if(ship.getCurFuel() != prevFuel)
        {
            prevFuel = ship.getCurFuel();
            return true;
        }
        else
        {
            return false;
        }
    }
}