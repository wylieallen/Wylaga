package Wylaga.Overstates.Displayables.EntityDisplayables;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Game.Entities.Entity;

import java.awt.image.BufferedImage;

public class EntityDisplayable extends Displayable
{
    private Entity entity;

    public EntityDisplayable(Entity entity, BufferedImage image)
    {
        super(entity.getPosition(), image);
        this.entity = entity;
    }

    public void update() {}

    public boolean expired()
    {
        return entity.expired();
    }
}
