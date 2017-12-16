package Wylaga.Overstates.Game.Entities.Ships;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.ShipComponents.ShipWeapon;
import Wylaga.Util.Trajectory;
import Wylaga.WylagaApp;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Set;

public class PlayerShip extends Ship
{
    public static final Dimension defaultDimension = new Dimension(50, 39);
    private static final int defaultSpeed = 5, defaultHealth = 100, defaultScorePenalty = -100;

    private SpecialModule specialModule = new TurboModule();

    private boolean special;
    private boolean underConstraint;

    private int maxFuel;
    private int curFuel;

    public PlayerShip(Point2D.Double origin, Dimension dimension, int health, int scorePenalty)
    {
        super(origin, dimension, Team.PLAYER, defaultSpeed, health, Trajectory.getDirection(0, -1), scorePenalty, ShipWeapon.getPlayerWeapon());
        special = false;
        underConstraint = false;
        curFuel = maxFuel = 200;
    }

    public PlayerShip()
    {
        super(new Point2D.Double(WylagaApp.WIDTH / 2 - defaultDimension.width / 2, 600), defaultDimension, Team.PLAYER, defaultSpeed, defaultHealth,
                Trajectory.getDirection(0, -1), defaultScorePenalty, ShipWeapon.getPlayerWeapon());

        special = false;
        curFuel = maxFuel = 200;
    }

    public void setWeapon(ShipWeapon weapon)
    {
        super.setWeapon(weapon);
    }

    public EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory)
    {
        return entityDisplayableFactory.makePlayerDisplayable(this);
    }

    public void update()
    {
        specialModule.update();
        super.update();
    }


    /*
    protected LinearProjectile getNewProjectile()
    {
        Point2D.Double projPt = new Point2D.Double(getOrigin().x, getOrigin().y);
        LinearProjectile projectile = new PlayerProjectile(projPt, getTeam());
        int yInitial = (getTeam() == Team.ENEMY) ? getDimension().height : 0;
        projectile.translatePosition(getDimension().width / 2 - projectile.getDimension().width / 2, yInitial);
        return projectile;
    }
    */

    public void setSpecial(boolean special) { this.special = special; }
    public boolean specialDeployed() { return special; }

    public int getMaxFuel() { return maxFuel; }
    public int getCurFuel() { return curFuel; }

    public void flagAsUnconstrained() {underConstraint = false;}
    public void flagAsConstrained() {underConstraint = true;}
    public boolean isUnderConstraint() {return underConstraint;}

    public void refuel(int fuel)
    {
        curFuel += fuel;
        if(maxFuel < curFuel)
            curFuel = maxFuel;
    }

    public void setCurFuel(int fuel)
    {
        curFuel = fuel;
    }

    private interface SpecialModule
    {
        void update();
    }

    private class TurboModule implements SpecialModule
    {
        public void update()
        {
            if(special)
            {
                if(curFuel > 0)
                {
                    if(getTrajectory().equals(Trajectory.getDirection(0, 0)))
                    {
                        curFuel--;
                        // super.setSpeed(5);
                        // updating speed in this frame is pointless since the ship is stationary
                    }
                    else
                    {
                        curFuel -= 2;
                        setSpeed(10);
                    }
                }
                else
                {
                    setSpeed(5);
                }
            }
            else
            {
                setSpeed(5);
                if(curFuel < maxFuel)
                {
                    curFuel++;
                }
            }
        }
    }
}
