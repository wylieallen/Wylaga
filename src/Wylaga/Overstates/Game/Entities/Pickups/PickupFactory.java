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
            return new Pickup(point, () -> game.getPlayerShip().heal(10), EntityDisplayableFactory::makeHealthPickupDisplayable);
        }
        else if (roll < 6)
        {
            return new Pickup(point, () -> game.addPoints(30), EntityDisplayableFactory::makeScorePickupDisplayable);
        }
        else if (roll < 8)
        {
            return new Pickup(point, () -> game.getPlayerShip().refuel(50), EntityDisplayableFactory::makePowerPickupDisplayable);
        }
        else
            return new Pickup(point, game::respawnWingmen, EntityDisplayableFactory::makeWingmanPickupDisplayable);
    }
}
