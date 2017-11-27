package Wylaga.Overstates.Displayables;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public interface Displayable
{
    // Accessors:

    Point2D.Double getPosition();
    Dimension getSize();

    // By default, Displayables don't expire.
    // expired() should be overridden by subclasses that want to take advantage of it.
    default boolean expired() {return false;}

    // Mutators:

    // By default, Displayables don't do anything when updated.
    // update() should be overridden by subclasses that want to take advantage of it.
    default void update() {}

    void draw(Graphics2D g2d);

    void drawWithOffset(Graphics2D g2d, Point2D.Double offset);
}
