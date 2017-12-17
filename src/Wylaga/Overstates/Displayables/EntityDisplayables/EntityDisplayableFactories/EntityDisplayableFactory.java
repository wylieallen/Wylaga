package Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.*;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.ChassisDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.EngineDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.SpecialDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.WeaponDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.SimpleEntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.SubimageEntityDisplayable;
import Wylaga.Overstates.Displayables.Explosions.Explosion;
import Wylaga.Overstates.Game.Entities.Pickups.*;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.Ship;
import Wylaga.Overstates.Game.Entities.Ships.ShipComponents.ShipWeapon;
import Wylaga.Rendering.ImageFactory;
import Wylaga.Util.Random.Random;

import java.awt.*;
import java.awt.geom.Point2D;

public class EntityDisplayableFactory
{
    private static EntityDisplayableFactory instance = new EntityDisplayableFactory();

    public static EntityDisplayableFactory getInstance() {
        return instance;
    }

    // Ships:

    public EntityDisplayable makePlayerDisplayable(Ship playerShip)
    {
        return new ShipDisplayable(playerShip, new Explosion(playerShip.getOrigin(),  500, Color.ORANGE, 100),
                makePlayerChassisDisplayable(playerShip),
                makePlayerEngineDisplayable(playerShip),
                makePlayerWeaponDisplayable(playerShip.getWeapon()),
                makePlayerSpecialDisplayable(playerShip));
    }

    public EntityDisplayable makeEnemyDisplayable(Ship enemyShip)
    {
        return new ShipDisplayable(enemyShip, new Explosion(enemyShip.getOrigin(), 240, Color.CYAN, 80),
                new ChassisDisplayable(enemyShip, new Point2D.Double(0, 0), ImageFactory.getEnemyBaseChassis(), ImageFactory.getEnemyHurtChassis(),
                        ImageFactory.getEnemyNearDeathChassis()),
                new EngineDisplayable(enemyShip, new Point2D.Double(7, 0), ImageFactory.getEnemyBaseEngine(), ImageFactory.getEnemyBoostEngine(),
                        ImageFactory.getEnemyBaseEngine(), ImageFactory.getEnemyBrakeImage(), false),
                makeEnemyWeaponDisplayable(enemyShip.getWeapon()));
    }

    public EntityDisplayable makeWingmanDisplayable(Ship wingman)
    {
        return new ShipDisplayable(wingman, new Explosion(wingman.getOrigin(), 250, Color.MAGENTA, 80),
                new ChassisDisplayable(wingman, new Point2D.Double(0,0 ), ImageFactory.getWingmanBaseChassis(), ImageFactory.getWingmanHurtChassis(),
                        ImageFactory.getWingmanNearDeathChassis()),
                new EngineDisplayable(wingman, new Point2D.Double(5, 20), ImageFactory.getWingmanBaseEngine(), ImageFactory.getWingmanBoostEngine(),
                        ImageFactory.getWingmanBoostEngine2(), ImageFactory.getWingmanBrakeImage(), true),
                makeWingmanWeaponDisplayable(wingman.getWeapon()),
                new SpecialDisplayable(wingman, new Point2D.Double(5, 15), ImageFactory.getWingmanBaseSpecial(), ImageFactory.getWingmanDeployedSpecial()));
    }

    // Ship Components:

    public ChassisDisplayable makePlayerChassisDisplayable(Ship playerShip)
    {
        return new ChassisDisplayable(playerShip, new Point2D.Double(0, 0), ImageFactory.getPlayerBaseChassis(), ImageFactory.getPlayerHurtChassis(),
            ImageFactory.getPlayerNearDeathChassis());
    }

    public EngineDisplayable makePlayerEngineDisplayable(Ship playerShip)
    {
        return new EngineDisplayable(playerShip, new Point2D.Double(12, 35), ImageFactory.getPlayerBaseEngine(), ImageFactory.getPlayerBoostEngine(),
                ImageFactory.getPlayerBoostEngine2(), ImageFactory.getPlayerBrakeImage(), true);
    }

    public SpecialDisplayable makePlayerSpecialDisplayable(Ship playerShip)
    {
        return new SpecialDisplayable(playerShip, new Point2D.Double(7, 24), ImageFactory.getPlayerBaseSpecial(), ImageFactory.getPlayerDeployedSpecial());
    }

    public WeaponDisplayable makePlayerWeaponDisplayable(ShipWeapon weapon)
    {
        return new WeaponDisplayable(weapon, new Point2D.Double(22, 0), ImageFactory.getPlayerBaseWeapon(), ImageFactory.getPlayerFiringWeapon());
    }

    public WeaponDisplayable makeOrangePlayerWeaponDisplayable(ShipWeapon weapon)
    {
        return new WeaponDisplayable(weapon, new Point2D.Double(22, 0), ImageFactory.getPlayerBaseWeapon(), ImageFactory.getPlayerFiringOrangeWeapon());
    }

    public WeaponDisplayable makeYellowPlayerWeaponDisplayable(ShipWeapon weapon)
    {
        return new WeaponDisplayable(weapon, new Point2D.Double(22, 0), ImageFactory.getPlayerBaseWeapon(), ImageFactory.getPlayerFiringYellowWeapon());
    }

    public WeaponDisplayable makeGreenPlayerWeaponDisplayable(ShipWeapon weapon)
    {
        return new WeaponDisplayable(weapon, new Point2D.Double(22, 0), ImageFactory.getPlayerBaseWeapon(), ImageFactory.getPlayerFiringGreenWeapon());
    }

    public WeaponDisplayable makeCyanPlayerWeaponDisplayable(ShipWeapon weapon)
    {
        return new WeaponDisplayable(weapon, new Point2D.Double(22, 0), ImageFactory.getPlayerBaseWeapon(), ImageFactory.getPlayerFiringCyanWeapon());
    }

    public WeaponDisplayable makeMagentaPlayerWeaponDisplayable(ShipWeapon weapon)
    {
        return new WeaponDisplayable(weapon, new Point2D.Double(22, 0), ImageFactory.getPlayerBaseWeapon(), ImageFactory.getPlayerFiringMagentaWeapon());
    }

    public WeaponDisplayable makeWingmanWeaponDisplayable(ShipWeapon shipWeapon)
    {
        return new WeaponDisplayable(shipWeapon, new Point2D.Double(10, 0), ImageFactory.getWingmanBaseWeapon(), ImageFactory.getWingmanFiringWeapon());
    }

    public WeaponDisplayable makeEnemyWeaponDisplayable(ShipWeapon shipWeapon)
    {
        return new WeaponDisplayable(shipWeapon, new Point2D.Double(6, 22), ImageFactory.getEnemyBaseWeapon(), ImageFactory.getEnemyFiringWeapon());
    }

    // Projectiles:

    public EntityDisplayable makeProjectileDisplayable(Projectile projectile)
    {
        return new SimpleEntityDisplayable(projectile, ImageFactory.getProjectileImage(),
                new Explosion(projectile.getOrigin(), 140, Color.GREEN, 30));
    }

    public EntityDisplayable makePlayerProjectileDisplayable(Projectile projectile)
    {
        return new SimpleEntityDisplayable(projectile, ImageFactory.getPlayerProjectileImage(),
                new Explosion(projectile.getOrigin(), Random.rollInt(120) + 80, Color.RED , 30));
    }

    public EntityDisplayable makeOrangePlayerProjectileDisplayable(Projectile projectile)
    {
        return new SimpleEntityDisplayable(projectile, ImageFactory.getOrangePlayerProjectileImage(),
                new Explosion(projectile.getOrigin(), Random.rollInt(120) + 80, Color.ORANGE , 30));
    }

    public EntityDisplayable makeYellowPlayerProjectileDisplayable(Projectile projectile)
    {
        return new SimpleEntityDisplayable(projectile, ImageFactory.getYellowPlayerProjectileImage(),
                new Explosion(projectile.getOrigin(), Random.rollInt(120) + 80, Color.YELLOW , 30));
    }

    public EntityDisplayable makeGreenPlayerProjectileDisplayable(Projectile projectile)
    {
        return new SimpleEntityDisplayable(projectile, ImageFactory.getGreenPlayerProjectileImage(),
                new Explosion(projectile.getOrigin(), Random.rollInt(120) + 80, Color.GREEN , 30));
    }

    public EntityDisplayable makeCyanPlayerProjectileDisplayable(Projectile projectile)
    {
        return new SimpleEntityDisplayable(projectile, ImageFactory.getCyanPlayerProjectileImage(),
                new Explosion(projectile.getOrigin(), Random.rollInt(120) + 80, Color.CYAN , 30));
    }

    public EntityDisplayable makeMagentaPlayerProjectileDisplayable(Projectile projectile)
    {
        return new SimpleEntityDisplayable(projectile, ImageFactory.getMagentaPlayerProjectileImage(),
                new Explosion(projectile.getOrigin(), Random.rollInt(120) + 80, Color.MAGENTA , 30));
    }

    public EntityDisplayable makeWingmanProjectileDisplayable(Projectile projectile)
    {
        return new SimpleEntityDisplayable(projectile, ImageFactory.getWingmanProjectileImage(),
                new Explosion(projectile.getOrigin(), 80, Color.RED, 20));
    }

    // Pickups:

    public EntityDisplayable makeHealthPickupDisplayable(Pickup pickup)
    {
        return new SimpleEntityDisplayable(pickup, ImageFactory.getHealthPickupImage(), new Explosion(pickup.getOrigin(), 100, Color.LIGHT_GRAY, 30));
    }

    public EntityDisplayable makeScorePickupDisplayable(Pickup pickup)
    {
        return new SimpleEntityDisplayable(pickup, ImageFactory.getScorePickupImage(), new Explosion(pickup.getOrigin(), 100, Color.LIGHT_GRAY, 30));
    }

    public EntityDisplayable makePowerPickupDisplayable(Pickup pickup)
    {
        return new SimpleEntityDisplayable(pickup, ImageFactory.getPowerPickupImage(), new Explosion(pickup.getOrigin(), 100, Color.LIGHT_GRAY, 30));
    }

    public EntityDisplayable makeWingmanPickupDisplayable(Pickup pickup)
    {
        return new SimpleEntityDisplayable(pickup, ImageFactory.getWingmanPickupImage(), new Explosion(pickup.getOrigin(), 100, Color.LIGHT_GRAY, 30));
    }

    public EntityDisplayable makeSuperHealthPickupDisplayable(Pickup pickup)
    {
        return new SimpleEntityDisplayable(pickup, ImageFactory.getSuperHealthPickupImage(), new Explosion(pickup.getOrigin(), 200, Color.YELLOW, 100));
    }

    public EntityDisplayable makeSuperWingmanPickupDisplayable(Pickup pickup)
    {
        return new SimpleEntityDisplayable(pickup, ImageFactory.getSuperWingmanPickupImage(), new Explosion(pickup.getOrigin(), 200, Color.YELLOW, 100));
    }

    public EntityDisplayable makeMegaHealthPickupDisplayable(Pickup pickup)
    {
        return new SubimageEntityDisplayable(pickup, ImageFactory.getMegaHealthPickupImage(), new Point(0, 0), 6, new Explosion(pickup.getOrigin(), 200, Color.PINK, 150));
    }

    public EntityDisplayable makeWeaponPickupDisplayable(Pickup pickup)
    {
        return new SubimageEntityDisplayable(pickup, ImageFactory.makeWeaponPickupImage(), new Point(0, 0), 6, new Explosion(pickup.getOrigin(), 200, Color.PINK, 150));
    }
}
