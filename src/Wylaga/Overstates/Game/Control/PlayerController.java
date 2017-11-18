package Wylaga.Overstates.Game.Control;

import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;
import Wylaga.Util.KeyRole;
import Wylaga.Util.Trajectory;

public class PlayerController implements GameController
{
    private PlayerShip ship;

    private boolean actionDepressed;
    private boolean specialDepressed;
    private boolean leftDepressed;
    private boolean rightDepressed;
    private boolean upDepressed;
    private boolean downDepressed;

    private boolean shotFired;

    public PlayerController(PlayerShip ship)
    {
        this.ship = ship;
        reset();
    }

    public void parseKey(KeyRole keyRole, boolean depressed)
    {
        switch(keyRole)
        {
            case UP:
                upDepressed = depressed;
                break;

            case DOWN:
                downDepressed = depressed;
                break;

            case LEFT:
                leftDepressed = depressed;
                break;

            case RIGHT:
                rightDepressed = depressed;
                break;

            case SELECT:
            case ACTION:
                actionDepressed = depressed;
                if(!depressed)
                {
                    shotFired = false;
                }
                break;

            case SPECIAL:
                specialDepressed = depressed;
                break;
        }
    }

    public void reset()
    {
        actionDepressed = specialDepressed = leftDepressed = rightDepressed = upDepressed = downDepressed = shotFired = false;
    }

    public void update()
    {
        ship.setTrajectory(getTrajectory());
        ship.setFiring(isFiring());
        ship.setSpecial(specialDepressed);
    }

    private boolean isFiring()
    {
        if(actionDepressed && !shotFired)
        {
            return (shotFired = true);
        }
        else
        {
            return false;
        }
    }


    private Trajectory getTrajectory()
    {
        return Trajectory.getDirection(getSign(rightDepressed, leftDepressed), getSign(downDepressed, upDepressed));
    }

    private int getSign(boolean positive, boolean negative)
    {
        if(positive == negative) // positive XNOR negative
        {
            return 0;
        }
        else if (positive)
        {
            return 1;
        }
        else // if (negative)
        {
            return -1;
        }
    }
}
