package Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.*;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.ChassisDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.EngineDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.SpecialDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.WeaponDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.SimpleEntityDisplayable;
import Wylaga.Overstates.Displayables.Explosions.Explosion;
import Wylaga.Overstates.Game.Entities.Pickups.*;
import Wylaga.Overstates.Game.Entities.Ships.EnemyShip;
import Wylaga.Overstates.Game.Entities.Ships.Wingman;
import Wylaga.Rendering.ImageFactory;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;
import Wylaga.Util.Random;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

public class PrimitiveEDFactory implements EntityDisplayableFactory
{
    public EntityDisplayable makePlayerDisplayable(PlayerShip playerShip)
    {
        Set<Displayable> shipComponents = new HashSet<>();
        shipComponents.add( new ChassisDisplayable(playerShip, new Point2D.Double(0, 0), ImageFactory.getPlayerBaseChassis(), ImageFactory.getPlayerHurtChassis()));
        shipComponents.add( new WeaponDisplayable(playerShip, new Point2D.Double(22, 0), ImageFactory.getPlayerBaseWeapon(), ImageFactory.getPlayerFiringWeapon()));
        shipComponents.add( new EngineDisplayable(playerShip, new Point2D.Double(12, 35), ImageFactory.getPlayerBaseEngine(), ImageFactory.getPlayerBoostEngine(),
                ImageFactory.getPlayerBoostEngine2(), ImageFactory.getPlayerBrakeImage(), true));
        shipComponents.add(new SpecialDisplayable(playerShip, new Point2D.Double(7, 24), ImageFactory.getPlayerBaseSpecial(), ImageFactory.getPlayerDeployedSpecial()));

        return new ShipDisplayable(playerShip, new Explosion(playerShip.getOrigin(), 500, Color.ORANGE, 100), shipComponents);
    }

    public EntityDisplayable makeEnemyDisplayable(EnemyShip enemyShip)
    {
        Set<Displayable> shipComponents = new HashSet<>();
        shipComponents.add( new ChassisDisplayable(enemyShip, new Point2D.Double(0, 0), ImageFactory.getEnemyBaseChassis(), ImageFactory.getEnemyHurtChassis()));
        shipComponents.add( new WeaponDisplayable(enemyShip, new Point2D.Double(6, 22), ImageFactory.getEnemyBaseWeapon(), ImageFactory.getEnemyFiringWeapon()));
        shipComponents.add( new EngineDisplayable(enemyShip, new Point2D.Double(7, 0), ImageFactory.getEnemyBaseEngine(), ImageFactory.getEnemyBoostEngine(),
                ImageFactory.getEnemyBaseEngine(), ImageFactory.getEnemyBrakeImage(), false));
        return new ShipDisplayable(enemyShip, new Explosion(enemyShip.getOrigin(), 240, Color.CYAN, 80), shipComponents);
    }

    public EntityDisplayable makeWingmanDisplayable(Wingman wingman)
    {
        Set<Displayable> shipComponents = new HashSet<>();
        shipComponents.add(new ChassisDisplayable(wingman, new Point2D.Double(0,0 ), ImageFactory.getWingmanBaseChassis(), ImageFactory.getWingmanHurtChassis()));
        shipComponents.add(new WeaponDisplayable(wingman, new Point2D.Double(10, 0), ImageFactory.getWingmanBaseWeapon(), ImageFactory.getWingmanFiringWeapon()));
        shipComponents.add(new EngineDisplayable(wingman, new Point2D.Double(5, 20), ImageFactory.getWingmanBaseEngine(), ImageFactory.getWingmanBoostEngine(),
                ImageFactory.getWingmanBoostEngine2(), ImageFactory.getWingmanBrakeImage(), true));
        shipComponents.add(new SpecialDisplayable(wingman, new Point2D.Double(5, 15), ImageFactory.getWingmanBaseSpecial(), ImageFactory.getWingmanDeployedSpecial()));
        return new ShipDisplayable(wingman, new Explosion(wingman.getOrigin(), 250, Color.MAGENTA, 80), shipComponents);
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

    public EntityDisplayable makeWingmanProjectileDisplayable(Projectile projectile)
    {
        return new SimpleEntityDisplayable(projectile, ImageFactory.getWingmanProjectileImage(),
                new Explosion(projectile.getOrigin(), 80, Color.RED, 20));
    }

    public EntityDisplayable makeHealthPickupDisplayable(HealthPickup pickup)
    {
        return new SimpleEntityDisplayable(pickup, ImageFactory.getHealthPickupImage(), new Explosion(pickup.getOrigin(), 100, Color.LIGHT_GRAY, 30));
    }

    public EntityDisplayable makeScorePickupDisplayable(ScorePickup pickup)
    {
        return new SimpleEntityDisplayable(pickup, ImageFactory.getScorePickupImage(), new Explosion(pickup.getOrigin(), 100, Color.LIGHT_GRAY, 30));
    }

    public EntityDisplayable makePowerPickupDisplayable(PowerPickup pickup)
    {
        return new SimpleEntityDisplayable(pickup, ImageFactory.getPowerPickupImage(), new Explosion(pickup.getOrigin(), 100, Color.LIGHT_GRAY, 30));
    }

    public EntityDisplayable makeWingmanPickupDisplayable(WingmanPickup pickup)
    {
        return new SimpleEntityDisplayable(pickup, ImageFactory.getWingmanPickupImage(), new Explosion(pickup.getOrigin(), 100, Color.LIGHT_GRAY, 30));
    }
}
