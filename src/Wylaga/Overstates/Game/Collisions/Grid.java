package Wylaga.Overstates.Game.Collisions;

import Wylaga.Overstates.Game.Entities.Entity;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

// Grid partitions a 2D space into rectangular Cells, which it stores and facilitates access to.
public class Grid
{
    private Cell[][] cells;

    private Set<Cell> occupiedCells;

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

        occupiedCells = new HashSet<>();

        for(int i = 0; i < cellCols; i++)
        {
            for(int j = 0; j < cellRows; j++)
            {
                cells[i][j] = new Cell();
            }
        }
    }

    public void addAll(Set<? extends Entity> entities)
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

        for(int i = originCell.x; inRange(0, i, Math.min(terminusCell.x, cellCols - 1)); i++)
        {
            for(int j = originCell.y; inRange(0, j, Math.min(terminusCell.y, cellRows - 1)); j++)
            {
                Cell cell = cells[i][j];
                entity.addToCell(cell);
                occupiedCells.add(cell);
            }
        }
    }

    private boolean inRange(int min, int val, int max) { return min <= val && val <= max; }

    private Point getCell(Point point)
    {
        return new Point(point.x / cellWidth, point.y / cellHeight);
    }

    public Set<Cell> getOccupiedCells() { return occupiedCells; }

    public void clear()
    {
        for(Cell cell : occupiedCells)
        {
            cell.clear();
        }
        occupiedCells.clear();
    }
}
