package Wylaga.Overstates.Game.Entities.Projectiles;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;
import Wylaga.Overstates.Game.Entities.Team;
import Wylaga.Util.Trajectory;

import java.awt.*;

public class PlayerProjectile extends Projectile
{
    public static final Dimension defaultDimension = new Dimension(4, 15);
    private static int defaultSpeed = 15;

    public PlayerProjectile(PlayerShip creator)
    {
        super(new Point(creator.getOrigin()), defaultDimension, Team.PLAYER, defaultSpeed, Trajectory.getDirection(0, -1), 10);
        super.translatePosition((creator.getDimension().width / 2) - (defaultDimension.width / 2), -defaultDimension.height);
    }

    public static Dimension getDefaultDimension() {return defaultDimension;}

    public EntityDisplayable getDisplayable(EntityDisplayableFactory edf)
    {
        return edf.makePlayerProjectileDisplayable(this);
    }
}
