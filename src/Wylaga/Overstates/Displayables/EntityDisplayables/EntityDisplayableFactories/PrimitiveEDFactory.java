package Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.*;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.ChassisDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.EngineDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.SpecialDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.WeaponDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.SimpleEntityDisplayable;
import Wylaga.Overstates.Displayables.Explosions.Explosion;
import Wylaga.Overstates.Game.Entities.Pickups.Pickup;
import Wylaga.Overstates.Game.Entities.Ships.EnemyShip;
import Wylaga.Rendering.ImageFactory;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;
import Wylaga.Util.Random;

import java.awt.*;
import java.awt.geom.Point2D;

public class PrimitiveEDFactory implements EntityDisplayableFactory
{
    public EntityDisplayable makePlayerDisplayable(PlayerShip playerShip)
    {
        ChassisDisplayable chassisDisplayable = new ChassisDisplayable(playerShip, new Point2D.Double(0, 0), ImageFactory.getPlayerBaseChassis(), ImageFactory.getPlayerHurtChassis());
        WeaponDisplayable weaponDisplayable = new WeaponDisplayable(playerShip, new Point2D.Double(22, 0), ImageFactory.getPlayerBaseWeapon(), ImageFactory.getPlayerFiringWeapon());
        EngineDisplayable engineDisplayable = new EngineDisplayable(playerShip, new Point2D.Double(12, 35), ImageFactory.getPlayerBaseEngine(), ImageFactory.getPlayerBoostEngine(),
                ImageFactory.getPlayerBrakeImage(), true);
        SpecialDisplayable specialDisplayable = new SpecialDisplayable(playerShip, new Point2D.Double(7, 24), ImageFactory.getPlayerBaseSpecial(), ImageFactory.getPlayerDeployedSpecial());
        return new ShipDisplayable(playerShip, new Explosion(playerShip.getOrigin(), 500, Color.ORANGE, 100),
                chassisDisplayable, engineDisplayable, weaponDisplayable, specialDisplayable);
    }

    public EntityDisplayable makeEnemyDisplayable(EnemyShip enemyShip)
    {
        ChassisDisplayable chassisDisplayable = new ChassisDisplayable(enemyShip, new Point2D.Double(0, 0), ImageFactory.getEnemyBaseChassis(), ImageFactory.getEnemyHurtChassis());
        WeaponDisplayable weaponDisplayable = new WeaponDisplayable(enemyShip, new Point2D.Double(6, 22), ImageFactory.getEnemyBaseWeapon(), ImageFactory.getEnemyFiringWeapon());
        EngineDisplayable engineDisplayable = new EngineDisplayable(enemyShip, new Point2D.Double(7, 0), ImageFactory.getEnemyBaseEngine(), ImageFactory.getEnemyBoostEngine(),
                ImageFactory.getEnemyBrakeImage(), false);
        return new ShipDisplayable(enemyShip, new Explosion(enemyShip.getOrigin(), 240, Color.CYAN, 80),
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

    public EntityDisplayable makeHealthPickupDisplayable(Pickup pickup)
    {
        return new SimpleEntityDisplayable(pickup, ImageFactory.getHealthPickupImage(), new Explosion(pickup.getOrigin(), 100, Color.LIGHT_GRAY, 30));
    }
}
