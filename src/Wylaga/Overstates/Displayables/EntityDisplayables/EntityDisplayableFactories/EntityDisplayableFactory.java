package Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Game.Entities.Pickup;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.EnemyShip;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;

public interface EntityDisplayableFactory
{
    EntityDisplayable makePlayerDisplayable(PlayerShip playerShip);
    EntityDisplayable makeProjectileDisplayable(Projectile projectile);
    EntityDisplayable makePlayerProjectileDisplayable(Projectile projectile);
    EntityDisplayable makeEnemyDisplayable(EnemyShip enemyShip);
    EntityDisplayable makeHealthPickupDisplayable(Pickup pickup);
}
