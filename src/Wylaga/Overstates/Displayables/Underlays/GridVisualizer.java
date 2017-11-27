package Wylaga.Overstates.Displayables.Underlays;

import Wylaga.Overstates.Displayables.CompositeDisplayable;
import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Overstates.Game.Collisions.Grid;
import Wylaga.Rendering.ImageFactory;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class GridVisualizer extends CompositeDisplayable
{
    public GridVisualizer(Point2D.Double point, Grid grid)
    {
        super(point, makeFromGrid(grid), new Dimension(1280, 720));
    }

    private static Set<Displayable> makeFromGrid(Grid grid)
    {
        Set<Displayable> displayables = new LinkedHashSet<>();
        displayables.add(new SimpleDisplayable(new Point2D.Double(0, 0), ImageFactory.makeBlackRect(1280, 720)));
        for(Grid.Cell cell : grid.getAllCells())
        {
            displayables.add(new CellDisplayable(cell));
        }
        return displayables;
    }

    private static class CellDisplayable extends SimpleDisplayable
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

        private Grid.Cell cell;
        private int cellPopulation;

        private Graphics2D g2d;

        public CellDisplayable(Grid.Cell cell)
        {
            super(new Point2D.Double(cell.getOrigin().x, cell.getOrigin().y), new BufferedImage(cell.getSize().width, cell.getSize().height, BufferedImage.TYPE_INT_ARGB));
            this.g2d = super.getImage().createGraphics();
            this.cell = cell;
        }

        private Color getColor()
        {
            return cellPopulation < 3 ? colors[cellPopulation] : colors[3];
        }

        public void update()
        {
            cellPopulation = cell.getShips().size() + cell.getProjectiles().size() + cell.getPickups().size();
            g2d.setColor(getColor());
            g2d.fillRect(0, 0, cell.getSize().width, cell.getSize().height);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawRect(0, 0, cell.getSize().width - 1, cell.getSize().height - 1);
        }
    }
}
