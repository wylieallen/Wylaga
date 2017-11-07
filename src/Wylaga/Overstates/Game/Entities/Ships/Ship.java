package Wylaga.Overstates.Game.Entities.Ships;

import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Team;
import Wylaga.Util.Trajectory;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public abstract class Ship extends Entity
{
    private int health;
    private int points;

    private boolean firing;
    private boolean dying;
    private Trajectory projectileTrajectory;
    private double projectileSpeed = 12;
    private boolean alive;
    private Projectile newProjectile;

    public Ship(Point position, Dimension dimension, Team team, double speed, int health, Trajectory projectileTrajectory, int points)
    {
        super(position, dimension, team, speed);
        firing = false;
        dying = false;
        this.health = health;
        this.projectileTrajectory = projectileTrajectory;
        this.points = points;
    }

    public void update()
    {
        super.propelSelf();

        if (!isAlive())
        {
            dying = true;
        }
    }

    public boolean isAlive()
    {
        return health > 0;
    }

    public boolean expired()
    {
        return !isAlive();
    }

    public double getProjectileSpeed() {return projectileSpeed;}

    public Trajectory getProjectileTrajectory() {
        return projectileTrajectory;
    }

    public int getPoints() {
        return points;
    }
    public int getHealth() { return health; }

    public ArrayList<Projectile> getNewProjectiles()
    {
        ArrayList<Projectile> newProjectiles = new ArrayList<>();
        newProjectiles.add(getNewProjectile());
        return newProjectiles;
    }

    private Projectile getNewProjectile()
    {
        return new Projectile(this);
    }

    public void setFiring(boolean firing)
    {
        this.firing = firing;
    }

    public boolean isFiring()
    {
        return firing;
    }

    public void takeDamage(int damage)
    {
        health -= damage;
    }

    public boolean vulnerableTo(Projectile projectile)
    {
        return projectile.getTeam() != this.getTeam();
    }
}
