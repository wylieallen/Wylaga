package Wylaga.Util;

import java.awt.geom.Point2D;

public class Trajectory
{
    // Static cardinal directions:

    private static Trajectory[][] DIRECTIONS = makeDirections();

    private static Trajectory[][] makeDirections() {
        Trajectory[][] directions = new Trajectory[3][3];

        for(int i = -1; i <= 1; i++)
            for(int j = -1; j <= 1; j++)
                directions[i + 1][j + 1] = new Trajectory(i, j);

        return directions;
    }

    public static Trajectory getDirection(int x, int y)
    {
        return DIRECTIONS[x + 1][y + 1];
    }

    // ============================================

    private double dx;
    private double dy;

    public Trajectory(double dx, double dy)
    {
        setAsUnitVector(dx, dy);
    }

    public Trajectory(Point2D.Double origin, Point2D.Double destination)
    {
        this(destination.x - origin.x, destination.y - origin.y);
    }

    private void setAsUnitVector(double dx, double dy)
    {
        this.dx = dx;
        this.dy = dy;
        double mag = calculateMagnitude();
        if(mag != 0)
        {
            this.scale(1.0 / mag);
        }
    }

    public double getDx() {return dx;}
    public double getDy() {return dy;}

    private double calculateMagnitude()
    {
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    private void scale(double ddx, double ddy)
    {
        dx *= ddx;
        dy *= ddy;
    }

    private void scale(double d) { scale(d, d); }
}
