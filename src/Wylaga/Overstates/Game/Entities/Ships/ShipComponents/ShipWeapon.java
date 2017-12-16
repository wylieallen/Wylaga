package Wylaga.Overstates.Game.Entities.Ships.ShipComponents;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.WeaponDisplayable;
import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Overstates.Game.Entities.Projectiles.LinearProjectile;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Util.Trajectory;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

public abstract class ShipWeapon
{
    protected Point2D.Double projectileOrigin;
    protected int projectileSpeed;
    protected Trajectory projectileTrajectory;
    protected int projectileDamage;
    private boolean firing = false;

    public ShipWeapon(Point2D.Double projectileOrigin, int projectileSpeed, Trajectory projectileTrajectory, int projectileDamage)
    {
        this.projectileOrigin = projectileOrigin;
        this.projectileSpeed = projectileSpeed;
        this.projectileTrajectory = projectileTrajectory;
        this.projectileDamage = projectileDamage;
    }

    public Set<Projectile> makeProjectiles(Point2D.Double shipOrigin)
    {
        Set<Projectile> projectiles = new HashSet<>();
        populateProjectiles(projectiles, shipOrigin);
        return projectiles;
    }

    protected abstract void populateProjectiles(Set<Projectile> projectiles, Point2D.Double shipOrigin);

    public int getProjectileSpeed()
    {
        return projectileSpeed;
    }

    public Trajectory getProjectileTrajectory()
    {
        return projectileTrajectory;
    }

    public boolean isFiring() { return firing; }

    public void setFiring(boolean firing) { this.firing = firing; }

    public abstract WeaponDisplayable getDisplayable(EntityDisplayableFactory edf);


    // Factory methods:

    public static ShipWeapon getEnemyWeapon()
    {
        return new ShipWeapon(new Point2D.Double(9, 25), 12, Trajectory.getDirection(0, 1), 10)
        {
            private final Dimension defaultDimension = new Dimension(7, 7);
            private int defaultSpeed = 12;
            private int defaultDamage = 10;

            protected void populateProjectiles(Set<Projectile> projectiles, Point2D.Double shipOrigin)
            {
                projectiles.add(new LinearProjectile(new Point2D.Double(shipOrigin.x + projectileOrigin.x, shipOrigin.y + projectileOrigin.y), defaultDimension,
                        Entity.Team.ENEMY, defaultSpeed, projectileTrajectory, defaultDamage, EntityDisplayableFactory::makeProjectileDisplayable));
            }

            public WeaponDisplayable getDisplayable(EntityDisplayableFactory edf)
            {
                return edf.makeEnemyWeaponDisplayable(this);
            }
        };
    }

    public static ShipWeapon getPlayerWeapon()
    {
        return new ShipWeapon(new Point2D.Double(23, -6), 15, Trajectory.getDirection(0, -1), 5)
        {
            private final Dimension defaultDimension = new Dimension(4, 15);
            private int defaultSpeed = 15;
            private int defaultDamage = 5;

            protected void populateProjectiles(Set<Projectile> projectiles, Point2D.Double shipOrigin)
            {
                //projectiles.add(new PlayerProjectile(new Point2D.Double(shipOrigin.x + projectileOrigin.x, shipOrigin.y + projectileOrigin.y),
                //        Entity.Team.PLAYER, projectileSpeed, projectileTrajectory, projectileDamage));
                projectiles.add(new LinearProjectile(new Point2D.Double(shipOrigin.x + projectileOrigin.x, shipOrigin.y + projectileOrigin.y), defaultDimension,
                        Entity.Team.PLAYER, defaultSpeed, projectileTrajectory, defaultDamage, EntityDisplayableFactory::makePlayerProjectileDisplayable));
            }

            public WeaponDisplayable getDisplayable(EntityDisplayableFactory edf)
            {
                return edf.makePlayerWeaponDisplayable(this);
            }
        };
    }

    public static ShipWeapon getOrangePlayerWeapon()
    {
        return new ShipWeapon(new Point2D.Double(23, -6), 15, Trajectory.getDirection(0, -1), 10)
        {
            private final Dimension defaultDimension = new Dimension(4, 15);
            private int defaultSpeed = 15;
            private int defaultDamage = 10;

            protected void populateProjectiles(Set<Projectile> projectiles, Point2D.Double shipOrigin)
            {
                //projectiles.add(new PlayerProjectile(new Point2D.Double(shipOrigin.x + projectileOrigin.x, shipOrigin.y + projectileOrigin.y),
                //        Entity.Team.PLAYER, projectileSpeed, projectileTrajectory, projectileDamage));
                projectiles.add(new LinearProjectile(new Point2D.Double(shipOrigin.x + projectileOrigin.x, shipOrigin.y + projectileOrigin.y), defaultDimension,
                        Entity.Team.PLAYER, defaultSpeed, projectileTrajectory, defaultDamage, EntityDisplayableFactory::makeOrangePlayerProjectileDisplayable));
            }

            public WeaponDisplayable getDisplayable(EntityDisplayableFactory edf)
            {
                return edf.makeOrangePlayerWeaponDisplayable(this);
            }
        };
    }

    public static ShipWeapon getYellowPlayerWeapon()
    {
        return new ShipWeapon(new Point2D.Double(23, -6), 15, Trajectory.getDirection(0, -1), 15)
        {
            private final Dimension defaultDimension = new Dimension(4, 15);
            private int defaultSpeed = 15;
            private int defaultDamage = 15;

            protected void populateProjectiles(Set<Projectile> projectiles, Point2D.Double shipOrigin)
            {
                //projectiles.add(new PlayerProjectile(new Point2D.Double(shipOrigin.x + projectileOrigin.x, shipOrigin.y + projectileOrigin.y),
                //        Entity.Team.PLAYER, projectileSpeed, projectileTrajectory, projectileDamage));
                projectiles.add(new LinearProjectile(new Point2D.Double(shipOrigin.x + projectileOrigin.x, shipOrigin.y + projectileOrigin.y), defaultDimension,
                        Entity.Team.PLAYER, defaultSpeed, projectileTrajectory, defaultDamage, EntityDisplayableFactory::makeYellowPlayerProjectileDisplayable));
            }

            public WeaponDisplayable getDisplayable(EntityDisplayableFactory edf)
            {
                return edf.makeYellowPlayerWeaponDisplayable(this);
            }
        };
    }

    public static ShipWeapon getGreenPlayerWeapon()
    {
        return new ShipWeapon(new Point2D.Double(23, -6), 15, Trajectory.getDirection(0, -1), 20)
        {
            private final Dimension defaultDimension = new Dimension(4, 15);
            private int defaultSpeed = 15;
            private int defaultDamage = 20;

            protected void populateProjectiles(Set<Projectile> projectiles, Point2D.Double shipOrigin)
            {
                //projectiles.add(new PlayerProjectile(new Point2D.Double(shipOrigin.x + projectileOrigin.x, shipOrigin.y + projectileOrigin.y),
                //        Entity.Team.PLAYER, projectileSpeed, projectileTrajectory, projectileDamage));
                projectiles.add(new LinearProjectile(new Point2D.Double(shipOrigin.x + projectileOrigin.x, shipOrigin.y + projectileOrigin.y), defaultDimension,
                        Entity.Team.PLAYER, defaultSpeed, projectileTrajectory, defaultDamage, EntityDisplayableFactory::makeGreenPlayerProjectileDisplayable));
            }

            public WeaponDisplayable getDisplayable(EntityDisplayableFactory edf)
            {
                return edf.makeGreenPlayerWeaponDisplayable(this);
            }
        };
    }

    public static ShipWeapon getCyanPlayerWeapon()
    {
        return new ShipWeapon(new Point2D.Double(23, -6), 15, Trajectory.getDirection(0, -1), 25)
        {
            private final Dimension defaultDimension = new Dimension(4, 15);
            private int defaultSpeed = 15;
            private int defaultDamage = 25;

            protected void populateProjectiles(Set<Projectile> projectiles, Point2D.Double shipOrigin)
            {
                //projectiles.add(new PlayerProjectile(new Point2D.Double(shipOrigin.x + projectileOrigin.x, shipOrigin.y + projectileOrigin.y),
                //        Entity.Team.PLAYER, projectileSpeed, projectileTrajectory, projectileDamage));
                projectiles.add(new LinearProjectile(new Point2D.Double(shipOrigin.x + projectileOrigin.x, shipOrigin.y + projectileOrigin.y), defaultDimension,
                        Entity.Team.PLAYER, defaultSpeed, projectileTrajectory, defaultDamage, EntityDisplayableFactory::makeCyanPlayerProjectileDisplayable));
            }

            public WeaponDisplayable getDisplayable(EntityDisplayableFactory edf)
            {
                return edf.makeCyanPlayerWeaponDisplayable(this);
            }
        };
    }

    public static ShipWeapon getMagentaPlayerWeapon()
    {
        return new ShipWeapon(new Point2D.Double(23, -6), 15, Trajectory.getDirection(0, -1), 30)
        {
            private final Dimension defaultDimension = new Dimension(4, 15);
            private int defaultSpeed = 15;
            private int defaultDamage = 30;

            protected void populateProjectiles(Set<Projectile> projectiles, Point2D.Double shipOrigin)
            {
                //projectiles.add(new PlayerProjectile(new Point2D.Double(shipOrigin.x + projectileOrigin.x, shipOrigin.y + projectileOrigin.y),
                //        Entity.Team.PLAYER, projectileSpeed, projectileTrajectory, projectileDamage));
                projectiles.add(new LinearProjectile(new Point2D.Double(shipOrigin.x + projectileOrigin.x, shipOrigin.y + projectileOrigin.y), defaultDimension,
                        Entity.Team.PLAYER, defaultSpeed, projectileTrajectory, defaultDamage, EntityDisplayableFactory::makeMagentaPlayerProjectileDisplayable));
            }

            public WeaponDisplayable getDisplayable(EntityDisplayableFactory edf)
            {
                return edf.makeMagentaPlayerWeaponDisplayable(this);
            }
        };
    }


    public static ShipWeapon getWingmanWeapon()
    {
        return new ShipWeapon(new Point2D.Double(12, -2), 12, Trajectory.getDirection(0, -1), 5)
        {
            private final Dimension defaultDimension = new Dimension(1, 10);
            private int defaultSpeed = 12;
            private int defaultDamage = 5;

            protected void populateProjectiles(Set<Projectile> projectiles, Point2D.Double shipOrigin)
            {
                //projectiles.add(new WingmanProjectile(new Point2D.Double(shipOrigin.x + projectileOrigin.x, shipOrigin.y + projectileOrigin.y),
                //        Entity.Team.PLAYER, projectileSpeed, projectileTrajectory, projectileDamage));
                projectiles.add(new LinearProjectile(new Point2D.Double(shipOrigin.x + projectileOrigin.x, shipOrigin.y + projectileOrigin.y), defaultDimension,
                        Entity.Team.PLAYER, defaultSpeed, projectileTrajectory, defaultDamage, EntityDisplayableFactory::makeWingmanProjectileDisplayable));
            }

            public WeaponDisplayable getDisplayable(EntityDisplayableFactory edf)
            {
                return edf.makeWingmanWeaponDisplayable(this);
            }
        };
    }

    /*
    public static ShipWeapon getEnemyWeapon()
    {
        return new ShipWeapon(new Point())
        {
            protected void populateProjectiles(Set<LinearProjectile> projectiles, Point shipOrigin)
            {
                //projectiles.add(new LinearProjectile())
            }
        }
    }
    */
}
