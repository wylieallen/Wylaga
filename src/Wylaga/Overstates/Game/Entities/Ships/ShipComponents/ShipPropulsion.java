package Wylaga.Overstates.Game.Entities.Ships.ShipComponents;

import Wylaga.Util.Trajectory;

public class ShipPropulsion
{
    private int speed;
    private Trajectory trajectory;

    public ShipPropulsion(int speed)
    {
        this.speed = speed;
    }

    public int getSpeed() {return speed;}
}
