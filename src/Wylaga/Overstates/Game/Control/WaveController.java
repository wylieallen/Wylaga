package Wylaga.Overstates.Game.Control;

import Wylaga.Overstates.Game.Entities.Ships.Ship;
import Wylaga.Overstates.Game.Wave;
import Wylaga.Util.Random.Random;
import Wylaga.Util.Trajectory;
import Wylaga.WylagaApp;

import java.awt.geom.Point2D;

public class WaveController implements GameController
{
    private Wave wave;

    private double yInitial;
    private double heightTracker;

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
            ship.setFiring(Random.rollInt(60 + 2*wave.getShips().size() - wave.getWaveNumber()) <= 0);
            ship.setTrajectory(trajectory);
        }
    }

    private void updateTrajectory()
    {
        Point2D.Double leftPoint = wave.getLeftShip().getOrigin();
        Point2D.Double rightPoint = wave.getRightShip().getOrigin();
        Point2D.Double topPoint = wave.getTopShip().getOrigin();
        Point2D.Double bottomPoint = wave.getBottomShip().getOrigin();

        if(topPoint.y < 32)
        {
            trajectory = Trajectory.getDirection(0, 1);
            return;
        }

        int dx, dy;

        if(trajectory.getDx() == 1)
        {
            if(rightPoint.x >= (WylagaApp.WIDTH - 64))
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
            if(leftPoint.x <= 32)
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
