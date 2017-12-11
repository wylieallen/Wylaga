package Wylaga.Overstates.Game.Entities.Pickups;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Collisions.Grid;
import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Util.AbstractFunction;
import Wylaga.Util.Trajectory;

import java.awt.*;
import java.awt.geom.Point2D;

public class Pickup extends Entity
{
    private AbstractFunction effect;
    private EDVF edvf;
    private boolean expired;

    public Pickup(Point2D.Double position, AbstractFunction effect, EDVF edvf)
    {
        super(new Point2D.Double(position.x, position.y), new Dimension(15, 15), Team.NEUTRAL, 1);
        super.setTrajectory(Trajectory.getDirection(0, 1));
        this.edvf = edvf;
        this.effect = effect;
        this.expired = false;
    }

    public void update()
    {
        propelSelf();
    }

    public void trigger() { effect.execute(); }

    public void deactivate()
    {
        expired = true;
    }

    public boolean expired()
    {
        return expired;
    }

    public void addToCell(Grid.Cell cell)
    {
        cell.addPickup(this);
    }

    public EntityDisplayable getDisplayable(EntityDisplayableFactory edf)
    {
        return edvf.getDisplayable(edf, this);
    }

    // EDVF = EntityDisplayableVendingFunction
    public interface EDVF
    {
        EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory, Pickup pickup);
    }
}
