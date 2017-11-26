package Wylaga.Overstates.Game.Entities.Pickups;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Game;
import Wylaga.Util.Random;

import java.awt.geom.Point2D;

public class PickupFactory
{
    public static Pickup makePickup(Game game, Point2D.Double point)
    {
        int roll = Random.rollInt(10);
        if (roll < 3)
        {
            return new Pickup(point, () -> game.getPlayerShip().heal(10))
            {
                @Override
                public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
                {
                    return entityDisplayableFactory.makeHealthPickupDisplayable(this);
                }
            };
        }
        else if (roll < 6)
        {
            return new Pickup(point, () -> game.addPoints(30))
            {
                @Override
                public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
                {
                    return entityDisplayableFactory.makeScorePickupDisplayable(this);
                }
            };
        }
        else if (roll < 8)
        {
            return new Pickup(point, () -> game.getPlayerShip().refuel(50))
            {
                @Override
                public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
                {
                    return entityDisplayableFactory.makePowerPickupDisplayable(this);
                }
            };
        }
        else
            return new Pickup(point, game::respawnWingmen)
            {
                @Override
                public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
                {
                    return entityDisplayableFactory.makeWingmanPickupDisplayable(this);
                }
            };
    }
}
