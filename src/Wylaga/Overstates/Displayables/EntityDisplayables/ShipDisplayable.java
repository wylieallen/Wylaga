package Wylaga.Overstates.Displayables.EntityDisplayables;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.awt.image.BufferedImage;

public class ShipDisplayable extends EntityDisplayable
{
    private Ship ship;
    private BufferedImage baseImage;
    private BufferedImage hurtImage;
    private BufferedImage firingImage;
    private ShipState state;

    public ShipDisplayable(Ship ship, BufferedImage baseImage, BufferedImage hurtImage, BufferedImage firingImage, Displayable successor)
    {
        super(ship, baseImage, successor);
        this.ship = ship;
        this.baseImage = baseImage;
        this.hurtImage = hurtImage;
        this.firingImage = firingImage;
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
        private boolean firing = false;
        private boolean hurt = false;

        protected boolean updateCondition()
        {
            if(prevHealth > ship.getHealth())
            {
                hurt = true;
                return true;
            }
            else if (ship.isFiring())
            {
                firing = true;
                return true;
            }
            return false;
        }

        protected ShipState nextState()
        {
            if(hurt) return new HurtState();
            if(firing) return new FiringState();
            return new BaseState();
        }

        protected BufferedImage nextImage()
        {
            if(hurt) return hurtImage;
            if(firing) return firingImage;
            return baseImage;
        }
    }

    private class HurtState extends ShipState
    {
        private int hurtAnimationDuration = 2;

        protected boolean updateCondition() { return --hurtAnimationDuration <= 0; }
        protected ShipState nextState()
        {
            if(ship.isFiring()) return new FiringState();
            return new BaseState();
        }
        protected BufferedImage nextImage()
        {
            if(ship.isFiring()) return firingImage;
            return baseImage;
        }
    }

    private class FiringState extends ShipState
    {
        private FiringState() { prevHealth = ship.getHealth(); }

        protected final int prevHealth;
        private int firingAnimationDuration = 2;
        private boolean hurt = false;

        protected boolean updateCondition()
        {
            if (--firingAnimationDuration <= 0)
            {
                return true;
            }
            else if (prevHealth > ship.getHealth())
            {
                hurt = true;
                return true;
            }
            return false;
        }

        protected ShipState nextState() { return hurt ? new HurtState() : new BaseState(); }
        protected BufferedImage nextImage() { return hurt ? hurtImage : baseImage; }
    }
}
