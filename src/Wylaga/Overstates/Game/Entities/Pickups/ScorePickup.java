package Wylaga.Overstates.Game.Entities.Pickups;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;
import Wylaga.Util.AbstractFunction;

import java.awt.geom.Point2D;

public class ScorePickup extends Pickup
{
    public ScorePickup(Point2D.Double point, AbstractFunction scoreFunction)
    {
        super(point, scoreFunction);
    }

    @Override
    public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
    {
        return entityDisplayableFactory.makeScorePickupDisplayable(this);
    }
}
