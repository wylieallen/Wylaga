package Wylaga.Overstates.Displayables.Explosions;

import Wylaga.Overstates.Displayables.CompositeDisplayable;
import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Util.Random.Random;
import Wylaga.Util.Trajectory;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Explosion extends CompositeDisplayable
{
    private int lifespan = 100; //18 + RandomNumberGenerator.rollInt(7);

    public Explosion(Point2D.Double point, int size, Color color, int particleCount)
    {
        super(new Point2D.Double(point.x, point.y), makeParticles(particleCount, size / 2, size / 2, color), new Dimension(size, size));
    }

    private static Set<Displayable> makeParticles(int count, int initial, int size, Color color)
    {
        Set<Displayable> particles = new LinkedHashSet<>();

        for(int i = 0; i < count; i++)
        {
            int roll = Random.rollInt(3) + 1;
            particles.add(new Particle(new Point(initial, initial), roll, roll, color, size));
        }

        return particles;
    }


    @Override
    public void update()
    {
        super.update();
        --lifespan;
    }

    @Override
    public boolean expired()
    {
        return lifespan <= 0;
    }

    private static class Particle implements Displayable
    {
        private Point2D.Double position;
        private Trajectory trajectory;
        private double dx;
        private double dy;

        private Dimension size;

        private Color color;

        private Shape shape;

        private int lifespan;

        public Particle(Point origin, int width, int height, Color color, int maxDist)
        {
            this.position = new Point2D.Double(origin.x, origin.y);
            int speed = Random.rollInt(12) + 4;
            this.trajectory = new Trajectory(Random.rollInt(360) - 180, Random.rollInt(360) - 180);
            this.color = color;
            this.size = new Dimension(width, height);
            //System.out.println("Traj: " + trajectory.getDx() + "," + trajectory.getDy());

            this.dx = (trajectory.getDx() * speed);
            this.dy = (trajectory.getDy() * speed);

            if(dx == 0 && dy == 0)
            {
                dy = 1;
            }

            double unitMag = Math.sqrt(dx * dx + dy * dy);

            this.lifespan = (int) Math.ceil(maxDist / unitMag);

            int roll = Random.rollInt(10);

            if(roll < 4)
            {
                this.shape = new Rectangle2D.Double(0, 0, width, height);
            }
            else if (roll < 6)
            {
                this.shape = new Ellipse2D.Double(0, 0, width, height);
            }
            else
            {
                this.shape = new Polygon(new int[]{0, width / 2, 0}, new int[]{0, height, 0}, 3);
            }
        }

        @Override
        public Dimension getSize() {
            return size;
        }

        @Override
        public Point2D.Double getPosition()
        {
            return position;
        }

        @Override
        public void draw(Graphics2D g2d)
        {
            Color prevColor = g2d.getColor();

            g2d.setColor(color);
            //g2d.fillRect((int) position.x, (int) position.y, size.width, size.height);

            AffineTransform t = g2d.getTransform();
            g2d.translate((int) position.x, (int) position.y);

            g2d.fill(shape);

            g2d.setTransform(t);
            g2d.setColor(prevColor);
        }

        @Override
        public void drawWithOffset(Graphics2D g2d, Point2D.Double offset)
        {
            Color prevColor = g2d.getColor();

            g2d.setColor(color);
            //g2d.fillRect((int) (position.x + offset.x), (int) (position.y + offset.y), size.width, size.height);

            AffineTransform t = g2d.getTransform();
            g2d.translate((int) (position.x + offset.x), (int) (position.y + offset.y));
            g2d.fill(shape);

            g2d.setTransform(t);
            g2d.setColor(prevColor);
        }

        @Override
        public boolean expired()
        {
            return lifespan <= 0;
        }

        @Override
        public void update()
        {
            position.setLocation(position.x + dx, position.y + dy);
            lifespan--;
        }
    }
}
