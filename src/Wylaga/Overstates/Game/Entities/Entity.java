package Wylaga.Overstates.Game.Entities;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Game.Collisions.Cell;
import Wylaga.Util.Trajectory;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.geom.Point2D;

// Entity represents a game object that is subject to collision.

public abstract class Entity
{
    private Point2D.Double position;
    private Dimension dimension;
    private Team team;
    private Trajectory trajectory;
    private double speed;

    // =================================================================================================================
    // Constructor:

    protected Entity(Point2D.Double position, Dimension dimension, Team team, double speed)
    {
        this.position = position;
        this.dimension = dimension;
        this.team = team;
        this.speed = speed;
        this.trajectory = Trajectory.getDirection(0, 0);
    }

    // =================================================================================================================
    // Mutators:

    protected void translatePosition(double dx, double dy)
    {
        position.setLocation(position.x + dx, position.y + dy);
    }

    private void translatePosition(Trajectory trajectory)
    {
       translatePosition((trajectory.getDx() * speed), (trajectory.getDy() * speed));
    }

    protected void propelSelf()
    {
        translatePosition(trajectory);
    }

    public void setTrajectory(Trajectory trajectory) {this.trajectory = trajectory;}
    public void setSpeed(double speed) {this.speed = speed;}

    // =================================================================================================================
    // Accessors:

    public double getSpeed() {return speed;}
    public Trajectory getTrajectory() {return trajectory;}
    public Dimension getDimension() {return dimension;}
    public Team getTeam() {return team;}
    public Point2D.Double getOrigin() {return position;}
    public Point2D.Double getTerminus() {return new Point2D.Double(position.x + dimension.width, position.y + dimension.height);}

    // =================================================================================================================
    // Abstract methods:

    // update() prompts the Entity to modify its internal state.
    public abstract void update();

    // expired() returns true when the Entity has outlived its usefulness.
    public abstract boolean expired();

    // getDisplayable() uses double dispatch to request an appropriate SimpleEntityDisplayable.
    // Concrete subclasses of Entity must define the EDF method that is called.
    public abstract EntityDisplayable getDisplayable(EntityDisplayableFactory entityDisplayableFactory);

    // addToCell() similarly allows concrete Entities to define which of a Cell's Lists they'll be added to.
    public abstract void addToCell(Cell cell);
}

