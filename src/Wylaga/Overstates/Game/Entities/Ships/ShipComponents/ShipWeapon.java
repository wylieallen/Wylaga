package Wylaga.Overstates.Game.Entities.Ships.ShipComponents;

import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Overstates.Game.Entities.Projectiles.PlayerProjectile;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Util.Trajectory;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

public abstract class ShipWeapon
{
    protected Point projectileOrigin;
    protected int projectileSpeed;
    protected Trajectory projectileTrajectory;
    protected int projectileDamage;

    public ShipWeapon(Point projectileOrigin, int projectileSpeed, Trajectory projectileTrajectory, int projectileDamage)
    {
        this.projectileOrigin = projectileOrigin;
        this.projectileSpeed = projectileSpeed;
        this.projectileTrajectory = projectileTrajectory;
        this.projectileDamage = projectileDamage;
    }

    public Set<Projectile> makeProjectiles(Point shipOrigin)
    {
        Set<Projectile> projectiles = new HashSet<>();
        populateProjectiles(projectiles, shipOrigin);
        return projectiles;
    }

    protected abstract void populateProjectiles(Set<Projectile> projectiles, Point shipOrigin);

    public int getProjectileSpeed()
    {
        return projectileSpeed;
    }

    public Trajectory getProjectileTrajectory()
    {
        return projectileTrajectory;
    }

    public static ShipWeapon getPlayerWeapon()
    {
        return new ShipWeapon(new Point(23, -6), 15, Trajectory.getDirection(0, -1), 10)
        {
            protected void populateProjectiles(Set<Projectile> projectiles, Point shipOrigin)
            {
                projectiles.add(new PlayerProjectile(new Point2D.Double(shipOrigin.x + projectileOrigin.x, shipOrigin.y + projectileOrigin.y),
                        Entity.Team.PLAYER, projectileSpeed, projectileTrajectory, projectileDamage));
            }
        };
    }

    /*
    public static ShipWeapon getEnemyWeapon()
    {
        return new ShipWeapon(new Point())
        {
            protected void populateProjectiles(Set<Projectile> projectiles, Point shipOrigin)
            {
                //projectiles.add(new Projectile())
            }
        }
    }
    */
}
