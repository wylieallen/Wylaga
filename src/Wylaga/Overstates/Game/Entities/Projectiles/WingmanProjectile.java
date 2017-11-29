package Wylaga.Overstates.Game.Entities.Projectiles;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Entities.Ships.Wingman;
import Wylaga.Overstates.Game.Entities.Team;
import Wylaga.Util.Trajectory;

import java.awt.*;
import java.awt.geom.Point2D;

public class WingmanProjectile extends Projectile
{
    public static final Dimension defaultDimension = new Dimension(1, 10);
    private static int defaultSpeed = 12;

    public WingmanProjectile(Wingman creator)
    {
        super(new Point2D.Double(creator.getOrigin().x, creator.getOrigin().y), defaultDimension, Team.PLAYER, defaultSpeed,
                Trajectory.getDirection(0, -1), 5, EntityDisplayableFactory::makeWingmanProjectileDisplayable);
        super.translatePosition((creator.getDimension().width / 2) - (defaultDimension.width / 2), -defaultDimension.height + 10);
    }

    public WingmanProjectile(Point2D.Double origin, Team team, int speed, Trajectory trajectory, int damage)
    {
        super(origin, defaultDimension, team, speed, trajectory, damage, EntityDisplayableFactory::makeWingmanProjectileDisplayable);
    }
}
