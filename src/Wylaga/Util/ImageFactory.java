package Wylaga.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFactory
{
    private static final String compDir = "ComponentImages/";
    private static final String enemyDir = compDir + "Enemy/";
    private static final String playerDir = compDir + "Player/";
    private static final String wingmanDir = compDir + "Wingman/";

    // Image IO is handled as soon as the ImageFactory class is loaded:

    private static final BufferedImage wylagaLogoImage = loadImage("WylagaLogoBig.png");

    private static final BufferedImage basePlayerImage = loadImage("BasePlayer.png");

    private static final BufferedImage playerProjectileImage = loadImage("PlayerProjectile.png");
    private static final BufferedImage orangePlayerProjectileImage = loadImage("OrangePlayerProjectile.png");
    private static final BufferedImage yellowPlayerProjectileImage = loadImage("YellowPlayerProjectile.png");
    private static final BufferedImage greenPlayerProjectileImage = loadImage("GreenPlayerProjectile.png");
    private static final BufferedImage cyanPlayerProjectileImage = loadImage("CyanPlayerProjectile.png");
    private static final BufferedImage magentaPlayerProjectileImage = loadImage("MagentaPlayerProjectile.png");

    private static final BufferedImage projectileImage = loadImage("Projectile.png");
    private static final BufferedImage wingmanProjectileImage = playerProjectileImage.getSubimage(1, 1, 1, 10);

    private static final BufferedImage smallBasePlayerImage = loadImage("MiniPlayer.png");

    private static final BufferedImage enemyBaseChassis = loadImage(enemyDir + "EnemyBaseChassis.png");
    private static final BufferedImage enemyHurtChassis = loadImage(enemyDir +"EnemyHurtChassis.png");
    private static final BufferedImage enemyNearDeathChassis = loadImage(enemyDir + "EnemyNearDeathChassis.png");
    private static final BufferedImage enemyBaseWeapon = loadImage(enemyDir + "EnemyBaseWeapon.png");
    private static final BufferedImage enemyFiringWeapon = loadImage(enemyDir + "EnemyFiringWeapon.png");
    private static final BufferedImage enemyBaseEngine = loadImage(enemyDir + "EnemyBaseEngine.png");
    private static final BufferedImage enemyBoostEngine = loadImage(enemyDir + "EnemyBoostEngine.png");
    private static final BufferedImage enemyBrakeImage = enemyBoostEngine;

    private static final BufferedImage playerBaseChassis = loadImage(playerDir + "PlayerBaseChassis.png");
    private static final BufferedImage playerHurtChassis = loadImage(playerDir + "PlayerHurtChassis.png");
    private static final BufferedImage playerNearDeathChassis = loadImage(playerDir + "PlayerNearDeathChassis.png");
    private static final BufferedImage playerBaseWeapon = loadImage(playerDir + "PlayerBaseWeapon.png");
    private static final BufferedImage playerFiringWeapon = loadImage(playerDir + "PlayerFiringWeapon.png");
    private static final BufferedImage playerBaseEngine = loadImage(playerDir + "PlayerBaseEngine.png");
    private static final BufferedImage playerBoostEngine = loadImage(playerDir + "PlayerBoostEngine1.png");
    private static final BufferedImage playerBoostEngine2 = loadImage(playerDir + "PlayerBoostEngine2.png");
    private static final BufferedImage playerBrakeImage = loadImage(playerDir + "PlayerBrakeEngine.png");
    private static final BufferedImage playerBaseSpecial = loadImage(playerDir + "PlayerBaseSpecial.png");
    private static final BufferedImage playerDeployedSpecial = loadImage(playerDir + "PlayerDeployedSpecial.png");

    private static final BufferedImage playerFiringOrangeWeapon = loadImage(playerDir + "PlayerFiringOrangeWeapon.png");
    private static final BufferedImage playerFiringYellowWeapon = loadImage(playerDir + "PlayerFiringYellowWeapon.png");
    private static final BufferedImage playerFiringGreenWeapon = loadImage(playerDir + "PlayerFiringGreenWeapon.png");
    private static final BufferedImage playerFiringCyanWeapon = loadImage(playerDir + "PlayerFiringCyanWeapon.png");
    private static final BufferedImage playerFiringMagentaWeapon = loadImage(playerDir + "PlayerFiringMagentaWeapon.png");

    private static final BufferedImage wingmanHurtChassis = loadImage(wingmanDir + "WingmanHurtChassis.png");
    private static final BufferedImage wingmanBaseChassis = loadImage(wingmanDir + "WingmanBaseChassis.png");
    private static final BufferedImage wingmanNearDeathChassis = loadImage(wingmanDir + "WingmanNearDeathChassis.png");
    private static final BufferedImage wingmanBaseWeapon = loadImage(wingmanDir + "WingmanBaseWeapon.png");
    private static final BufferedImage wingmanFiringWeapon = loadImage(wingmanDir + "WingmanFiringWeapon.png");
    private static final BufferedImage wingmanBaseEngine = loadImage(wingmanDir + "WingmanBaseEngine.png");
    private static final BufferedImage wingmanBoostEngine = loadImage(wingmanDir + "WingmanBoostEngine1.png");
    private static final BufferedImage wingmanBoostEngine2 = loadImage(wingmanDir + "WingmanBoostEngine2.png");
    private static final BufferedImage wingmanBrakeImage = loadImage(wingmanDir + "WingmanBrakeEngine.png");
    private static final BufferedImage wingmanBaseSpecial = loadImage(wingmanDir + "WingmanBaseSpecial.png");
    private static final BufferedImage wingmanDeployedSpecial = loadImage(wingmanDir + "WingmanDeployedSpecial.png");

    private static final BufferedImage healthPickupImage = loadImage("HealthPickup.png");
    private static final BufferedImage scorePickupImage = loadImage("ScorePickup.png");
    private static final BufferedImage powerPickupImage = loadImage("PowerPickup.png");
    private static final BufferedImage wingmanPickupImage = loadImage("WingmanPickup.png");
    private static final BufferedImage superHealthPickupImage = loadImage("SuperHealthPickup.png");
    private static final BufferedImage superWingmanPickupImage = loadImage("SuperWingmanPickup.png");
    private static final BufferedImage megaHealthPickupImage = loadImage("MegaHealthPickup.png");
    private static final BufferedImage weaponPickupImage = loadImage("WeaponUpgradePickup.png");

    private static final BufferedImage pressAnyKeyImage = loadImage("PressAnyKey.png");

    private static final BufferedImage exitButtonImage = loadImage("ExitButton.png");
    private static final BufferedImage startButtonImage = loadImage("StartButton.png");


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
        //return makeVerticalMenuButton("EXIT", new Point(150, 50), Color.WHITE, Color.GRAY, Color.BLACK);
        return exitButtonImage;
    }

    public static BufferedImage makeExitButtonHover()
    {
        //return makeVerticalMenuButton("EXIT", new Point(150, 50), Color.RED, Color.GRAY, Color.BLACK);
        BufferedImage image = new BufferedImage(exitButtonImage.getWidth(), exitButtonImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(exitButtonImage, 0, 0, null);
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(0, 0, exitButtonImage.getWidth() - 1, exitButtonImage.getHeight() - 1);
        return image;
    }

    public static BufferedImage makeExitButtonPress()
    {
        return makeVerticalMenuButton("EXIT", new Point(150, 50), Color.RED, Color.GRAY, Color.WHITE);
    }


    // Start button images:

    public static BufferedImage makeStartButtonBase()
    {
        //return makeVerticalMenuButton("START", new Point(150, 50), Color.WHITE, Color.GRAY, Color.BLACK);
        return startButtonImage;
    }

    public static BufferedImage makeStartButtonHover()
    {
        //return makeVerticalMenuButton("START", new Point(150, 50), Color.RED, Color.GRAY, Color.BLACK);
        BufferedImage image = new BufferedImage(startButtonImage.getWidth(), startButtonImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(startButtonImage, 0, 0, null);
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(0, 0, startButtonImage.getWidth() - 1, startButtonImage.getHeight() - 1);
        return image;
    }

    public static BufferedImage makeStartButtonPress()
    {
        BufferedImage image = new BufferedImage(startButtonImage.getWidth(), startButtonImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(startButtonImage, 0, 0, null);
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(0, 0, startButtonImage.getWidth() - 1, startButtonImage.getHeight() - 1);
        return image;
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

    public static BufferedImage getPowerPickupImage() { return powerPickupImage; }

    public static BufferedImage getPlayerBoostEngine2() {
        return playerBoostEngine2;
    }

    public static BufferedImage getWingmanHurtChassis() {
        return wingmanHurtChassis;
    }

    public static BufferedImage getWingmanBaseChassis() {
        return wingmanBaseChassis;
    }

    public static BufferedImage getWingmanBaseWeapon() {
        return wingmanBaseWeapon;
    }

    public static BufferedImage getWingmanFiringWeapon() {
        return wingmanFiringWeapon;
    }

    public static BufferedImage getWingmanBaseEngine() {
        return wingmanBaseEngine;
    }

    public static BufferedImage getWingmanBoostEngine() {
        return wingmanBoostEngine;
    }

    public static BufferedImage getWingmanBoostEngine2() {
        return wingmanBoostEngine2;
    }

    public static BufferedImage getWingmanBrakeImage() {
        return wingmanBrakeImage;
    }

    public static BufferedImage getWingmanBaseSpecial() {
        return wingmanBaseSpecial;
    }

    public static BufferedImage getWingmanDeployedSpecial() {
        return wingmanDeployedSpecial;
    }

    public static BufferedImage getWingmanPickupImage() { return wingmanPickupImage; }

    public static BufferedImage getWingmanProjectileImage() { return wingmanProjectileImage; }

    public static BufferedImage getPlayerNearDeathChassis() {
        return playerNearDeathChassis;
    }

    public static BufferedImage getEnemyNearDeathChassis() {
        return enemyNearDeathChassis;
    }

    public static BufferedImage getWingmanNearDeathChassis() {
        return wingmanNearDeathChassis;
    }

    public static BufferedImage getSuperHealthPickupImage() {
        return superHealthPickupImage;
    }

    public static BufferedImage getSuperWingmanPickupImage() {
        return superWingmanPickupImage;
    }

    public static BufferedImage getMegaHealthPickupImage() { return megaHealthPickupImage; }

    public static BufferedImage getOrangePlayerProjectileImage() { return orangePlayerProjectileImage; }

    public static BufferedImage makeWeaponPickupImage() { return weaponPickupImage; }

    public static BufferedImage getYellowPlayerProjectileImage() { return yellowPlayerProjectileImage; }

    public static BufferedImage getGreenPlayerProjectileImage() { return greenPlayerProjectileImage; }

    public static BufferedImage getCyanPlayerProjectileImage() { return cyanPlayerProjectileImage; }

    public static BufferedImage getMagentaPlayerProjectileImage() { return magentaPlayerProjectileImage; }

    public static BufferedImage getPlayerFiringOrangeWeapon() { return playerFiringOrangeWeapon; }

    public static BufferedImage getPlayerFiringYellowWeapon() { return playerFiringYellowWeapon; }

    public static BufferedImage getPlayerFiringGreenWeapon() { return playerFiringGreenWeapon; }

    public static BufferedImage getPlayerFiringCyanWeapon() { return playerFiringCyanWeapon; }

    public static BufferedImage getPlayerFiringMagentaWeapon() { return playerFiringMagentaWeapon; }

    public static BufferedImage getWylagaLogoImage() { return wylagaLogoImage; }

    public static BufferedImage getPressAnyKeyImage()
    {
        return pressAnyKeyImage;
    }
}
