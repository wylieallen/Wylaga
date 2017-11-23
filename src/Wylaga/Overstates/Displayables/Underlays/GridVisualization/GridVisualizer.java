package Wylaga.Overstates.Displayables.Underlays.GridVisualization;

import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Overstates.Game.Collisions.Cell;
import Wylaga.Overstates.Game.Collisions.Grid;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class GridVisualizer extends SimpleDisplayable
{
    private Set<CellDisplayable> cellDisplayables;
    private Graphics2D g2d;

    public GridVisualizer(Point point, BufferedImage image, Grid grid)
    {
        super(point, image);
        g2d = image.createGraphics();
        cellDisplayables = new HashSet<>();
        for(Cell cell : grid.getAllCells())
        {
            cellDisplayables.add(new CellDisplayable(cell));
        }
        //g2d.setBackground(Color.BLACK);
        g2d.clearRect(0, 0, 1280, 720);
    }

    public void update()
    {
        g2d.clearRect(0, 0, 1280, 720);
        for(CellDisplayable cell : cellDisplayables)
        {
            cell.update();
            g2d.drawImage(cell.getImage(), cell.getPosition().x, cell.getPosition().y, null);
        }
    }
}
