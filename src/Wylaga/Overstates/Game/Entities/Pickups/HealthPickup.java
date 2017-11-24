package Wylaga.Overstates.Game.Entities.Pickups;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;

import java.awt.*;
import java.awt.geom.Point2D;

public class HealthPickup extends Pickup
{
    public HealthPickup(Point2D.Double point, PlayerShip ship)
    {
        super(point, () -> ship.heal(10));
    }

    @Override
    public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
    {
        return entityDisplayableFactory.makeHealthPickupDisplayable(this);
    }
}
