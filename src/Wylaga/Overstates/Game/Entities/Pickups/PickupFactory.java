package Wylaga.Overstates.Game.Entities.Pickups;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Overstates.Game.Entities.Ships.ShipComponents.ShipWeapon;
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

            roll = Random.rollInt(100);
            if (roll <= 5)
            {
                return new Pickup(point, () -> {game.getPlayerShip().upgradeMaxHealth(10); game.getPlayerShip().heal(100);}, EntityDisplayableFactory::makeMegaHealthPickupDisplayable);
            }
            else if(roll <= 15)
            {
                return new Pickup(point, () -> game.getPlayerShip().heal(100), EntityDisplayableFactory::makeSuperHealthPickupDisplayable);
            }
            else
            {
                return new Pickup(point, () -> game.getPlayerShip().heal(10), EntityDisplayableFactory::makeHealthPickupDisplayable);
            }

        }
        else if (roll < 6)
        {
            roll = Random.rollInt(10);
            if(roll <= 3)
            {
                return new Pickup(point, game::upgradePlayerWeapon, EntityDisplayableFactory::makeWeaponPickupDisplayable);
            }
            // Score Pickup:
            return new Pickup(point, () -> game.addPoints(30), EntityDisplayableFactory::makeScorePickupDisplayable);
        }
        else if (roll < 8)
        {
            // Power Pickup:
            return new Pickup(point, () -> game.getPlayerShip().refuel(50), EntityDisplayableFactory::makePowerPickupDisplayable);
        }
        else
        {
            // if roll == 8 or 9
            // Wingman Pickup:
            roll = Random.rollInt(10);
            if(roll <= 0)
            {
                return new Pickup(point, game::respawnSuperWingmen, EntityDisplayableFactory::makeSuperWingmanPickupDisplayable);
            }

            return new Pickup(point, game::respawnWingmen, EntityDisplayableFactory::makeWingmanPickupDisplayable);
        }

    }
}
