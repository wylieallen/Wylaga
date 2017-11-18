package Wylaga.Overstates.Game.Collisions;

import Wylaga.Overstates.Game.Entities.Entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Grid partitions a 2D space into rectangular Cells, which it stores and facilitates access to.
public class Grid
{
    private List<Cell> cellList;
    private Cell[][] cells;

    private int cellWidth;
    private int cellHeight;

    private int cellCols;
    private int cellRows;

    public Grid(int width, int height, int cellCols, int cellRows)
    {
        if(width == 0 || height == 0 || cellCols == 0 || cellRows == 0)
        {
            throw new IllegalArgumentException();
        }

        this.cellCols = cellCols;
        this.cellRows = cellRows;

        cellWidth = width / cellCols;
        cellHeight = height / cellRows;

        cells = new Cell[cellCols][cellRows];
        cellList = new ArrayList<>();

        for(int i = 0; i < cellCols; i++)
        {
            for(int j = 0; j < cellRows; j++)
            {
                cellList.add(cells[i][j] = new Cell());
            }
        }
    }

    public List<Cell> getCellList() {return cellList;}

    public void clear()
    {
        for(Cell cell : cellList)
        {
            cell.clear();
        }
    }

    public void addAll(List<? extends Entity> entities)
    {
        for(Entity entity : entities)
        {
            addToCells(entity);
        }
    }

    public void addToCells(Entity entity)
    {
        Point originCell = getCell(entity.getOrigin());
        Point terminusCell = getCell(entity.getTerminus());

        for(int i = originCell.x; inRange(i, 0, Math.min(terminusCell.x, cellCols - 1)); i++)
        {
            for(int j = originCell.y; inRange(j, 0, Math.min(terminusCell.y, cellRows - 1)); j++)
            {
                entity.addToCell(cells[i][j]);
            }
        }
    }

    private boolean inRange(int val, int min, int max)
    {
        return min <= val && val <= max;
    }

    private Point getCell(Point point)
    {
        return new Point(point.x / cellWidth, point.y / cellHeight);
    }
}
