package Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components;

import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class ChassisDisplayable extends SimpleDisplayable
{
    private Ship ship;
    private BufferedImage baseImage;
    private BufferedImage hurtImage;
    private BufferedImage nearDeathImage;
    private ChassisDisplayable.State currentState;

    public ChassisDisplayable(Ship ship, Point2D.Double offsetPoint, BufferedImage baseImage, BufferedImage hurtImage, BufferedImage nearDeathImage)
    {
        super(offsetPoint, baseImage);
        this.ship = ship;
        this.baseImage = baseImage;
        this.hurtImage = hurtImage;
        this.nearDeathImage = nearDeathImage;
        this.currentState = new BaseState();
    }

    protected Ship getShip() {return ship;}

    @Override
    public void update() { currentState.update(); }

    private interface State { void update();}

    private class BaseState implements State
    {
        private int prevHealth;

        private BaseState()
        {
            setImage(baseImage);
            prevHealth = ship.getHealth();
        }

        public void update()
        {
            if(prevHealth > ship.getHealth())
            {
                currentState = new HurtState();
            }
        }
    }

    private class HurtState implements State
    {
        private int prevHealth;
        private int counter;

        private HurtState()
        {
            setImage(hurtImage);
            prevHealth = ship.getHealth();
            counter = 2;
        }

        public void update()
        {
            if(prevHealth > ship.getHealth())
            {
                currentState = new HurtState();
            }
            else if(--counter <= 0)
            {
                if(ship.getHealth() > 10)
                    currentState = new BaseState();
                else
                    currentState = new NearDeathState();
            }
        }
    }

    private class NearDeathState implements State
    {
        private int prevHealth;

        private NearDeathState()
        {
            setImage(nearDeathImage);
            prevHealth = ship.getHealth();
        }

        public void update()
        {
            if(prevHealth > ship.getHealth())
            {
                currentState = new HurtState();
            }
            else if(ship.getHealth() > 10)
            {
                currentState = new BaseState();
            }
        }

    }
}
