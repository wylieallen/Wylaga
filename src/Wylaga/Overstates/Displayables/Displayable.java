package Wylaga.Overstates.Displayables;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public interface Displayable {
    // Accessors:
    Point2D.Double getPosition();
    BufferedImage getImage();

    boolean expired();

    // Mutators:
    void update();

    void draw(Graphics2D g2d);
    void drawWithOffset(Graphics2D g2d, Point2D.Double offset);
}
