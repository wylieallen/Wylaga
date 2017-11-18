package Wylaga.Overstates.Game;

import Wylaga.Overstates.Game.Collisions.CollisionChecker;
import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;
import Wylaga.Overstates.Game.Entities.Ships.Ship;
import Wylaga.Overstates.Game.Collisions.Cell;
import Wylaga.Overstates.Game.Collisions.Grid;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game
{
    private List<List<? extends Entity>> entities;
    private List<Ship> ships;
    private List<Projectile> projectiles;

    private List<Ship> expiredShips;
    private List<Projectile> expiredProjectiles;
    private List<Entity> newEntities;

    private Dimension worldSize = new Dimension(1280, 720);

    private int score;
    private int waveCount;

    private PlayerShip playerShip;

    private Wave wave;

    private CollisionManager collisionManager;

    public Game()
    {
        score = 0;
        waveCount = 0;

        expiredShips = new ArrayList<>();
        expiredProjectiles = new ArrayList<>();
        newEntities = new ArrayList<>();

        entities = new ArrayList<>();
        entities.add(ships = new ArrayList<>());
        entities.add(projectiles = new ArrayList<>());

        spawnShip(playerShip = new PlayerShip());
        wave = new NullWave();
        collisionManager = new CollisionManager();
    }

    public void update()
    {
        // Updating then colliding prevents player from going slightly out of bounds
        // Colliding then updating allows player to go slightly out of bounds
        wave.update();
        updateEntities();
        collisionManager.processCollisions();
    }

    private void updateEntities()
    {
        newEntities.clear();
        updateShips();
        updateProjectiles();
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
                score += ship.getPoints();
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

    public void nextWave()
    {
        wave = new Wave(++waveCount);
        spawnShips(wave.getShips());
    }

    public boolean waveOver()
    {
        return wave.isDestroyed();
    }

    private void spawnShip(Ship ship)
    {
        ships.add(ship);
        newEntities.add(ship);
    }

    private void spawnShips(List<Ship> newShips)
    {
        ships.addAll(newShips);
        newEntities.addAll(newShips);
    }

    private void spawnProjectiles(List<Projectile> newProjectiles)
    {
        projectiles.addAll(newProjectiles);
        newEntities.addAll(newProjectiles);
    }

    public List<List<? extends Entity>> getEntities() { return entities; }
    public List<Entity> getNewEntities() { return newEntities; }
    public PlayerShip getPlayerShip()
    {
        return playerShip;
    }
    public int getScore() { return score; }

    public int getWaveCount() {
        return waveCount;
    }

    private class CollisionManager
    {
        private Grid grid;
        private ArrayList<EntityPair> loggedCollisions;

        private CollisionManager()
        {
            grid = new Grid(1280, 720, 16, 9);
            loggedCollisions = new ArrayList<>();
        }

        public void processCollisions()
        {
            constrainToWorld();
            resetGrid();
            for(Cell cell : grid.getCellList())
            {
                processProjectiles(cell);
                processShips(cell);
            }
        }

        private void processProjectiles(Cell cell)
        {
            for(Projectile projectile : cell.getProjectiles())
            {
                if(!CollisionChecker.entityOnWorld(projectile, worldSize))
                {
                    projectile.deactivate();
                    continue;
                }

                for(Ship ship : cell.getShips())
                {
                    if(ship.vulnerableTo(projectile) && CollisionChecker.entitiesCollide(projectile, ship) && !collisionLogged(projectile, ship))
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
            if(playerShip.isAlive())
            {
                for(Ship ship : cell.getShips())
                {
                    if(CollisionChecker.entitiesCollide(playerShip, ship) && !collisionLogged(playerShip, ship))
                    {
                        logCollision(playerShip, ship);
                        playerShip.takeDamage(30);
                        ship.takeDamage(30);
                    }
                }
            }
        }

        private void constrainToWorld()
        {
            if(!CollisionChecker.entityInWorld(playerShip, worldSize))
            {
                Point point = playerShip.getOrigin();

                int xMax = worldSize.width - playerShip.getDimension().width;
                int yMax = worldSize.height - playerShip.getDimension().height;

                int x = point.x;
                int y = point.y;

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

                //System.out.println("Setting loc");
                point.setLocation(x, y);
            }
        }

        private void resetGrid()
        {
            grid.clear();
            grid.addAll(ships);
            grid.addAll(projectiles);
            loggedCollisions.clear();
        }

        public void logCollision(Entity entity1, Entity entity2)
        {
            loggedCollisions.add(new EntityPair(entity1, entity2));
        }

        public boolean collisionLogged(Entity entity1, Entity entity2)
        {
            EntityPair curCollision = new EntityPair(entity1, entity2);
            for(EntityPair collision : loggedCollisions)
            {
                if(collision.equivalentTo(curCollision))
                    return true;
            }
            return false;
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
