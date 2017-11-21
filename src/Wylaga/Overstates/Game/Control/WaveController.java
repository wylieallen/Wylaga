package Wylaga.Overstates.Game.Control;

import Wylaga.Overstates.Game.Entities.Ships.Ship;
import Wylaga.Overstates.Game.Wave;
import Wylaga.Util.Random;
import Wylaga.Util.Trajectory;

import java.awt.*;

public class WaveController implements GameController
{
    private Wave wave;

    private int yInitial;
    private int heightTracker;

    private Trajectory trajectory;

    public WaveController(Wave wave)
    {
        this.wave = wave;
        this.trajectory = Trajectory.getDirection(1, 0);
    }

    public void update()
    {
        updateTrajectory();
        for(Ship ship : wave.getShips())
        {
            ship.setFiring(Random.rollInt(60 + 2*wave.getShips().size()) == 0);
            ship.setTrajectory(trajectory);
        }
    }

    public void updateTrajectory()
    {
        Point leftPoint = wave.getLeftShip().getOrigin();
        Point rightPoint = wave.getRightShip().getOrigin();
        Point topPoint = wave.getTopShip().getOrigin();
        Point bottomPoint = wave.getBottomShip().getOrigin();

        int dx, dy;

        if(trajectory.getDx() == 1)
        {
            if(rightPoint.x >= (1280 - 64))
            {
                dx = 0;
                dy = 1;
                heightTracker = yInitial = rightPoint.y;
            }
            else
            {
                return;
            }
        }
        else if(trajectory.getDx() == -1)
        {
            if(leftPoint.x <= 64)
            {
                dx = 0;
                dy = 1;
                heightTracker = yInitial = rightPoint.y;
            }
            else
            {
                return;
            }
        }
        else
        {
            if(heightTracker >= yInitial + 32)
            {
                if(leftPoint.x <= 256)
                {
                    dx = 1;
                    dy = 0;
                }
                else
                {
                    dx = -1;
                    dy = 0;
                }
            }
            else
            {
                heightTracker += wave.getSpeed();
                return;
            }
        }
        trajectory = Trajectory.getDirection(dx, dy);
    }
}
