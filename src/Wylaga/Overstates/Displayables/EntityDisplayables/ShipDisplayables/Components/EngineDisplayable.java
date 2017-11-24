package Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components;

import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EngineDisplayable extends SimpleDisplayable
{
    private Ship ship;
    private BufferedImage baseImage;
    private BufferedImage boostImage;
    private BufferedImage brakeImage;
    private EngineDisplayable.State currentState;

    public EngineDisplayable(Ship ship, Point offsetPoint, BufferedImage baseImage, BufferedImage boostImage, BufferedImage brakeImage, boolean isPlayer)
    {
        super(offsetPoint, baseImage);
        this.ship = ship;
        this.baseImage = baseImage;
        this.boostImage = boostImage;
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
