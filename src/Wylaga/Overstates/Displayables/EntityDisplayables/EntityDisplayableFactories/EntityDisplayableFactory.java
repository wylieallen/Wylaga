package Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.EnemyShip;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;

public interface EntityDisplayableFactory
{
    public EntityDisplayable makePlayerDisplayable(PlayerShip playerShip);
    public EntityDisplayable makeProjectileDisplayable(Projectile projectile);
    public EntityDisplayable makePlayerProjectileDisplayable(Projectile projectile);
    public EntityDisplayable makeEnemyDisplayable(EnemyShip enemyShip);
}
