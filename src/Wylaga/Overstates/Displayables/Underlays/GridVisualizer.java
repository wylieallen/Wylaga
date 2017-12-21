package Wylaga.Overstates.Displayables.Underlays;

import Wylaga.Overstates.Displayables.CompositeDisplayable;
import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Overstates.Game.Collisions.Grid;
import Wylaga.Util.ImageFactory;
import Wylaga.WylagaApp;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedHashSet;
import java.util.Set;

public class GridVisualizer extends CompositeDisplayable
{
    public GridVisualizer(Point2D.Double point, Grid grid)
    {
        super(point, makeFromGrid(grid), new Dimension(WylagaApp.WIDTH, WylagaApp.HEIGHT));
    }

    private static Set<Displayable> makeFromGrid(Grid grid)
    {
        Set<Displayable> displayables = new LinkedHashSet<>();
        displayables.add(new SimpleDisplayable(new Point2D.Double(0, 0), ImageFactory.makeBlackRect(WylagaApp.WIDTH, WylagaApp.HEIGHT)));
        for(Grid.Cell cell : grid.getAllCells())
        {
            displayables.add(new CellDisplayable(cell));
        }
        return displayables;
    }

    private static class CellDisplayable implements Displayable
    {
        private static Color[] colors = initializeColors();

        private Point2D.Double origin;
        private Dimension dimension;

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

        public CellDisplayable(Grid.Cell cell)
        {
            this.origin = new Point2D.Double(cell.getOrigin().x, cell.getOrigin().y);
            this.dimension = cell.getSize();
            this.cell = cell;
        }

        public void draw(Graphics2D g2d)
        {
            drawAtPoint(g2d, origin);
        }

        public void drawWithOffset(Graphics2D g2d, Point2D.Double offset)
        {
            drawAtPoint(g2d, new Point2D.Double(origin.x + offset.x, origin.y + offset.y));
        }

        private void drawAtPoint(Graphics2D g2d, Point2D.Double point)
        {
            g2d.setColor(getColor());
            g2d.fillRect((int) point.x, (int) point.y, dimension.width, dimension.height);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawRect((int) point.x, (int) point.y, dimension.width - 1, dimension.height - 1);
        }

        @Override
        public void update()
        {
            cellPopulation = cell.getShips().size() + cell.getPickups().size() + cell.getProjectiles().size();
        }

        public Point2D.Double getPosition() {return origin;}
        public Dimension getSize() {return dimension;}


        private Color getColor()
        {
            return cellPopulation < 3 ? colors[cellPopulation] : colors[3];
        }

    }
}
