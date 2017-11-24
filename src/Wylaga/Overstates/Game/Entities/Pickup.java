package Wylaga.Overstates.Game.Entities;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Collisions.Cell;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;
import Wylaga.Util.AbstractFunction;
import Wylaga.Util.Trajectory;

import java.awt.*;

public class Pickup extends Entity
{
    private AbstractFunction effect;
    private boolean expired;

    public Pickup(Point position, AbstractFunction effect)
    {
        super(new Point(position.x, position.y), new Dimension(15, 15), Team.NEUTRAL, 1);
        super.setTrajectory(Trajectory.getDirection(0, 1));
        this.effect = effect;
        this.expired = false;
    }

    public void update()
    {
        propelSelf();
    }

    public void trigger()
    {
        effect.execute();
    }

    public void deactivate()
    {
        expired = true;
    }

    public boolean expired()
    {
        return expired;
    }

    public void addToCell(Cell cell)
    {
        cell.addPickup(this);
    }

    @Override
    public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
    {
        return entityDisplayableFactory.makeHealthPickupDisplayable(this);
    }
}
