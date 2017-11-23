package Wylaga.Overstates.Displayables;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Displayable {
    Point getPosition();
    BufferedImage getImage();
    // By default, Displayables don't do anything when updated.
    // update() should be overridden by subclasses that want to take advantage of it.
    void update();

    // By default, Displayables don't expire.
    // expired() should be overridden by subclasses that want to take advantage of it.
    boolean expired();

    void draw(Graphics2D g2d);

    void drawWithOffset(Graphics2D g2d, Point position);
}
