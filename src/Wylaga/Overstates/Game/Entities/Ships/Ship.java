package Wylaga.Overstates.Game.Entities.Ships;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Collisions.Grid;
import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Overstates.Game.Entities.Projectiles.LinearProjectile;
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

    private boolean dying;

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

    public Ship(Point2D.Double position, Dimension dimension, Team team, double speed, int health, int points, ShipWeapon weapon)
    {
        super(position, dimension, team, speed);
        dying = false;
        this.health = health;
        this.points = points;
        this.weapon = weapon;
    }

    // =================================================================================================================
    // Accessors:


    public ShipWeapon getWeapon() { return weapon; }

    public int getPoints() {
        return points;
    }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }

    public Set<Projectile> getNewProjectiles()
    {
        return weapon.makeProjectiles(getOrigin());
    }

    public boolean vulnerableTo(Projectile projectile)
    {
        return projectile.getTeam() != this.getTeam();
    }

    // todo: getCurFuel() is only used by ShipDisplayable's SpecialDisplayable, eliminate that dependency to factor this out
    public int getCurFuel() { return 0; }

    public boolean specialDeployed() { return false; }
    public boolean isFiring() { return weapon.isFiring(); }
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
        weapon.setFiring(firing);
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

    public void upgradeMaxHealth(int upgrade)
    {
        maxHealth += upgrade;
    }

    public void setWeapon(ShipWeapon weapon) {
        this.weapon = weapon;
    }
}
