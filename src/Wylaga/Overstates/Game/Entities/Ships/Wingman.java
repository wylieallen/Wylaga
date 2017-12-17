package Wylaga.Overstates.Game.Entities.Ships;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Entities.Ships.ShipComponents.ShipWeapon;
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

    private double defaultSpeed;

    private Queue<Point2D.Double> destinations;
    private Queue<Boolean> specialActivations;
    private Queue<Boolean> weaponFirings;

    public Wingman(PlayerShip leader, Point offset)
    {
        super(new Point2D.Double(leader.getOrigin().x + offset.x, leader.getOrigin().y + offset.y), defaultDimension,
                30, 0);

        this.offset = offset;
        this.leader = leader;
        this.defaultSpeed = getSpeed();
        destinations = new LinkedList<>();
        specialActivations = new LinkedList<>();
        weaponFirings = new LinkedList<>();

        setWeapon(ShipWeapon.getWingmanWeapon());

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

            super.setFiring(weaponFirings.remove());
            super.setCurFuel(leader.getCurFuel());
            super.setSpecial(specialActivations.remove());
            super.setTrajectory(new Trajectory(super.getOrigin(), destinations.remove()));

            if (distToDest < super.getSpeed())
            {
                if(distToDest <= 3)
                {
                    curPos.setLocation(nextDest.x, nextDest.y);
                    super.setSpeed(0);
                }
                else
                {
                    super.setSpeed(distToDest);
                }
            }

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
}
