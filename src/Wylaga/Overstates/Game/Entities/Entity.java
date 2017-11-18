package Wylaga.Overstates.Game.Entities;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Util.Trajectory;

import java.awt.Point;
import java.awt.Dimension;

// Entity represents a game object that is subject to collision.

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

    // =================================================================================================================
    // Mutators:

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

    // =================================================================================================================
    // Accessors:

    public Trajectory getTrajectory() {return trajectory;}
    public Dimension getDimension() {return dimension;}
    public Team getTeam() {return team;}
    public Point getOrigin() {return position;}
    public Point getTerminus() {return new Point(position.x + dimension.width, position.y + dimension.height);}

    // =================================================================================================================
    // Abstract methods:

    // update() prompts the Entity to modify its internal state.
    public abstract void update();

    // expired() returns true when the Entity has outlived its usefulness.
    public abstract boolean expired();

    // getDisplayable() uses double dispatch to request an appropriate EntityDisplayable.
    // Concrete subclasses of Entity must define the EDF method that is called.
    public abstract EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory);
}

