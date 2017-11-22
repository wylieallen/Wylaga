package Wylaga.Rendering;

import Wylaga.Overstates.Game.Entities.Projectiles.PlayerProjectile;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.EnemyShip;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFactory
{

    private static final BufferedImage basePlayerImage = loadImage("BasePlayer.png"); //makeFilledRect(PlayerShip.defaultDimension, Color.MAGENTA);
    private static final BufferedImage hurtPlayerImage = loadImage("HurtPlayer.png"); //makeFilledRect(PlayerShip.defaultDimension, Color.RED);
    private static final BufferedImage playerProjectileImage = loadImage("PlayerProjectile.png");
    private static final BufferedImage projectileImage = loadImage("Projectile.png");

    private static final BufferedImage baseEnemyImage = loadImage("BaseEnemy.png");//makeFilledOval(EnemyShip.defaultDimension, Color.BLUE);
    private static final BufferedImage hurtEnemyImage = loadImage("HurtEnemy.png");//makeFilledOval(EnemyShip.defaultDimension, Color.RED);
    private static final BufferedImage smallBasePlayerImage = loadImage("MiniPlayer.png");


    // Sprite construction:

    private static BufferedImage loadImage(String file)
    {
        try
        {
            return ImageIO.read(new File(file));
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    // Geometric primitives:

    public static BufferedImage makeBorderedRect(Dimension dimension, Color color)
    {
        BufferedImage image = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
        Graphics g2d = image.createGraphics();
        g2d.setColor(color);
        g2d.fillRect(0, 0, dimension.width,  dimension.height);
        g2d.setColor(Color.WHITE);
        g2d.drawRect(0, 0, dimension.width - 1, dimension.height - 1);
        return image;
    }

    public static BufferedImage makeBlackRect(int width, int height)
    {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);
        return img;
    }

    private static BufferedImage makeFilledRect(Dimension dimension, Color color)
    {
        BufferedImage image = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
        Graphics g2d = image.createGraphics();
        g2d.setColor(color);
        g2d.fillRect(0, 0, dimension.width,  dimension.height);
        return image;
    }

    private static BufferedImage makeFilledOval(Dimension dimension, Color color)
    {
        BufferedImage image = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
        Graphics g2d = image.createGraphics();
        g2d.setColor(color);
        g2d.fillOval(0, 0, dimension.width - 1,  dimension.height - 1);
        g2d.setColor(Color.WHITE);
        g2d.drawOval(0, 0, dimension.width - 1, dimension.height - 1);
        return image;
    }

    private static BufferedImage makeBorderedRect(Dimension dimension, Color bodyColor, Color borderColor)
    {
        BufferedImage image = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(bodyColor);
        g2d.fillRect(0, 0, dimension.width, dimension.height);
        g2d.setColor(borderColor);
        g2d.drawRect(0, 0, dimension.width - 1, dimension.height - 1);
        return image;
    }

    // Button primitives:

    private static BufferedImage makeVerticalMenuButton(String text, Point strPt,
                                                        Color backgroundColor, Color outlineColor, Color textColor)
    {
        int width = 400, height = 100;
        BufferedImage img = new BufferedImage(400, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(backgroundColor);
        g2d.fillRect(0,0, width - 1, height - 1);
        g2d.setColor(outlineColor);
        g2d.drawRect(0, 0, width - 1, height - 1);
        g2d.setColor(textColor);
        g2d.drawString(text, strPt.x, strPt.y);
        return img;
    }

    // Exit button images:

    public static BufferedImage makeExitButtonBase()
    {
        return makeVerticalMenuButton("EXIT", new Point(150, 50), Color.WHITE, Color.GRAY, Color.BLACK);
    }

    public static BufferedImage makeExitButtonHover()
    {
        return makeVerticalMenuButton("EXIT", new Point(150, 50), Color.RED, Color.GRAY, Color.BLACK);
    }

    public static BufferedImage makeExitButtonPress()
    {
        return makeVerticalMenuButton("EXIT", new Point(150, 50), Color.RED, Color.GRAY, Color.WHITE);
    }


    // Start button images:

    public static BufferedImage makeStartButtonBase()
    {
        return makeVerticalMenuButton("START", new Point(150, 50), Color.WHITE, Color.GRAY, Color.BLACK);
    }

    public static BufferedImage makeStartButtonHover()
    {
        return makeVerticalMenuButton("START", new Point(150, 50), Color.RED, Color.GRAY, Color.BLACK);
    }

    public static BufferedImage makeStartButtonPress()
    {
        return makeVerticalMenuButton("START", new Point(150, 50), Color.RED, Color.GRAY, Color.WHITE);
    }

    // Entity images:

    //public static BufferedImage makeEntityImage(Entity entity)
    //{
       // return makeFilledRect(entity.getDimension(), );
    //}

    public static BufferedImage getBasePlayerImage()
    {
        return basePlayerImage;
    }

    public static BufferedImage getHurtPlayerImage() { return hurtPlayerImage; }

    public static BufferedImage getProjectileImage()
    {
        return projectileImage;
    }

    public static BufferedImage getBaseEnemyImage() { return baseEnemyImage; }

    public static BufferedImage getHurtEnemyImage() { return hurtEnemyImage; }

    public static BufferedImage getPlayerProjectileImage() {
        return playerProjectileImage;
    }

    public static BufferedImage getSmallBasePlayerImage() { return smallBasePlayerImage; }
}
