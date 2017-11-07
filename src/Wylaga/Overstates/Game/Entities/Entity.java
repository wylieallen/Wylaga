package Wylaga.Overstates.Game.Entities;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Util.Trajectory;

import java.awt.Point;
import java.awt.Dimension;

public abstract class Entity
{
    private Point position;
    private Dimension dimension;
    private Team team;
    private Trajectory trajectory;
    private double speed;

    public Entity(Point position, Dimension dimension, Team team, double speed)
    {
        this.position = position;
        this.dimension = dimension;
        this.team = team;
        this.speed = speed;
        this.trajectory = Trajectory.getDirection(0, 0);
    }

    protected void translatePosition(int dx, int dy)
    {
        position.translate(dx, dy);
    }

    protected void translatePosition(Trajectory trajectory)
    {
       translatePosition((int) (trajectory.getDx() * speed), (int) (trajectory.getDy() * speed));
    }

    protected void propelSelf()
    {
        translatePosition(trajectory);
    }

    public void setTrajectory(Trajectory trajectory) {this.trajectory = trajectory;}
    public void setSpeed(double speed) {this.speed = speed;}

    public Point getPosition() {return position;}
    public Dimension getDimension() {return dimension;}
    public Team getTeam() {return team;}

    public abstract void update();
    public abstract boolean expired();
    public abstract EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory);
}

