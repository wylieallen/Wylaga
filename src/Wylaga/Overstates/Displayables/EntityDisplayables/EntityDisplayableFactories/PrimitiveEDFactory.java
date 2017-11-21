package Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayable;
import Wylaga.Overstates.Displayables.Explosions.Explosion;
import Wylaga.Overstates.Game.Entities.Projectiles.PlayerProjectile;
import Wylaga.Overstates.Game.Entities.Ships.EnemyShip;
import Wylaga.Rendering.ImageFactory;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;
import Wylaga.Util.Random;

import java.awt.*;

public class PrimitiveEDFactory implements EntityDisplayableFactory
{
    public EntityDisplayable makePlayerDisplayable(PlayerShip playerShip)
    {
        return new ShipDisplayable(playerShip, ImageFactory.getBasePlayerImage(), ImageFactory.getHurtPlayerImage(), new Explosion(playerShip.getOrigin(), 240, Color.RED));
    }

    public EntityDisplayable makeProjectileDisplayable(Projectile projectile)
    {
        return new EntityDisplayable(projectile, ImageFactory.getProjectileImage(), new Explosion(projectile.getOrigin(), Random.rollInt(120) + 80, Color.ORANGE));
    }

    public EntityDisplayable makePlayerProjectileDisplayable(Projectile projectile)
    {
        return new EntityDisplayable(projectile, ImageFactory.getPlayerProjectileImage(), new Explosion(projectile.getOrigin(), Random.rollInt(120) + 80, Color.ORANGE));
    }

    public EntityDisplayable makeEnemyDisplayable(EnemyShip enemyShip)
    {
        return new ShipDisplayable(enemyShip, ImageFactory.getBaseEnemyImage(), ImageFactory.getHurtEnemyImage(), new Explosion(enemyShip.getOrigin(), 240, Color.RED));
    }
}
