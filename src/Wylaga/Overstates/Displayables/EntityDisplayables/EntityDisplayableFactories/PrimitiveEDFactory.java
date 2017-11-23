package Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.*;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.ChassisDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.EngineDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.WeaponDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.SimpleEntityDisplayable;
import Wylaga.Overstates.Displayables.Explosions.Explosion;
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
        ChassisDisplayable chassisDisplayable = new ChassisDisplayable(playerShip, new Point(0, 0), ImageFactory.getPlayerBaseChassis(), ImageFactory.getPlayerHurtChassis());
        WeaponDisplayable weaponDisplayable = new WeaponDisplayable(playerShip, new Point(22, 0), ImageFactory.getPlayerBaseWeapon(), ImageFactory.getPlayerFiringWeapon());
        EngineDisplayable engineDisplayable = new EngineDisplayable(playerShip, new Point(12, 35), ImageFactory.getPlayerBaseEngine(), ImageFactory.getPlayerBoostEngine(), true);
        return new ShipDisplayable(playerShip.getOrigin(), playerShip, new Explosion(playerShip.getOrigin(), 500, Color.ORANGE, 100),
                chassisDisplayable, engineDisplayable, weaponDisplayable);
    }

    public EntityDisplayable makeEnemyDisplayable(EnemyShip enemyShip)
    {
        ChassisDisplayable chassisDisplayable = new ChassisDisplayable(enemyShip, new Point(0, 0), ImageFactory.getEnemyBaseChassis(), ImageFactory.getEnemyHurtChassis());
        WeaponDisplayable weaponDisplayable = new WeaponDisplayable(enemyShip, new Point(6, 22), ImageFactory.getEnemyBaseWeapon(), ImageFactory.getEnemyFiringWeapon());
        EngineDisplayable engineDisplayable = new EngineDisplayable(enemyShip, new Point(7, 0), ImageFactory.getEnemyBaseEngine(), ImageFactory.getEnemyBoostEngine(), false);
        return new ShipDisplayable(enemyShip.getOrigin(), enemyShip, new Explosion(enemyShip.getOrigin(), 240, Color.CYAN, 80),
                chassisDisplayable, engineDisplayable, weaponDisplayable);
    }

    public EntityDisplayable makeProjectileDisplayable(Projectile projectile)
    {
        return new SimpleEntityDisplayable(projectile, ImageFactory.getProjectileImage(),
                new Explosion(projectile.getOrigin(), 140, Color.GREEN, 30));
    }

    public EntityDisplayable makePlayerProjectileDisplayable(Projectile projectile)
    {
        return new SimpleEntityDisplayable(projectile, ImageFactory.getPlayerProjectileImage(),
                new Explosion(projectile.getOrigin(),Random.rollInt(120) + 80, Color.RED , 30));
    }
}
