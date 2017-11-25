package Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components;

import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class EngineDisplayable extends SimpleDisplayable
{
    private Ship ship;
    private BufferedImage baseImage;
    private BufferedImage boostImage;
    private BufferedImage boostImage2;
    private BufferedImage brakeImage;
    private EngineDisplayable.State currentState;

    public EngineDisplayable(Ship ship, Point2D.Double offsetPoint, BufferedImage baseImage, BufferedImage boostImage, BufferedImage boostImage2, BufferedImage brakeImage, boolean isPlayer)
    {
        super(offsetPoint, baseImage);
        this.ship = ship;
        this.baseImage = baseImage;
        this.boostImage = boostImage;
        this.boostImage2 = boostImage2;
        this.brakeImage = brakeImage;
        this.currentState = isPlayer ? new BaseState() : new EnemyBaseState();
    }

    @Override
    public void update()
    {
        currentState.update();
    }

    //==================================================================================================================

    private interface State {void update();}

    private class BaseState implements State
    {
        private BaseState()
        {
            setImage(baseImage);
        }

        public void update()
        {
            if(ship.getTrajectory().getDy() < 0)
            {
                currentState = new BoostState();
            }
            else if (ship.getTrajectory().getDy() > 0)
            {
                currentState = new BrakeState();
            }
        }
    }

    private class BoostState implements State
    {
        private BoostState()
        {
            setImage(boostImage);
        }

        private int counter = 10;

        public void update()
        {
            if(ship.getTrajectory().getDy() == 0)
            {
                currentState = new BaseState();
            }
            else if (ship.getTrajectory().getDy() > 0)
            {
                currentState = new BrakeState();
            }
            else if (--counter <= 0)
            {
                currentState = new BoostState2();
            }
        }
    }

    private class BoostState2 implements State
    {
        private BoostState2() { setImage(boostImage2); }

        private int counter = 20;

        public void update()
        {
            if(ship.getTrajectory().getDy() == 0)
            {
                currentState = new BaseState();
            }
            else if (ship.getTrajectory().getDy() > 0)
            {
                currentState = new BrakeState();
            }
            else if (--counter <= 0)
            {
                currentState = new BoostState();
            }
        }
    }

    private class BrakeState implements State
    {
        private BrakeState() { setImage(brakeImage); }

        public void update()
        {
            if(ship.getTrajectory().getDy() == 0)
            {
                currentState = new BaseState();
            }
            else if(ship.getTrajectory().getDy() < 0)
            {
                currentState = new BoostState();
            }
        }
    }

    private class EnemyBaseState implements State
    {
        private EnemyBaseState() { setImage(baseImage); }

        public void update()
        {
            if(ship.getTrajectory().getDy() > 0)
            {
                currentState = new EnemyBoostState();
            }
        }
    }

    private class EnemyBoostState implements State
    {
        private EnemyBoostState() {setImage(boostImage);}

        public void update()
        {
            if(ship.getTrajectory().getDy() <= 0)
            {
                currentState = new EnemyBaseState();
            }
        }
    }
}
