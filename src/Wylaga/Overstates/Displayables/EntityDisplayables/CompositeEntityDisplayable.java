package Wylaga.Overstates.Displayables.EntityDisplayables;

import Wylaga.Overstates.Displayables.CompositeDisplayable;
import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Game.Entities.Entity;

import java.awt.*;
import java.util.Set;

public class CompositeEntityDisplayable extends CompositeDisplayable implements EntityDisplayable
{
    private Entity entity;
    private Displayable successor;

    protected CompositeEntityDisplayable(Entity entity, Displayable successor, Set<Displayable> components)
    {
        super(entity.getOrigin(), components);
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
