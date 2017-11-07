package Wylaga.Overstates.Game.Entities.Ships;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Entities.Team;
import Wylaga.Util.Trajectory;

import java.awt.*;

public class PlayerShip extends Ship
{
    public static final Dimension defaultDimension = new Dimension(25, 25);

    public PlayerShip()
    {
        super(new Point(500, 700), defaultDimension, Team.PLAYER, 5, 100,
                Trajectory.getDirection(0, -1), -100);
    }

    public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
    {
        return entityDisplayableFactory.makePlayerDisplayable(this);
    }
}
