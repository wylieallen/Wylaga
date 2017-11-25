package Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Game.Entities.Pickups.*;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Projectiles.WingmanProjectile;
import Wylaga.Overstates.Game.Entities.Ships.EnemyShip;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;
import Wylaga.Overstates.Game.Entities.Ships.Wingman;

public interface EntityDisplayableFactory
{
    EntityDisplayable makePlayerDisplayable(PlayerShip playerShip);
    EntityDisplayable makeEnemyDisplayable(EnemyShip enemyShip);
    EntityDisplayable makeWingmanDisplayable(Wingman wingman);

    EntityDisplayable makeProjectileDisplayable(Projectile projectile);
    EntityDisplayable makePlayerProjectileDisplayable(Projectile projectile);
    EntityDisplayable makeWingmanProjectileDisplayable(Projectile wingmanProjectile);

    EntityDisplayable makeHealthPickupDisplayable(HealthPickup pickup);
    EntityDisplayable makeScorePickupDisplayable(ScorePickup scorePickup);
    EntityDisplayable makePowerPickupDisplayable(PowerPickup powerPickup);
    EntityDisplayable makeWingmanPickupDisplayable(WingmanPickup wingmanPickup);

}
