package Wylaga.Overstates.Game.Entities.Ships;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Projectiles.WingmanProjectile;
import Wylaga.Util.Trajectory;

import java.awt.*;
import java.awt.geom.Point2D;

import java.util.LinkedList;
import java.util.Queue;

public class Wingman extends PlayerShip
{
    public static final Dimension defaultDimension = new Dimension(25, 25);

    private PlayerShip leader;
    private Point offset;
    private boolean shootNext;

    private double defaultSpeed;
    private double curSpeed;

    private Queue<Point2D.Double> destinations;
    private Queue<Boolean> specialActivations;
    private Queue<Boolean> weaponFirings;

    public Wingman(PlayerShip leader, Point offset)
    {
        super(new Point2D.Double(leader.getOrigin().x + offset.x, leader.getOrigin().y + offset.y), defaultDimension,
                30, 0);

        this.offset = offset;
        this.leader = leader;
        this.shootNext = false;
        this.defaultSpeed = getSpeed();
        destinations = new LinkedList<>();
        specialActivations = new LinkedList<>();
        weaponFirings = new LinkedList<>();

        int tightness = 3;

        for(int i = 0; i < tightness; i++)
        {
            destinations.add(super.getOrigin());
            specialActivations.add(false);
            weaponFirings.add(false);
        }
    }

    public void update()
    {
        super.setSpeed(defaultSpeed);

        if(leader.isAlive())
        {
            Point2D.Double nextDest = calculateDestination();
            Point2D.Double curPos = super.getOrigin();

            double distToDest = Point2D.distance(nextDest.x, nextDest.y, curPos.x, curPos.y);

            if (distToDest < super.getSpeed())
            {
                if(distToDest <= 3)
                {
                    curPos.setLocation(nextDest.x, nextDest.y);
                }
                else
                {
                    super.setSpeed(distToDest);
                }
            }

            super.setFiring(weaponFirings.remove());
            super.setSpecial(specialActivations.remove());
            super.setTrajectory(new Trajectory(super.getOrigin(), destinations.remove()));
            super.setCurFuel(leader.getCurFuel());

            weaponFirings.add(leader.isFiring());
            specialActivations.add(leader.specialDeployed());
            destinations.add(calculateDestination());
        }
        else
        {
            super.setFiring(leader.isFiring());
            super.setSpecial(leader.specialDeployed());
            super.setTrajectory(leader.getTrajectory());
        }
        super.update();
    }

    private Point2D.Double calculateDestination()
    {
        return new Point2D.Double(leader.getOrigin().x + offset.x, leader.getOrigin().y + offset.y);
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
