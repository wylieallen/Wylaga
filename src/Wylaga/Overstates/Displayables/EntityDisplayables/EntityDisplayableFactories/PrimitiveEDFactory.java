package Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayable;
import Wylaga.Overstates.Game.Entities.Projectiles.PlayerProjectile;
import Wylaga.Overstates.Game.Entities.Ships.EnemyShip;
import Wylaga.Rendering.ImageFactory;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;

public class PrimitiveEDFactory implements EntityDisplayableFactory
{
    public EntityDisplayable makePlayerDisplayable(PlayerShip playerShip)
    {
        return new ShipDisplayable(playerShip, ImageFactory.getBasePlayerImage(), ImageFactory.getHurtPlayerImage());
    }

    public EntityDisplayable makeProjectileDisplayable(Projectile projectile)
    {
        return new EntityDisplayable(projectile, ImageFactory.getProjectileImage());
    }

    public EntityDisplayable makePlayerProjectileDisplayable(Projectile projectile)
    {
        return new EntityDisplayable(projectile, ImageFactory.getPlayerProjectileImage());
    }

    public EntityDisplayable makeEnemyDisplayable(EnemyShip enemyShip)
    {
        return new ShipDisplayable(enemyShip, ImageFactory.getBaseEnemyImage(), ImageFactory.getHurtEnemyImage());
    }
}
