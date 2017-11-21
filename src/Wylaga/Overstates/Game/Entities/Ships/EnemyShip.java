package Wylaga.Overstates.Game.Entities.Ships;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Team;
import Wylaga.Util.Trajectory;

import java.awt.*;

public class EnemyShip extends Ship
{
    public static final Dimension defaultDimension = new Dimension(25, 25);

    public EnemyShip(Point position)
    {
        super(position, defaultDimension, Team.ENEMY, 1, 30, Trajectory.getDirection(0 ,1), 10);
    }

    public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
    {
        return entityDisplayableFactory.makeEnemyDisplayable(this);
    }
}
