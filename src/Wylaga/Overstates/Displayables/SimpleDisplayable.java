package Wylaga.Overstates.Displayables;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class SimpleDisplayable implements Displayable {
    public final static Displayable nullDisplayable = new SimpleDisplayable(new Point2D.Double(0, 0), new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));

    private Point2D.Double position;
    private BufferedImage image;

    public SimpleDisplayable(Point2D.Double position, BufferedImage image)
    {
        this.position = position;
        this.image = image;
    }

    protected void setImage(BufferedImage image) {this.image = image;}

    @Override
    public BufferedImage getImage() {return image;}

    @Override
    public Point2D.Double getPosition() {return position;}

    // By default, Displayables don't do anything when updated.
    // update() should be overridden by subclasses that want to take advantage of it.
    @Override
    public void update() {};

    // By default, Displayables don't expire.
    // expired() should be overridden by subclasses that want to take advantage of it.
    @Override
    public boolean expired() {return false;}

    @Override
    public void draw(Graphics2D g2d) { g2d.drawImage(image, (int) position.x, (int) position.y, null); }

    @Override
    public void drawWithOffset(Graphics2D g2d, Point2D.Double offset)
    {
        g2d.drawImage(image, (int) (position.x + offset.x), (int) (position.y + offset.y), null);
    }
}
