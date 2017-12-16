package Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components;

import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Overstates.Game.Entities.Ships.Ship;
import Wylaga.Overstates.Game.Entities.Ships.ShipComponents.ShipWeapon;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class WeaponDisplayable extends SimpleDisplayable
{
    private ShipWeapon weapon;
    private BufferedImage baseImage;
    private BufferedImage firingImage;
    private WeaponDisplayable.State currentState;

    public WeaponDisplayable(ShipWeapon weapon, Point2D.Double offsetPoint, BufferedImage baseImage, BufferedImage firingImage)
    {
        super(offsetPoint, baseImage);
        this.weapon = weapon;
        this.baseImage = baseImage;
        this.firingImage = firingImage;
        this.currentState = new BaseState();
    }

    @Override
    public void update()
    {
        currentState.update();
    }

    public ShipWeapon getWeapon() { return weapon; }

    private interface State {void update();}

    private class BaseState implements State
    {
        private BaseState()
        {
            setImage(baseImage);
        }

        public void update()
        {
            if(weapon.isFiring())
            {
                currentState = new FiringState();
            }
        }
    }

    private class FiringState implements State
    {
        private int counter;

        private FiringState()
        {
            setImage(firingImage);
            counter = 2;
        }

        public void update()
        {
            if(weapon.isFiring())
            {
                currentState = new FiringState();
            }
            else if (--counter <= 0)
            {
                currentState = new BaseState();
            }
        }
    }
}
