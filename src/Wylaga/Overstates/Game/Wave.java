package Wylaga.Overstates.Game;

import Wylaga.Overstates.Game.Control.WaveController;
import Wylaga.Overstates.Game.Entities.Ships.EnemyShip;
import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

public class Wave
{
    private int waveNumber;

    private WaveController controller;
    private Set<Ship> ships;
    private Ship leftShip;
    private Ship rightShip;
    private Ship topShip;
    private Ship bottomShip;

    public Wave(int waveNumber)
    {
        this.waveNumber = waveNumber;

        controller = new WaveController(this);
        ships = new HashSet<>();

        for (int i = 0; i < 5; i++)
            for(int j = 0; j < 4; j++)
                ships.add(new EnemyShip(new Point2D.Double((440 - 13) + 100 * i, -325 + 75 * j)));

        resetLeftShip();
        resetRightShip();
        resetTopShip();
        resetBottomShip();
    }

    public void update()
    {
        Set<Ship> expiredShips = new HashSet<>();

        for(Ship ship : ships)
            if(ship.expired())
                expiredShips.add(ship);

        if(!expiredShips.isEmpty())
        {
            ships.removeAll(expiredShips);

            if(expiredShips.contains(leftShip))
            {
                resetLeftShip();
            }
            if(expiredShips.contains(rightShip))
            {
                resetRightShip();
            }
            if(expiredShips.contains(topShip))
            {
                resetTopShip();
            }
            if(expiredShips.contains(bottomShip))
            {
                resetBottomShip();
            }

            double speed = getSpeed();

            for(Ship ship : ships)
            {
                ship.setSpeed(speed);
            }
        }

        controller.update();
    }

    public boolean isDestroyed() {return ships.size() <= 0;}

    public double getSpeed() {return 11.0 - (0.5 * ships.size());}

    private void resetRightShip()
    {
        Point2D.Double rightMax = new Point2D.Double(-9999, 0);
        for(Ship ship : ships)
        {
            if(ship.getOrigin().x > rightMax.x)
            {
                rightMax = ship.getOrigin();
                rightShip = ship;
            }
        }
    }

    private void resetLeftShip()
    {
        Point2D.Double leftMax = new Point2D.Double(9999, 0);
        for(Ship ship : ships)
        {
            if(ship.getOrigin().x < leftMax.x)
            {
                leftMax = ship.getOrigin();
                leftShip = ship;
            }
        }
    }

    private void resetTopShip()
    {
        Point2D.Double topMax = new Point2D.Double(0, 9999);
        for(Ship ship : ships)
        {
            if(ship.getOrigin().y < topMax.y)
            {
                topMax = ship.getOrigin();
                topShip = ship;
            }
        }
    }

    private void resetBottomShip()
    {
        Point2D.Double bottomMax = new Point2D.Double(0, -9999);
        for(Ship ship : ships)
        {
            if(ship.getOrigin().y > bottomMax.y)
            {
                bottomMax = ship.getOrigin();
                bottomShip = ship;
            }
        }
    }

    public Ship getLeftShip() {return leftShip;}
    public Ship getRightShip() {return rightShip;}
    public Ship getTopShip() {return topShip;}
    public Ship getBottomShip() {return bottomShip;}

    public Set<Ship> getShips() {
        return ships;
    }
}
