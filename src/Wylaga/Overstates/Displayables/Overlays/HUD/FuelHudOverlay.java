package Wylaga.Overstates.Displayables.Overlays.HUD;

import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;

import java.awt.*;

public class FuelHudOverlay extends HudOverlay
{
    private Graphics2D g2d;
    private PlayerShip ship;
    private int prevFuel;

    public FuelHudOverlay(Point position, PlayerShip ship)
    {
        super(position);
        this.ship = ship;
        this.g2d = super.getGraphics();
    }

    public void drawImage()
    {
        g2d.setColor(Color.WHITE);
        String string = "POWER: " + ship.getCurFuel() + " / " + ship.getMaxFuel();
        g2d.drawString(string,5, 12);
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