package Wylaga.Overstates.Game;

import Wylaga.Overstates.Game.Control.WaveController;
import Wylaga.Overstates.Game.Entities.Ships.EnemyShip;
import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Wave
{
    private int waveNumber;

    private WaveController controller;
    private Set<Ship> ships;
    private Ship leftShip;
    private Ship rightShip;

    public Wave(int waveNumber)
    {
        this.waveNumber = waveNumber;

        controller = new WaveController(this);
        ships = new HashSet<>();

        // todo: Wave shouldn't need to know all this position data to instantiate its ships
        for (int x = 32; x <= 500 - 32; x += 100)
            for (int y = 32; y <= 384 - 64 - 32; y += 75)
                ships.add(new EnemyShip(new Point(x, y)));

        resetLeftShip();
        resetRightShip();
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
        Point rightMax = new Point(-9999, 0);
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
        Point leftMax = new Point(9999, 0);
        for(Ship ship : ships)
        {
            if(ship.getOrigin().x < leftMax.x)
            {
                leftMax = ship.getOrigin();
                leftShip = ship;
            }
        }
    }

    public Ship getLeftShip() {return leftShip;}
    public Ship getRightShip() {return rightShip;}

    public Set<Ship> getShips() {
        return ships;
    }
}
