package Wylaga.Overstates.Game.Entities.Ships;

import Wylaga.Overstates.Game.Collisions.Cell;
import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Team;
import Wylaga.Util.Trajectory;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public abstract class Ship extends Entity
{
    private int health;
    private int points;

    private boolean firing;
    private boolean dying;
    private Trajectory projectileTrajectory;
    private double projectileSpeed = 12;

    // =================================================================================================================
    // Constructor:

    public Ship(Point position, Dimension dimension, Team team, double speed, int health, Trajectory projectileTrajectory, int points)
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

    private Projectile getNewProjectile()
    {
        return new Projectile(this);
    }

    public boolean vulnerableTo(Projectile projectile)
    {
        return projectile.getTeam() != this.getTeam();
    }

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

    public void addToCell(Cell cell) { cell.addShip(this); }

    public void update()
    {
        super.propelSelf();
        if(!isAlive())
        {
            dying = true;
        }
    }
}
