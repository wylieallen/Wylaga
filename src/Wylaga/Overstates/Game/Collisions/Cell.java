package Wylaga.Overstates.Game.Collisions;


import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Cell
{
    private Set<Ship> ships;
    private Set<Projectile> projectiles;

    private Point origin;
    private Dimension size;

    public Cell(Point origin, Dimension size)
    {
        this.origin = origin;
        this.size = size;
        ships = new HashSet<>();
        projectiles = new HashSet<>();
    }

    public void addShip(Ship ship)
    {
        ships.add(ship);
    }
    public void addProjectile(Projectile projectile)
    {
        projectiles.add(projectile);
    }

    public Set<Ship> getShips() {return ships;}
    public Set<Projectile> getProjectiles() {return projectiles;}
    public Point getOrigin() {return origin;}
    public Dimension getSize() {return size;}

    public void clear()
    {
        ships.clear();
        projectiles.clear();
    }
}
