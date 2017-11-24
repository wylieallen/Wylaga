package Wylaga.Overstates.Game.Entities.Ships;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Entities.Projectiles.PlayerProjectile;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Team;
import Wylaga.Util.Trajectory;

import java.awt.*;
import java.awt.geom.Point2D;

public class PlayerShip extends Ship
{
    public static final Dimension defaultDimension = new Dimension(50, 39);
    private static final int defaultSpeed = 5, defaultHealth = 100, defaultScorePenalty = -100;

    private SpecialModule specialModule = new TurboModule();

    private boolean special;

    private int maxFuel;
    private int curFuel;

    public PlayerShip()
    {
        super(new Point2D.Double(615, 600), defaultDimension, Team.PLAYER, defaultSpeed, defaultHealth,
                Trajectory.getDirection(0, -1), defaultScorePenalty);

        special = false;
        curFuel = maxFuel = 200;
    }

    public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
    {
        return entityDisplayableFactory.makePlayerDisplayable(this);
    }

    public void update()
    {
        specialModule.update();
        super.update();
    }

    protected Projectile getNewProjectile()
    {
        return new PlayerProjectile(this);
    }

    public void setSpecial(boolean special) { this.special = special; }
    public boolean specialDeployed() { return special; }

    public int getMaxFuel() { return maxFuel; }
    public int getCurFuel() { return curFuel; }

    public void addFuel(int fuel)
    {
        curFuel += fuel;
        if(maxFuel < curFuel)
            curFuel = maxFuel;
    }

    private interface SpecialModule
    {
        void update();
    }

    private class TurboModule implements SpecialModule
    {
        public void update()
        {
            if(special)
            {
                if(curFuel > 0)
                {
                    if(getTrajectory().equals(Trajectory.getDirection(0, 0)))
                    {
                        curFuel--;
                        // super.setSpeed(5);
                        // updating speed in this frame is pointless since the ship is stationary
                    }
                    else
                    {
                        curFuel -= 2;
                        setSpeed(10);
                    }
                }
                else
                {
                    setSpeed(5);
                }
            }
            else
            {
                setSpeed(5);
                if(curFuel < maxFuel)
                {
                    curFuel++;
                }
            }
        }
    }
}
