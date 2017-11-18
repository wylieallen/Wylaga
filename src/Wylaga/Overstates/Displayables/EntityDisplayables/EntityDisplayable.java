package Wylaga.Overstates.Displayables.EntityDisplayables;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Game.Entities.Entity;

import java.awt.image.BufferedImage;

public class EntityDisplayable extends Displayable
{
    private Entity entity;

    public EntityDisplayable(Entity entity, BufferedImage image)
    {
        super(entity.getOrigin(), image);
        this.entity = entity;
    }

    public boolean expired()
    {
        return entity.expired();
    }

    // By default, EntityDisplayables don't do anything when updated.
    // update() should be overridden by subclasses that want to take advantage of it.
    public void update() {}
}
