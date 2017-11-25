package Wylaga.Overstates.Game.Collisions;

import Wylaga.Overstates.Game.Entities.Entity;

import java.awt.*;
import java.awt.geom.Point2D;

public class CollisionChecker
{
    static private Point2D.Double origin = new Point2D.Double(0, 0);
    static private Point2D.Double farTopLeft = new Point2D.Double(-75, -75);

    static private boolean rectanglesBounded(Point2D.Double outerMin, Point2D.Double outerMax, Point2D.Double innerMin, Point2D.Double innerMax)
    {
        // returns true if inner rectangle is completely inside
        if(innerMin.x < outerMin.x || innerMax.x > outerMax.x)
        {
            return false;
        }

        else if(innerMin.y < outerMin.y || innerMax.y > outerMax.y)
        {
            return false;
        }

        else
        {
            return true;
        }
    }

    static private boolean rectanglesOverlap(Point2D.Double min1, Point2D.Double max1, Point2D.Double min2, Point2D.Double max2)
    {
        // Determines whether two rectangles overlap, based on current position and dimension.
        // Based on solution described at: http://www.geeksforgeeks.org/find-two-rectangles-overlap/

        if(min1.y > max2.y || min2.y > max1.y)
        {
            //System.out.println("Vertically Disjoint");
            return false;
        }

        else if(min1.x > max2.x || min2.x > max1.x)
        {
            //System.out.println("Horizontally Disjoint");
            return false;
        }

        else
        {
            //System.out.println("CollisionChecker detected!");
            return true;
        }
    }

    static private Point2D.Double makeMax(Point2D.Double min, Dimension dimension)
    {
        return new Point2D.Double(min.x + dimension.width, min.y + dimension.height);
    }

    static public boolean entityNotNearWorld(Entity entity, Dimension worldSize)
    {
        Point2D.Double min1 = farTopLeft;
        Point2D.Double min2 = entity.getOrigin();
        Point2D.Double max1 = makeMax(min1, new Dimension(worldSize.width + 100, worldSize.height + 100));
        Point2D.Double max2 = makeMax(min2, entity.getDimension());

        return !rectanglesBounded(min1, max1, min2, max2);
    }

    static public boolean entityInWorld(Entity entity, Dimension worldSize)
    {
        Point2D.Double min1 = origin;
        Point2D.Double min2 = entity.getOrigin();
        Point2D.Double max1 = makeMax(min1, worldSize);
        Point2D.Double max2 = makeMax(min2, entity.getDimension());

        return rectanglesBounded(min1, max1, min2, max2);
    }

    static public boolean entityOnWorld(Entity entity, Dimension worldSize)
    {
        Point2D.Double min1 = origin;
        Point2D.Double min2 = entity.getOrigin();
        Point2D.Double max1 = makeMax(min1, worldSize);
        Point2D.Double max2 = makeMax(min2, entity.getDimension());

        return rectanglesOverlap(min1, max1, min2, max2);
    }

    static public boolean entitiesCollide(Entity entity1, Entity entity2)
    {
        if(entity1 == entity2)
        {
            // An entity cannot collide with itself
            return false;
        }

        //System.out.println("Checking for collision");
        Point2D.Double min1 = entity1.getOrigin();
        Point2D.Double min2 = entity2.getOrigin();
        Point2D.Double max1 = makeMax(min1, entity1.getDimension());
        Point2D.Double max2 = makeMax(min2, entity2.getDimension());

        return rectanglesOverlap(min1, max1, min2, max2);
    }
}