package Wylaga.Overstates.Game.Collisions;


import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cell
{
    private Point origin;
    private Point terminus;

    private List<Ship> ships;
    private List<Projectile> projectiles;

    public Cell(Point origin, Dimension dimension)
    {
        this.origin = origin;
        this.terminus = new Point(origin.x + dimension.width - 1, origin.y + dimension.height - 1); //todo: maybe undo offset?
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

    public void clear()
    {
        ships.clear();
        projectiles.clear();
    }

    public List<Ship> getShips() {return ships;}
    public List<Projectile> getProjectiles() {return projectiles;}
}
