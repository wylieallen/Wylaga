package Wylaga.Overstates.Game.Entities.Ships;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Entities.Team;
import Wylaga.Util.Trajectory;

import java.awt.*;

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
        super(new Point(500, 700), defaultDimension, Team.PLAYER, defaultSpeed, defaultHealth,
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

    public void setSpecial(boolean special) { this.special = special; }

    public int getMaxFuel() { return maxFuel; }
    public int getCurFuel() { return curFuel; }

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
