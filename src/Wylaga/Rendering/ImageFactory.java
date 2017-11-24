package Wylaga.Rendering;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFactory
{
    // Image IO is handled as soon as the ImageFactory class is loaded:
    private static final BufferedImage basePlayerImage = loadImage("BasePlayer.png");

    private static final BufferedImage playerProjectileImage = loadImage("PlayerProjectile.png");
    private static final BufferedImage projectileImage = loadImage("Projectile.png");

    private static final BufferedImage smallBasePlayerImage = loadImage("MiniPlayer.png");

    private static final BufferedImage enemyBaseChassis = loadImage("ComponentImages/EnemyBaseChassis.png");
    private static final BufferedImage enemyHurtChassis = loadImage("ComponentImages/EnemyHurtChassis.png");
    private static final BufferedImage enemyBaseWeapon = loadImage("ComponentImages/EnemyBaseWeapon.png");
    private static final BufferedImage enemyFiringWeapon = loadImage("ComponentImages/EnemyFiringWeapon.png");
    private static final BufferedImage enemyBaseEngine = loadImage("ComponentImages/EnemyBaseEngine.png");
    private static final BufferedImage enemyBoostEngine = loadImage("ComponentImages/EnemyBoostEngine.png");
    private static final BufferedImage enemyBrakeImage = enemyBoostEngine;


    private static final BufferedImage playerBaseChassis = loadImage("ComponentImages/PlayerBaseChassis.png");
    private static final BufferedImage playerHurtChassis = loadImage("ComponentImages/PlayerHurtChassis.png");
    private static final BufferedImage playerBaseWeapon = loadImage("ComponentImages/PlayerBaseWeapon.png");
    private static final BufferedImage playerFiringWeapon = loadImage("ComponentImages/PlayerFiringWeapon.png");
    private static final BufferedImage playerBaseEngine = loadImage("ComponentImages/PlayerBaseEngine.png");
    private static final BufferedImage playerBoostEngine = loadImage("ComponentImages/PlayerBoostEngine.png");
    private static final BufferedImage playerBrakeImage = loadImage("ComponentImages/PlayerBrakeEngine.png");
    private static final BufferedImage playerBaseSpecial = loadImage("ComponentImages/PlayerBaseSpecial.png");
    private static final BufferedImage playerDeployedSpecial = loadImage("ComponentImages/PlayerDeployedSpecial.png");

    private static final BufferedImage healthPickupImage = loadImage("HealthPickup.png");
    private static final BufferedImage scorePickupImage = loadImage("ScorePickup.png");


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

    public static BufferedImage getProjectileImage()
    {
        return projectileImage;
    }

    public static BufferedImage getPlayerProjectileImage() {
        return playerProjectileImage;
    }

    public static BufferedImage getSmallBasePlayerImage() { return smallBasePlayerImage; }

    public static BufferedImage getPlayerHurtChassis() {
        return playerHurtChassis;
    }

    public static BufferedImage getPlayerBaseChassis() {
        return playerBaseChassis;
    }

    public static BufferedImage getPlayerFiringWeapon() {
        return playerFiringWeapon;
    }

    public static BufferedImage getPlayerBaseWeapon() {
        return playerBaseWeapon;
    }

    public static BufferedImage getPlayerBaseEngine() {
        return playerBaseEngine;
    }

    public static BufferedImage getPlayerBoostEngine() {
        return playerBoostEngine;
    }

    public static BufferedImage getEnemyBaseChassis() { return enemyBaseChassis; }

    public static BufferedImage getEnemyHurtChassis() { return enemyHurtChassis; }

    public static BufferedImage getEnemyFiringWeapon() { return enemyFiringWeapon; }

    public static BufferedImage getEnemyBaseWeapon() { return enemyBaseWeapon; }

    public static BufferedImage getEnemyBaseEngine() { return enemyBaseEngine; }

    public static BufferedImage getEnemyBoostEngine() { return enemyBoostEngine; }

    public static BufferedImage getPlayerBrakeImage() {
        return playerBrakeImage;
    }

    public static BufferedImage getEnemyBrakeImage() {
        return enemyBrakeImage;
    }

    public static BufferedImage getPlayerBaseSpecial() {
        return playerBaseSpecial;
    }

    public static BufferedImage getPlayerDeployedSpecial() {
        return playerDeployedSpecial;
    }

    public static BufferedImage getHealthPickupImage() { return healthPickupImage; }

    public static BufferedImage getScorePickupImage() { return scorePickupImage; }
}
