package Wylaga.Overstates.Displayables;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class SubimageDisplayable implements Displayable
{
    private BufferedImage image;
    private Point2D.Double position;
    private Dimension size;

    private Point originOnImage;

    private int stepsPerFrame;
    private int stepsLeft;

    public SubimageDisplayable(BufferedImage image, Point2D.Double position, Dimension size, Point originOnImage, int stepsPerFrame)
    {
        this.image = image;
        this.position = position;
        this.size = size;
        this.originOnImage = originOnImage;
        this.stepsPerFrame = stepsPerFrame;
        this.stepsLeft = stepsPerFrame;
    }

    public void draw(Graphics2D g2d)
    {
        g2d.drawImage(image.getSubimage(originOnImage.x, originOnImage.y, size.width, size.height), (int) position.x, (int) position.y, null);
    }

    public void drawWithOffset(Graphics2D g2d, Point2D.Double offset)
    {
        g2d.drawImage(image.getSubimage(originOnImage.x, originOnImage.y, size.width, size.height), (int) (position.x + offset.x), (int) (position.y + offset.y), null);
    }

    public Point2D.Double getPosition()
    {
        return position;
    }

    public Dimension getSize() {return size;}

    public void update()
    {
        if(--stepsLeft <= 0)
        {
            int nextX = originOnImage.x + size.width;
            System.out.println("nextx " + nextX);
            if(nextX >= image.getWidth())
            {
                nextX = 0;
            }
            originOnImage = new Point(nextX, originOnImage.y);
            stepsLeft = stepsPerFrame;
        }
    }
}
