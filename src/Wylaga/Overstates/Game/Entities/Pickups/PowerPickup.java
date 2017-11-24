package Wylaga.Overstates.Game.Entities.Pickups;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;
import Wylaga.Util.AbstractFunction;

import java.awt.geom.Point2D;

public class PowerPickup extends Pickup
{
    public PowerPickup(Point2D.Double point, PlayerShip playerShip)
    {
        super(point, () -> playerShip.addFuel(50));
    }

    @Override
    public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
    {
        return entityDisplayableFactory.makePowerPickupDisplayable(this);
    }
}
