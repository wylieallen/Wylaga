package Wylaga.Overstates.Displayables.Explosions;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Util.Random;
import Wylaga.Util.Trajectory;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Explosion extends Displayable
{
    private Graphics2D g2d;

    private int size;

    private Color color;

    private int lifespan = 18 + Random.rollInt(7);

    private List<Particle> particles;

    public Explosion(Point point, int size, Color color)
    {
        super(point, new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB));
        this.size = size;
        this.color = color;
        this.g2d = super.getImage().createGraphics();
        g2d.setBackground(new Color(0, 0, 0,0));

        particles = new ArrayList<>();
        for(int i = 0; i < 20 + Random.rollInt(20); i++)
        {
            particles.add(new Particle(new Point(size / 2, size / 2),2, 2));
        }
    }

    public void update()
    {
        //System.out.println("Updating an explosion");


        --lifespan;


        g2d.clearRect(0, 0, size, size);

        g2d.setColor(color);

        for(Particle particle : particles)
        {
            particle.update();
            //g2d.drawImage(particle.image, particle.origin.x, particle.origin.y, null);
            g2d.fillRect(particle.position.x, particle.position.y, 2, 2);
        }

    }

    public boolean expired()
    {
        return lifespan <= 0 || super.getPosition().y < 0;
    }

    private class Particle
    {
        private Point origin;
        private Point position;
        private BufferedImage image;
        private Trajectory trajectory;
        private int speed;

        public Particle(Point origin, int width, int height)
        {
            this.origin = origin;
            this.position = new Point(origin.x, origin.y);
            this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            this.speed = Random.rollInt(12) + 4;
            this.trajectory = new Trajectory(Random.rollInt(100) - 50, Random.rollInt(100) - 50);
            //System.out.println("Traj: " + trajectory.getDx() + "," + trajectory.getDy());

            if(trajectory.getDx() == 0 && trajectory.getDy() == 0)
            {
                System.out.println("null projtraj");
                trajectory = Trajectory.getDirection(0, 1);
            }

            g2d.setColor(Color.ORANGE);
            g2d.fillRect(0, 0, 2, 2);
        }

        public void update()
        {
            int dx = (int) (trajectory.getDx() * speed);
            int dy = (int) (trajectory.getDy() * speed);

            //System.out.println("dx, dy = " + dx + "," + dy);

            if(dx == 0 && dy == 0)
            {
                dy = 1;
            }

            position.translate(dx, dy);
        }

        public BufferedImage getImage() {return image;}
    }
}
