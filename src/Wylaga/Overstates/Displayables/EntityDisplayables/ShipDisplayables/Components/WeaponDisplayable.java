package Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components;

import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class WeaponDisplayable extends SimpleDisplayable
{
    private Ship ship;
    private BufferedImage baseImage;
    private BufferedImage firingImage;
    private WeaponDisplayable.State currentState;

    public WeaponDisplayable(Ship ship, Point2D.Double offsetPoint, BufferedImage baseImage, BufferedImage firingImage)
    {
        super(offsetPoint, baseImage);
        this.ship = ship;
        this.baseImage = baseImage;
        this.firingImage = firingImage;
        this.currentState = new BaseState();
    }

    @Override
    public void update()
    {
        currentState.update();
    }

    private interface State {void update();}

    private class BaseState implements State
    {
        private BaseState()
        {
            setImage(baseImage);
        }

        public void update()
        {
            if(ship.isFiring())
            {
                currentState = new FiringState();
            }
        }
    }

    private class FiringState implements State
    {
        private int counter;

        private FiringState()
        {
            setImage(firingImage);
            counter = 2;
        }

        public void update()
        {
            if(ship.isFiring())
            {
                currentState = new FiringState();
            }
            else if (--counter <= 0)
            {
                currentState = new BaseState();
            }
        }
    }
}
