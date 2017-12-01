package Wylaga.Overstates.Game.Entities.Projectiles;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Collisions.Grid;
import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Overstates.Game.Entities.Ships.Ship;
import Wylaga.Overstates.Game.Entities.Team;
import Wylaga.Util.Trajectory;

import java.awt.*;
import java.awt.geom.Point2D;

public class Projectile extends Entity
{
    public static final Dimension defaultDimension = new Dimension(7, 7);
    public static final int defaultDamage = 10;

    private int damage;
    private boolean active;

    private EDVF edvf;

    public Projectile(Ship creator, EDVF edvf)
    {
        // todo: factor out Ship dependency by having Creator parameterize with all this stuff that's currently queried from it:
        super(new Point2D.Double(creator.getOrigin().x, creator.getOrigin().y), defaultDimension, creator.getTeam(), creator.getProjectileSpeed());
        int yInitial = (creator.getTeam() == Team.ENEMY) ? creator.getDimension().height : 0;
        super.translatePosition(creator.getDimension().width / 2 - defaultDimension.width / 2, yInitial);
        super.setTrajectory(creator.getProjectileTrajectory());
        this.damage = defaultDamage;
        this.active = true;
        this.edvf = edvf;
    }

    public Projectile(Point2D.Double position, Dimension dimension, Team team, double speed, Trajectory trajectory, int damage, EDVF edvf)
    {
        super(position, dimension, team, speed);
        super.setTrajectory(trajectory);
        this.damage = damage;
        this.active = true;
        this.edvf = edvf;
    }

    // Entity interface:
    public void update()
    {
        super.propelSelf();
        System.out.println("newloc: " + super.getOrigin());
    }
    public boolean expired() {return !active;}

    // Projectile interface:
    public int getDamage() {return damage;}
    public void deactivate()
    {
        active = false;
    }

    public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
    {
        return edvf.getDisplayable(entityDisplayableFactory, this);
    }

    public void addToCell(Grid.Cell cell) { cell.addProjectile(this); }

    // EDVF = EntityDisplayableVendingFunction
    public interface EDVF
    {
        EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory, Projectile projectile);
    }
}
