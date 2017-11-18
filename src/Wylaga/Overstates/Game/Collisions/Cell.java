package Wylaga.Overstates.Game.Collisions;


import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.util.ArrayList;
import java.util.List;

public class Cell
{
    private List<Ship> ships;
    private List<Projectile> projectiles;

    public Cell()
    {
        ships = new ArrayList<>();
        projectiles = new ArrayList<>();
    }

    public void addShip(Ship ship)
    {
        ships.add(ship);
    }
    public void addProjectile(Projectile projectile)
    {
        projectiles.add(projectile);
    }

    public List<Ship> getShips() {return ships;}
    public List<Projectile> getProjectiles() {return projectiles;}

    public void clear()
    {
        ships.clear();
        projectiles.clear();
    }
}
