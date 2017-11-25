package Wylaga.Overstates.Game.Entities.Ships;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Projectiles.WingmanProjectile;
import Wylaga.Util.Trajectory;

import java.awt.*;
import java.awt.geom.Point2D;

public class Wingman extends PlayerShip
{
    public static final Dimension defaultDimension = new Dimension(25, 25);

    private PlayerShip leader;
    private Point offset;

    public Wingman(PlayerShip leader, Point offset)
    {
        super(new Point2D.Double(leader.getOrigin().x + offset.x, leader.getOrigin().y + offset.y), defaultDimension,
                30, 0);
        this.offset = offset;
        this.leader = leader;
    }

    public void update()
    {
        super.update();
        super.setTrajectory(leader.getTrajectory());
        super.setFiring(leader.isFiring());
        super.setCurFuel(leader.getCurFuel());
        super.setSpecial(leader.specialDeployed());

        if(leader.isAlive() && leader.getTrajectory() == Trajectory.getDirection(0, 0))
        {
            super.getOrigin().setLocation(leader.getOrigin().x + offset.x, leader.getOrigin().y + offset.y);
        }
    }

    public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
    {
        return entityDisplayableFactory.makeWingmanDisplayable(this);
    }

    public void terminate() { super.takeDamage(super.getHealth()); }

    protected Projectile getNewProjectile()
    {
        return new WingmanProjectile(this);
    }
}
