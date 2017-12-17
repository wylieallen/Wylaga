package Wylaga.Overstates.Game.Entities.Ships.ShipComponents;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.WeaponDisplayable;
import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Overstates.Game.Entities.Projectiles.LinearProjectile;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Util.Trajectory;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ShipWeapon
{
    private Set<Projectile> prototypes;

    private WDVF wdvf;
    private boolean firing = false;

    public ShipWeapon(WDVF wdvf, Set<Projectile> prototypes)
    {
        this.prototypes = prototypes;
        this.wdvf = wdvf;
    }

    public Set<Projectile> makeProjectiles(Point2D.Double shipOrigin)
    {
        Set<Projectile> projectiles = new HashSet<>();
        for(Projectile prototype : prototypes)
        {
            projectiles.add(new Projectile(shipOrigin, prototype));
        }
        return projectiles;
    }

    //protected abstract void populateProjectiles(Set<Projectile> projectiles, Point2D.Double shipOrigin);

    public boolean isFiring() { return firing; }

    public void setFiring(boolean firing) { this.firing = firing; }

    public WeaponDisplayable getDisplayable(EntityDisplayableFactory edf)
    {
        return wdvf.getDisplayable(edf, this);
    }

    private interface WDVF
    {
        WeaponDisplayable getDisplayable(EntityDisplayableFactory edf, ShipWeapon weapon);
    }

    // Factory methods:

    private static Set<Projectile> makeSet(Projectile... projectiles)
    {
        Set<Projectile> set = new HashSet<>();
        set.addAll(Arrays.asList(projectiles));
        return set;
    }

    public static ShipWeapon getEnemyWeapon()
    {
        return new ShipWeapon(EntityDisplayableFactory::makeEnemyWeaponDisplayable,
                makeSet(new LinearProjectile(new Point2D.Double(9, 25), new Dimension(7, 7),
                        Entity.Team.ENEMY, 12, new Trajectory(0, 1), 10, EntityDisplayableFactory::makeProjectileDisplayable))
        );
    }

    public static ShipWeapon getPlayerWeapon()
    {
        return new ShipWeapon(EntityDisplayableFactory::makePlayerWeaponDisplayable,
                makeSet(new LinearProjectile(new Point2D.Double(23, -6), new Dimension(4, 15),
                        Entity.Team.PLAYER, 15, new Trajectory(0, -1), 5, EntityDisplayableFactory::makePlayerProjectileDisplayable))
        );
    }

    public static ShipWeapon getOrangePlayerWeapon()
    {
        return new ShipWeapon(EntityDisplayableFactory::makeOrangePlayerWeaponDisplayable,
                makeSet(new LinearProjectile(new Point2D.Double(23, -6), new Dimension(4, 15),
                        Entity.Team.PLAYER, 15, new Trajectory(0, -1), 10, EntityDisplayableFactory::makeOrangePlayerProjectileDisplayable))
        );
    }

    public static ShipWeapon getYellowPlayerWeapon()
    {
        return new ShipWeapon(EntityDisplayableFactory::makeYellowPlayerWeaponDisplayable,
                makeSet(new LinearProjectile(new Point2D.Double(23, -6), new Dimension(4, 15),
                        Entity.Team.PLAYER, 15, new Trajectory(0, -1), 15, EntityDisplayableFactory::makeYellowPlayerProjectileDisplayable))
        );
    }

    public static ShipWeapon getGreenPlayerWeapon()
    {
        return new ShipWeapon(EntityDisplayableFactory::makeGreenPlayerWeaponDisplayable,
                makeSet(new LinearProjectile(new Point2D.Double(23, -6), new Dimension(4, 15),
                        Entity.Team.PLAYER, 15, new Trajectory(0, -1), 20, EntityDisplayableFactory::makeGreenPlayerProjectileDisplayable))
        );
    }

    public static ShipWeapon getCyanPlayerWeapon()
    {
        return new ShipWeapon(EntityDisplayableFactory::makeCyanPlayerWeaponDisplayable,
                makeSet(new LinearProjectile(new Point2D.Double(23, -6), new Dimension(4, 15),
                        Entity.Team.PLAYER, 15, new Trajectory(0, -1), 25, EntityDisplayableFactory::makeCyanPlayerProjectileDisplayable))
        );
    }

    public static ShipWeapon getMagentaPlayerWeapon()
    {
        return new ShipWeapon(EntityDisplayableFactory::makeMagentaPlayerWeaponDisplayable,
                makeSet(new LinearProjectile(new Point2D.Double(23, -6), new Dimension(4, 15),
                        Entity.Team.PLAYER, 15, new Trajectory(0, -1), 30, EntityDisplayableFactory::makeMagentaPlayerProjectileDisplayable))
        );
    }

    public static ShipWeapon getWingmanWeapon()
    {
        return new ShipWeapon(EntityDisplayableFactory::makeWingmanWeaponDisplayable,
                makeSet(new LinearProjectile(new Point2D.Double(12, -2), new Dimension(1, 10),
                        Entity.Team.PLAYER, 12, new Trajectory(0, -1), 5, EntityDisplayableFactory::makeWingmanProjectileDisplayable))
        );
    }
}
