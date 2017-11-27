package Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Game.Entities.Pickups.*;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Projectiles.WingmanProjectile;
import Wylaga.Overstates.Game.Entities.Ships.EnemyShip;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;
import Wylaga.Overstates.Game.Entities.Ships.Ship;
import Wylaga.Overstates.Game.Entities.Ships.Wingman;

public interface EntityDisplayableFactory
{
    // Ships:
    EntityDisplayable makePlayerDisplayable(Ship playerShip);
    EntityDisplayable makeEnemyDisplayable(Ship enemyShip);
    EntityDisplayable makeWingmanDisplayable(Ship wingman);

    // Projectiles:
    EntityDisplayable makeProjectileDisplayable(Projectile projectile);
    EntityDisplayable makePlayerProjectileDisplayable(Projectile projectile);
    EntityDisplayable makeWingmanProjectileDisplayable(Projectile Projectile);

    // Pickups:
    EntityDisplayable makeHealthPickupDisplayable(Pickup pickup);
    EntityDisplayable makeScorePickupDisplayable(Pickup pickup);
    EntityDisplayable makePowerPickupDisplayable(Pickup pickup);
    EntityDisplayable makeWingmanPickupDisplayable(Pickup pickup);
}
