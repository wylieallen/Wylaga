package Wylaga.Overstates.Game.Entities.Projectiles;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Collisions.Grid;
import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Util.Trajectory;

import java.awt.*;
import java.awt.geom.Point2D;

public class LinearProjectile extends Projectile {
    public static final Dimension defaultDimension = new Dimension(7, 7);
    public static final int defaultDamage = 10;

    public LinearProjectile(Point2D.Double position, Dimension dimension, Team team, double speed, Trajectory trajectory, int damage, EDVF edvf)
    {
        super(position, dimension, team, speed, damage, edvf);
        super.setTrajectory(trajectory);
    }

    public LinearProjectile(Point2D.Double position, Team team, double speed, Trajectory trajectory, EDVF edvf)
    {
        this(position, defaultDimension, team, speed, trajectory, defaultDamage, edvf);
    }

}
