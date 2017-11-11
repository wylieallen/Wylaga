package Wylaga.Overstates.Displayables.EntityDisplayables;

import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.awt.image.BufferedImage;

public class ShipDisplayable extends EntityDisplayable
{
    private Ship ship;
    private BufferedImage baseImage;
    private BufferedImage hurtImage;
    private ShipState state;

    public ShipDisplayable(Ship ship, BufferedImage baseImage, BufferedImage hurtImage)
    {
        super(ship, baseImage);
        this.ship = ship;
        this.baseImage = baseImage;
        this.hurtImage = hurtImage;
        this.state = new BaseState();
    }
    public void update() { state.update(); }

    private abstract class ShipState
    {
        protected final int prevHealth;

        private ShipState() { prevHealth = ship.getHealth(); }

        public void update()
        {
            if(updateCondition())
            {
                setImage(nextImage());
                state = nextState();
            }
        }

        protected abstract boolean updateCondition();
        protected abstract ShipState nextState();
        protected abstract BufferedImage nextImage();
    }

    private class BaseState extends ShipState
    {
        protected boolean updateCondition() { return prevHealth > ship.getHealth(); }
        protected ShipState nextState() { return new HurtState(); }
        protected BufferedImage nextImage() { return hurtImage; }
    }

    private class HurtState extends ShipState
    {
        private int hurtAnimationDuration = 2;

        protected boolean updateCondition()
        {
            return (--hurtAnimationDuration <= 0); //&& (prevHealth <= ship.getHealth());
        }
        protected ShipState nextState() { return new BaseState(); }
        protected BufferedImage nextImage() { return baseImage; }
    }
}
