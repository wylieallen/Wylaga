package Wylaga.Overstates.Game.Entities.Projectiles;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;
import Wylaga.Util.Trajectory;

import java.awt.*;
import java.awt.geom.Point2D;

public class PlayerProjectile extends Projectile
{
    public static final Dimension defaultDimension = new Dimension(4, 15);
    private static int defaultSpeed = 15;
    private static int defaultDamage = 10;

    /*
    public PlayerProjectile(PlayerShip creator)
    {
        super(new Point2D.Double(creator.getOrigin().x, creator.getOrigin().y), defaultDimension, Team.PLAYER, defaultSpeed,
                Trajectory.getDirection(0, -1), 10, EntityDisplayableFactory::makePlayerProjectileDisplayable);
        super.translatePosition((creator.getDimension().width / 2) - (defaultDimension.width / 2), -defaultDimension.height + 10);
    }
    */

    public PlayerProjectile(Point2D.Double origin, Team team, int speed, Trajectory trajectory, int damage)
    {
        super(origin, defaultDimension, team, speed, trajectory, damage, EntityDisplayableFactory::makePlayerProjectileDisplayable);
    }

    public PlayerProjectile(Point2D.Double origin, Team team, int damage)
    {
        super(origin, defaultDimension, team, defaultSpeed, Trajectory.getDirection(0, -1), damage, EntityDisplayableFactory::makePlayerProjectileDisplayable);
    }

    public PlayerProjectile(Point2D.Double origin, Team team)
    {
        super(origin, defaultDimension, team, defaultSpeed, Trajectory.getDirection(0, -1), defaultDamage, EntityDisplayableFactory::makePlayerProjectileDisplayable);
    }
}
