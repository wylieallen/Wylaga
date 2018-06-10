package Wylaga.Overstates.Displayables.EntityDisplayables;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.SubimageDisplayable;
import Wylaga.Overstates.Game.Entities.Entity;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class SubimageEntityDisplayable extends SubimageDisplayable implements EntityDisplayable
{
    private Entity entity;
    private Displayable successor;

    public SubimageEntityDisplayable(Entity entity, BufferedImage image, Point originOnImage, int stepsPerFrame, Displayable successor)
    {
        super(image, entity.getOrigin(), entity.getDimension(), originOnImage, stepsPerFrame);
        this.entity = entity;
        this.successor = successor;
    }

    @Override
    public Displayable getSuccessorDisplayable()
    {
        successor.getPosition().setLocation(getPosition().x - (successor.getSize().width / 2), getPosition().y - (successor.getSize().height / 2));
        return successor;
    }

    @Override
    public boolean expired() {return entity.expired();}

    public Entity getEntity() {return entity;}
}
