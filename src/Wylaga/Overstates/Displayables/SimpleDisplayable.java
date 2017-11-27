package Wylaga.Overstates.Displayables;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class SimpleDisplayable implements Displayable {
    private Point2D.Double position;
    private BufferedImage image;
    private Dimension dimension;

    public SimpleDisplayable(Point2D.Double position, BufferedImage image)
    {
        this.position = position;
        this.image = image;
        this.dimension = new Dimension(image.getWidth(), image.getHeight());
    }

    protected void setImage(BufferedImage image) {this.image = image;}

    //@Override
    public BufferedImage getImage() {return image;}

    @Override
    public Dimension getSize() {return dimension;}

    @Override
    public Point2D.Double getPosition() {return position;}

    @Override
    public void draw(Graphics2D g2d)
    {
        g2d.drawImage(image, (int) position.x, (int) position.y, null);
    }

    @Override
    public void drawWithOffset(Graphics2D g2d, Point2D.Double offset)
    {
        Point2D.Double drawPoint = new Point2D.Double(position.x + offset.x, position.y + offset.y);
        g2d.drawImage(image, (int) drawPoint.x, (int) drawPoint.y, null);
    }

}
