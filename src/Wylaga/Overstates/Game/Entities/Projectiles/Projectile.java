package Wylaga.Overstates.Game.Entities.Projectiles;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Collisions.Grid;
import Wylaga.Overstates.Game.Entities.Entity;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Projectile extends Entity
{
    private int damage;
    private boolean active;
    private EDVF edvf;

    protected Projectile(Point2D.Double position, Dimension dimension, Team team, double speed, int damage, EDVF edvf)
    {
        super(position, dimension, team, speed);
        this.damage = damage;
        this.edvf = edvf;
        this.active = true;
    }

    // Entity interface:
    public void update()
    {
        propelSelf();
    }

    public boolean expired() {return !active;}

    // Projecitle interface:

    public void translatePosition(double dx, double dy)
    {
        super.translatePosition(dx, dy);
    }

    public int getDamage() {return damage;}

    public void deactivate() {active = false;}

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
