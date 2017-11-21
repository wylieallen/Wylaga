package Wylaga.Overstates.Game.Entities.Projectiles;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Collisions.Cell;
import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Overstates.Game.Entities.Ships.Ship;
import Wylaga.Overstates.Game.Entities.Team;
import Wylaga.Util.Trajectory;

import java.awt.*;

public class Projectile extends Entity
{
    public static final Dimension defaultDimension = new Dimension(7, 7);
    public static final int defaultDamage = 10;

    private int damage;
    private boolean active;

    public Projectile(Ship creator)
    {
        // todo: factor out Ship dependency by having Creator parameterize with all this stuff that's currently queried from it:
        super(new Point(creator.getOrigin()), defaultDimension, creator.getTeam(), creator.getProjectileSpeed());
        int yInitial = (creator.getTeam() == Team.ENEMY) ? creator.getDimension().height : 0;
        super.translatePosition(creator.getDimension().width / 2 - defaultDimension.width / 2, yInitial);
        super.setTrajectory(creator.getProjectileTrajectory());
        this.damage = defaultDamage;
        active = true;
    }

    public Projectile(Point position, Dimension dimension, Team team, double speed, Trajectory trajectory, int damage)
    {
        super(position, dimension, team, speed);
        super.setTrajectory(trajectory);
        this.damage = damage;
        this.active = true;
    }

    // Entity interface:
    public void update()
    {
        super.propelSelf();
    }
    public boolean expired() {return !active;}

    // Projectile interface:
    public int getDamage() {return damage;}
    public Team getTeam() {return super.getTeam();}
    public void deactivate()
    {
        active = false;
    }

    public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
    {
        return entityDisplayableFactory.makeProjectileDisplayable(this);
    }

    public void addToCell(Cell cell) { cell.addProjectile(this); }
}
