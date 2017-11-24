package Wylaga.Overstates.Displayables.Explosions;

import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Util.Random;
import Wylaga.Util.Trajectory;


import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Explosion extends SimpleDisplayable
{
    private Graphics2D g2d;

    private int size;

    private Color color;

    private int lifespan = 100; //18 + Random.rollInt(7);

    private List<Particle> particles;

    public Explosion(Point point, int size, Color color, int particleCount)
    {
        super(point, new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB));
        this.size = size;
        int halfSize = size / 2;
        this.color = color;
        this.g2d = super.getImage().createGraphics();
        g2d.setClip(new Ellipse2D.Double(0, 0, size, size));
        g2d.setBackground(new Color(0, 0, 0,0));

        particles = new ArrayList<>();
        for(int i = 0; i < particleCount; i++)
        {
            particles.add(new Particle(new Point(halfSize, halfSize),2, 2));
        }
    }

    public void update()
    {
        --lifespan;

        g2d.clearRect(0, 0, size, size);

        g2d.setColor(color);

        for(Particle particle : particles)
        {
            particle.update();
            //g2d.drawImage(particle.image, particle.origin.x, particle.origin.y, null);
            g2d.fillRect((int) particle.position.x, (int) particle.position.y, 2, 2);
        }

    }

    public boolean expired()
    {
        return lifespan <= 0;
    }

    private class Particle
    {
        private Point origin;
        private Point2D.Double position;
        private BufferedImage image;
        private Trajectory trajectory;
        private int speed;
        private double dx;
        private double dy;

        public Particle(Point origin, int width, int height)
        {
            this.origin = origin;
            this.position = new Point2D.Double(origin.x, origin.y);
            this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            this.speed = Random.rollInt(12) + 4;
            this.trajectory = new Trajectory(Random.rollInt(100) - 50, Random.rollInt(100) - 50);
            //System.out.println("Traj: " + trajectory.getDx() + "," + trajectory.getDy());

            this.dx = (trajectory.getDx() * speed);
            this.dy = (trajectory.getDy() * speed);

            if(dx == 0 && dy == 0)
            {
                dy = 1;
            }

            g2d.setColor(Color.ORANGE);
            g2d.fillRect(0, 0, 2, 2);
        }

        public void update()
        {
            position.setLocation(position.x + dx, position.y + dy);
        }

        public BufferedImage getImage() {return image;}
    }
}