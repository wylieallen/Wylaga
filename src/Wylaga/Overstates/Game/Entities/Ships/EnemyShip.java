package Wylaga.Overstates.Game.Entities.Ships;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Entities.Ships.ShipComponents.ShipWeapon;
import Wylaga.Util.Trajectory;

import java.awt.*;
import java.awt.geom.Point2D;

public class EnemyShip extends Ship
{
    public static final Dimension defaultDimension = new Dimension(25, 25);

    public EnemyShip(Point2D.Double position)
    {
        super(position, defaultDimension, Team.ENEMY, 1, 30, Trajectory.getDirection(0 ,1), 10, ShipWeapon.getEnemyWeapon());
    }

    public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
    {
        return entityDisplayableFactory.makeEnemyDisplayable(this);
    }
}
