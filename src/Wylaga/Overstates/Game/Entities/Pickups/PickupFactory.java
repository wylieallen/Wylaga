package Wylaga.Overstates.Game.Entities.Pickups;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Game;
import Wylaga.Util.Random.Random;

import java.awt.geom.Point2D;

public class PickupFactory
{
    public static Pickup makePickup(Game game, Point2D.Double point)
    {
        int roll = Random.rollInt(10);
        if (roll < 3)
        {
            // Health Pickup:
            return new Pickup(point, () -> game.getPlayerShip().heal(10), EntityDisplayableFactory::makeHealthPickupDisplayable);
        }
        else if (roll < 6)
        {
            // Score Pickup:
            return new Pickup(point, () -> game.addPoints(30), EntityDisplayableFactory::makeScorePickupDisplayable);
        }
        else if (roll < 8)
        {
            // Power Pickup:
            return new Pickup(point, () -> game.getPlayerShip().refuel(50), EntityDisplayableFactory::makePowerPickupDisplayable);
        }
        else // if roll == 8 or 9
            // Wingman Pickup:
            return new Pickup(point, game::respawnWingmen, EntityDisplayableFactory::makeWingmanPickupDisplayable);
    }
}
