package Wylaga.Overstates.Displayables.Underlays.GridVisualization;

import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Overstates.Game.Collisions.Cell;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class CellDisplayable extends SimpleDisplayable
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
        super(new Point2D.Double(cell.getOrigin().x, cell.getOrigin().y), new BufferedImage(cell.getSize().width, cell.getSize().height, BufferedImage.TYPE_INT_ARGB));
        this.g2d = super.getImage().createGraphics();
        this.cell = cell;
    }

    public Color getColor()
    {
        return cellPopulation < 3 ? colors[cellPopulation] : colors[3];
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
