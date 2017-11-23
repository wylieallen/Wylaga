package Wylaga.Overstates.Displayables.EntityDisplayables;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Overstates.Game.Entities.Entity;

import java.awt.image.BufferedImage;

public class SimpleEntityDisplayable extends SimpleDisplayable implements EntityDisplayable
{
    private Entity entity;
    private Displayable successor;

    public SimpleEntityDisplayable(Entity entity, BufferedImage image, Displayable successor)
    {
        super(entity.getOrigin(), image);
        this.entity = entity;
        this.successor = successor;
    }

    @Override
    public boolean expired()
    {
        return entity.expired();
    }

    @Override
    public Displayable getSuccessorDisplayable()
    {
        return successor;
    }
}
