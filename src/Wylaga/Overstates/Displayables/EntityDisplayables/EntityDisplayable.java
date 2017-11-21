package Wylaga.Overstates.Displayables.EntityDisplayables;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Game.Entities.Entity;

import java.awt.image.BufferedImage;

public class EntityDisplayable extends Displayable
{
    private Entity entity;
    private Displayable successor;

    public EntityDisplayable(Entity entity, BufferedImage image, Displayable successor)
    {
        super(entity.getOrigin(), image);
        this.entity = entity;
        this.successor = successor;
    }

    public boolean expired()
    {
        return entity.expired();
    }

    public Displayable getSuccessorDisplayable()
    {
        return successor;
    }
}
