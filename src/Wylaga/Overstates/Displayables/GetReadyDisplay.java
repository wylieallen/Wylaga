package Wylaga.Overstates.Displayables;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GetReadyDisplay extends Displayable
{
    private Graphics2D g2d;
    private int counter = 0;

    public GetReadyDisplay(Point position)
    {
        super(position, makeGetReadyImg());
        g2d = super.getImage().createGraphics();
        g2d.setBackground(new Color(0, 0, 0 ,0));
        g2d.setColor(Color.RED);
        Font font = new Font("Courier New", 1, 64);
        g2d.setFont(font);
    }

    private static BufferedImage makeGetReadyImg()
    {
        BufferedImage img = new BufferedImage(384, 64, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.RED);
        Font font = new Font("Courier New", 1, 64);
        g2d.setFont(font);
        g2d.drawString("GET RDY: 3", 0, 64);
        return img;
    }

    public void update()
    {
        counter = (counter >= 180) ? 0 : counter + 1;
        //counter++;
        //if(counter >= 180) counter = 0;
        g2d.clearRect(0, 0, 384, 64);
        g2d.drawString("GET RDY: " + (((180 - counter) / 60) + 1), 0, 64);
    }
}