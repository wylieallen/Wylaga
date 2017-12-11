package Wylaga.Overstates.Game.Entities.Ships;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Collisions.Grid;
import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.ShipComponents.ShipChassis;
import Wylaga.Overstates.Game.Entities.Ships.ShipComponents.ShipPropulsion;
import Wylaga.Overstates.Game.Entities.Ships.ShipComponents.ShipSpecial;
import Wylaga.Overstates.Game.Entities.Ships.ShipComponents.ShipWeapon;
import Wylaga.Util.Trajectory;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

public abstract class Ship extends Entity
{
    private int maxHealth = 100;
    private int health;
    private int points;

    private boolean firing;
    private boolean dying;
    private Trajectory projectileTrajectory;
    private double projectileSpeed = 12;

    private ShipChassis chassis;
    private ShipWeapon weapon;
    private ShipSpecial special;
    private ShipPropulsion propulsion;

    // =================================================================================================================
    // Constructor:

    public Ship(Point2D.Double point, Team team, ShipChassis chassis, ShipWeapon weapon, ShipSpecial special, ShipPropulsion propulsion)
    {
        super(point, chassis.getDimension(), team, propulsion.getSpeed());
        this.chassis = chassis;
        this.weapon = weapon;
        this.special = special;
        this.propulsion = propulsion;
    }

    public Ship(Point2D.Double position, Dimension dimension, Team team, double speed, int health, Trajectory projectileTrajectory, int points)
    {
        super(position, dimension, team, speed);
        firing = false;
        dying = false;
        this.health = health;
        this.projectileTrajectory = projectileTrajectory;
        this.points = points;
    }

    // =================================================================================================================
    // Accessors:

    public double getProjectileSpeed() {return projectileSpeed;}

    public Trajectory getProjectileTrajectory() {
        return projectileTrajectory;
    }

    public int getPoints() {
        return points;
    }
    public int getHealth() { return health; }

    public Set<Projectile> getNewProjectiles()
    {
        Set<Projectile> newProjectiles = new HashSet<>();
        newProjectiles.add(getNewProjectile());
        return newProjectiles;
    }

    protected Projectile getNewProjectile()
    {
        return new Projectile(this, EntityDisplayableFactory::makeProjectileDisplayable);
    }

    public boolean vulnerableTo(Projectile projectile)
    {
        return projectile.getTeam() != this.getTeam();
    }

    // todo: getCurFuel() is only used by ShipDisplayable's SpecialDisplayable, eliminate that dependency to factor this out
    public int getCurFuel() { return 0; }

    public boolean specialDeployed() { return false; }
    public boolean isFiring()
    {
        return firing;
    }
    public boolean isAlive()
    {
        return health > 0;
    }
    public boolean expired()
    {
        return dying;
    }

    // =================================================================================================================
    // Mutators:

    public void setFiring(boolean firing)
    {
        this.firing = firing;
    }

    public void takeDamage(int damage)
    {
        health -= damage;
    }

    public void heal(int healing)
    {
        health += healing;
        if(health > maxHealth) health = maxHealth;
    }

    public void addToCell(Grid.Cell cell) { cell.addShip(this); }

    public void update()
    {
        super.propelSelf();
        if(!isAlive())
        {
            dying = true;
        }
    }
}
