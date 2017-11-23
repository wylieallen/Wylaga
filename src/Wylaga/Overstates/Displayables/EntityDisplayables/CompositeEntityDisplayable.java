package Wylaga.Overstates.Displayables.EntityDisplayables;

import Wylaga.Overstates.Displayables.CompositeDisplayable;
import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Game.Entities.Entity;

import java.awt.*;

public class CompositeEntityDisplayable extends CompositeDisplayable implements EntityDisplayable
{
    private Entity entity;
    private Displayable successor;

    public CompositeEntityDisplayable(Point position, Entity entity, Displayable successor, Displayable... displayables)
    {
        super(position, displayables);
        this.entity = entity;
        this.successor = successor;
    }

    @Override
    public Displayable getSuccessorDisplayable() {
        return successor;
    }

    public Entity getEntity() {
        return entity;
    }

    public boolean expired()
    {
        return entity.expired();
    }
}
