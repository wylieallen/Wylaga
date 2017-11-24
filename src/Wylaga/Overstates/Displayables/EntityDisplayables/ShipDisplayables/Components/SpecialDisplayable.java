package Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components;

import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;
import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class SpecialDisplayable extends SimpleDisplayable
{
    private PlayerShip ship;
    private BufferedImage baseImage;
    private BufferedImage deployedImage;
    private SpecialDisplayable.State currentState;

    public SpecialDisplayable(PlayerShip ship, Point2D.Double offsetPoint, BufferedImage baseImage, BufferedImage deployedImage)
    {
        super(offsetPoint, baseImage);
        this.ship = ship;
        this.baseImage = baseImage;
        this.deployedImage = deployedImage;
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
            if(ship.specialDeployed() && ship.getCurFuel() > 0)
            {
                currentState = new DeployedState();
            }
        }
    }

    private class DeployedState implements State
    {
        private DeployedState()
        {
            setImage(deployedImage);
        }

        public void update()
        {
            if(!ship.specialDeployed() || ship.getCurFuel() <= 0)
            {
                currentState = new BaseState();
            }
        }
    }
}
