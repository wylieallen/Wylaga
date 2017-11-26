package Wylaga.Overstates.Game;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Game.Collisions.CollisionChecker;
import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Overstates.Game.Entities.Pickups.*;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;
import Wylaga.Overstates.Game.Entities.Ships.Ship;
import Wylaga.Overstates.Game.Collisions.Cell;
import Wylaga.Overstates.Game.Collisions.Grid;
import Wylaga.Overstates.Game.Entities.Ships.Wingman;
import Wylaga.Util.Random;


import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Game
{
    private Set<Set<? extends Entity>> entities;
    private Set<Ship> ships;
    private Set<Projectile> projectiles;
    private Set<Pickup> pickups;

    private Set<Ship> expiredShips;
    private Set<Projectile> expiredProjectiles;
    private Set<Pickup> expiredPickups;
    private Set<Entity> newEntities;

    private Dimension worldSize = new Dimension(1280, 720);

    private int score;
    private int waveCount;

    private PlayerShip playerShip;
    private Wingman leftWingman;
    private Wingman rightWingman;

    private Wave wave;

    private CollisionManager collisionManager;

    public Game()
    {
        score = 0;
        waveCount = 0;

        expiredShips = new HashSet<>();
        expiredProjectiles = new HashSet<>();
        expiredPickups = new HashSet<>();
        newEntities = Collections.newSetFromMap(new ConcurrentHashMap<Entity, Boolean>());

        entities = Collections.newSetFromMap(new ConcurrentHashMap<Set<? extends Entity>, Boolean>());
        entities.add(ships = Collections.newSetFromMap(new ConcurrentHashMap<Ship, Boolean>()));
        entities.add(projectiles = Collections.newSetFromMap(new ConcurrentHashMap<Projectile, Boolean>()));
        entities.add(pickups = Collections.newSetFromMap(new ConcurrentHashMap<Pickup, Boolean>()));

        spawnShip(playerShip = new PlayerShip());
        spawnShip(leftWingman = new Wingman(playerShip, new Point(-25, 46)));
        spawnShip(rightWingman = new Wingman(playerShip, new Point(50, 46)));

        wave = new NullWave();
        collisionManager = new CollisionManager();
    }

    public void update()
    {
        // Updating then colliding prevents player from going slightly out of bounds
        // Colliding then updating allows player to go slightly out of bounds before snapping back
        collisionManager.constrainToWorld();
        wave.update();
        updateEntities();
        collisionManager.processCollisions();
    }

    public void respawnWingmen()
    {
        leftWingman.terminate();
        rightWingman.terminate();
        spawnShip(leftWingman = new Wingman(playerShip, new Point(-25, 50)));
        spawnShip(rightWingman = new Wingman(playerShip, new Point(50, 50)));
    }

    private void updateEntities()
    {
        updateShips();
        updateProjectiles();
        updatePickups();
    }

    private void updateShips()
    {
        expiredShips.clear();

        for (Ship ship : ships)
        {
            ship.update();
            if(ship.expired())
            {
                expiredShips.add(ship);
                addPoints(ship.getPoints());
                if(Random.rollInt(10) == 0)
                {
                    spawnPickup(PickupFactory.makePickup(this, ship.getOrigin()));
                }
            }
            else if(ship.isFiring())
            {
                spawnProjectiles(ship.getNewProjectiles());
            }
        }

        ships.removeAll(expiredShips);
    }

    private void updateProjectiles()
    {
        expiredProjectiles.clear();

        for (Projectile projectile : projectiles)
        {
            projectile.update();
            if(projectile.expired())
            {
                expiredProjectiles.add(projectile);
            }
        }

        projectiles.removeAll(expiredProjectiles);
    }

    private void updatePickups()
    {
        expiredPickups.clear();

        for(Pickup pickup : pickups)
        {
            pickup.update();
            if(pickup.expired())
            {
                expiredPickups.add(pickup);
            }
        }

        pickups.removeAll(expiredPickups);
    }

    public void nextWave()
    {
        wave = new Wave(++waveCount);
        spawnShips(wave.getShips());
    }

    public boolean waveOver()
    {
        return wave.isDestroyed();
    }

    public void addPoints(int points) { score += points; }

    private void spawnShip(Ship ship)
    {
        ships.add(ship);
        newEntities.add(ship);
    }

    private void spawnShips(Set<Ship> newShips)
    {
        ships.addAll(newShips);
        newEntities.addAll(newShips);
    }

    private void spawnProjectiles(Set<Projectile> newProjectiles)
    {
        projectiles.addAll(newProjectiles);
        newEntities.addAll(newProjectiles);
    }

    private void spawnPickup(Pickup pickup)
    {
        pickups.add(pickup);
        newEntities.add(pickup);
    }

    public Set<Set<? extends Entity>> getEntities() { return entities; }

    public Set<Entity> getNewEntities()
    {
        Set<Entity> newbies = newEntities;
        newEntities = new HashSet<>();
        return newbies;
    }

    public PlayerShip getPlayerShip()
    {
        return playerShip;
    }
    public int getScore() { return score; }

    public int getWaveCount() {
        return waveCount;
    }

    public Grid getGrid()
    {
        return collisionManager.grid;
    }

    private class CollisionManager
    {
        private Grid grid;
        private Set<EntityPair> loggedCollisions;

        private CollisionManager()
        {
            grid = new Grid(1280, 720, 16, 9);
            loggedCollisions = new HashSet<>();
        }

        public void processCollisions()
        {
            resetGrid();
            for(Cell cell : grid.getOccupiedCells())
            {
                processProjectiles(cell);
                if(playerShip.isAlive())
                {
                    processShips(cell);
                    processPickups(cell);
                }
            }
        }

        private void processProjectiles(Cell cell)
        {
            for(Projectile projectile : cell.getProjectiles())
            {
                if(CollisionChecker.entityNotNearWorld(projectile, worldSize))
                {
                    projectile.deactivate();
                    continue;
                }

                for(Ship ship : cell.getShips())
                {
                    if(ship.vulnerableTo(projectile) && CollisionChecker.entitiesCollide(projectile, ship) && collisionNotLogged(projectile, ship))
                    {
                        logCollision(projectile, ship);
                        projectile.deactivate();
                        ship.takeDamage(projectile.getDamage());
                        break; // break out of inner loop and move on to next projectile
                    }
                }
            }
        }

        private void processShips(Cell cell)
        {
            for(Ship ship : cell.getShips())
            {
                for(Ship otherShip : cell.getShips())
                {
                    if (ship.getTeam() != otherShip.getTeam() && CollisionChecker.entitiesCollide(otherShip, ship) && collisionNotLogged(otherShip, ship)) {
                        logCollision(otherShip, ship);
                        otherShip.takeDamage(30);
                        ship.takeDamage(30);
                    }
                }
            }
        }

        private void processPickups(Cell cell)
        {
            for(Pickup pickup : cell.getPickups())
            {
                if(CollisionChecker.entityNotNearWorld(pickup, worldSize))
                {
                    pickup.deactivate();
                    continue;
                }
                if(CollisionChecker.entitiesCollide(playerShip, pickup) && collisionNotLogged(playerShip, pickup))
                {
                    logCollision(playerShip, pickup);
                    pickup.trigger();
                    pickup.deactivate();
                }
            }
        }

        public void constrainToWorld()
        {
            if(!CollisionChecker.entityInWorld(playerShip, worldSize))
            {
                playerShip.flagAsConstrained();

                Point2D.Double point = playerShip.getOrigin();

                int xMax = worldSize.width - playerShip.getDimension().width;
                int yMax = worldSize.height - playerShip.getDimension().height;

                // We're concerned with pixel fidelity here, so cast position to int for processing:
                int x = (int) point.x;
                int y = (int) point.y;

                if(point.x < 0)
                {
                    x = 0;
                }
                else if(point.x > xMax)
                {
                    x = xMax;
                }

                if(point.y < 0)
                {
                    y = 0;
                }
                else if (point.y > yMax)
                {
                    y = yMax;
                }

                point.setLocation(x, y);

                if(leftWingman.isAlive())
                {
                    leftWingman.getOrigin().setLocation(x - 25, y + 46);
                }
                if(rightWingman.isAlive())
                {
                    rightWingman.getOrigin().setLocation(x + 50, y + 46);
                }
            }
            else
            {
                playerShip.flagAsUnconstrained();
            }
        }

        private void resetGrid()
        {
            grid.clear();
            grid.addAll(ships);
            grid.addAll(projectiles);
            grid.addAll(pickups);
            loggedCollisions.clear();
        }

        public void logCollision(Entity entity1, Entity entity2)
        {
            loggedCollisions.add(new EntityPair(entity1, entity2));
        }

        public boolean collisionNotLogged(Entity entity1, Entity entity2)
        {
            EntityPair curCollision = new EntityPair(entity1, entity2);
            for(EntityPair collision : loggedCollisions)
            {
                if(collision.equivalentTo(curCollision))
                    return false;
            }
            return true;
        }

        private class EntityPair
        {
            private Entity entity1;
            private Entity entity2;

            public EntityPair(Entity entity1, Entity entity2)
            {
                this.entity1 = entity1;
                this.entity2 = entity2;
            }

            public boolean equivalentTo(EntityPair other)
            {
                return (this.entity1 == other.entity1 && this.entity2 == other.entity2) || (this.entity1 == other.entity2 && this.entity2 == other.entity1);
            }
        }
    }
}
