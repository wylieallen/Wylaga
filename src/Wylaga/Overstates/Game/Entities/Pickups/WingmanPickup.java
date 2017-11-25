package Wylaga.Overstates.Game.Entities.Pickups;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Util.AbstractFunction;

import java.awt.geom.Point2D;

public class WingmanPickup extends Pickup
{
    public WingmanPickup(Point2D.Double point, AbstractFunction scoreFunction)
    {
        super(point, scoreFunction);
    }

    @Override
    public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
    {
        return entityDisplayableFactory.makeWingmanPickupDisplayable(this);
    }
}
