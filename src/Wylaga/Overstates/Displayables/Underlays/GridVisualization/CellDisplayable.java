package Wylaga.Overstates.Displayables.Underlays.GridVisualization;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Game.Collisions.Cell;
import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CellDisplayable extends Displayable
{
    private static Color[] colors = initializeColors();

    private static Color[] initializeColors()
    {
        Color[] colors = new Color[4];
        colors[0] = Color.BLACK;
        colors[1] = new Color(0xFF, 0xCC, 0xCC);
        colors[2] = new Color(0xFF, 0x80, 0x80);
        colors[3] = Color.RED;
        return colors;
    }

    private Cell cell;
    private int cellPopulation;

    private Graphics2D g2d;

    public CellDisplayable(Cell cell)
    {
        super(cell.getOrigin(), new BufferedImage(cell.getSize().width, cell.getSize().height, BufferedImage.TYPE_INT_ARGB));
        this.g2d = super.getImage().createGraphics();
        this.cell = cell;
    }

    public Color getColor()
    {
        if(cellPopulation < 3)
        {
            return colors[cellPopulation];
        }
        else
        {
            return colors[3];
        }
    }

    public void update()
    {
        cellPopulation = cell.getShips().size() + cell.getProjectiles().size();
        g2d.setColor(getColor());
        g2d.fillRect(0, 0, cell.getSize().width, cell.getSize().height);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRect(0, 0, cell.getSize().width - 1, cell.getSize().height - 1);
    }
}
