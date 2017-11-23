package Wylaga.Overstates.Game.Entities.Ships.ShipComponents;

import java.awt.*;

public class ShipChassis
{
    private int maxHealth;
    private int curHealth;
    private Dimension dimension;

    public ShipChassis(int health, int width, int height)
    {
        this.maxHealth = this.curHealth = health;
        this.dimension = new Dimension(width, height);
    }

    public Dimension getDimension() {return dimension;}
    public int getCurHealth() {return curHealth;}
    public int getMaxHealth() {return maxHealth;}

    public void damage(int damage)
    {
        curHealth -= damage;
    }

    public void heal(int healing)
    {
        curHealth += healing;

        if(curHealth > maxHealth)
            curHealth = maxHealth;
    }
}
