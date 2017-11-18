package Wylaga.Overstates.Game.Collisions;


import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.util.HashSet;
import java.util.Set;

public class Cell
{
    private Set<Ship> ships;
    private Set<Projectile> projectiles;

    public Cell()
    {
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

    public void clear()
    {
        ships.clear();
        projectiles.clear();
    }
}
