package Wylaga.Util.CollisionGrid;


import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.Ship;
import Wylaga.Util.Collision;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cell
{
    private Point origin;
    private Point terminus;

    private List<Ship> ships;
    private List<Projectile> projectiles;
    private List<EntityTuple> loggedCollisions;

    public Cell(Point origin, Dimension dimension)
    {
        this.origin = origin;
        this.terminus = new Point(origin.x + dimension.width - 1, origin.y + dimension.height - 1); //todo: maybe offset by -1?
        ships = new ArrayList<>();
        projectiles = new ArrayList<>();
        loggedCollisions = new ArrayList<>();
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
        loggedCollisions.clear();
    }

    public List<Ship> getShips() {return ships;}
    public List<Projectile> getProjectiles() {return projectiles;}

    public void logCollision(Entity entity1, Entity entity2)
    {
        loggedCollisions.add(new EntityTuple(entity1, entity2));
    }

    public boolean collisionLogged(Entity entity1, Entity entity2)
    {
        EntityTuple curCollision = new EntityTuple(entity1, entity2);
        for(EntityTuple collision : loggedCollisions)
        {
            if(collision.equivalentTo(curCollision))
                return true;
        }
        return false;
    }

    private class EntityTuple
    {
        protected Entity entity1;
        protected Entity entity2;

        public EntityTuple(Entity entity1, Entity entity2)
        {
            this.entity1 = entity1;
            this.entity2 = entity2;
        }

        public boolean equivalentTo(EntityTuple other)
        {
            return (this.entity1 == other.entity1 && this.entity2 == other.entity2) || (this.entity1 == other.entity2 && this.entity2 == other.entity1);
        }
    }
}
