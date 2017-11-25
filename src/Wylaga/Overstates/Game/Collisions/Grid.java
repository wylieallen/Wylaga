package Wylaga.Overstates.Game.Collisions;

import Wylaga.Overstates.Game.Entities.Entity;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

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

        Dimension cellSize = new Dimension(cellWidth, cellHeight);

        cells = new Cell[cellCols][cellRows];

        occupiedCells = new HashSet<>();

        for(int i = 0; i < cellCols; i++)
        {
            for(int j = 0; j < cellRows; j++)
            {
                cells[i][j] = new Cell(new Point(i * cellWidth,  j * cellHeight), cellSize);
            }
        }
    }

    public void addAll(List<? extends Entity> entities)
    {
        for(Entity entity : entities)
        {
            addToCells(entity);
        }
    }

    private void addToCells(Entity entity)
    {
        Point originCell = getCellIndices(entity.getOrigin());
        Point terminusCell = getCellIndices(entity.getTerminus());

        for(int i = originCell.x; inRange(i, Math.min(terminusCell.x, cellCols - 1)); i++)
        {
            for(int j = originCell.y; inRange(j, Math.min(terminusCell.y, cellRows - 1)); j++)
            {
                Cell cell = cells[i][j];
                entity.addToCell(cell);
                occupiedCells.add(cell);
            }
        }
    }

    private boolean inRange(int val, int max) { return 0 <= val && val <= max; }

    private Point getCellIndices(Point2D.Double point)
    {
        return new Point((int) point.x / cellWidth, (int) point.y / cellHeight);
    }

    public Set<Cell> getOccupiedCells() { return occupiedCells; }

    public int getCellWidth() { return cellWidth; }
    public int getCellHeight() { return cellHeight; }
    public int getCellCols() { return cellCols; }
    public int getCellRows() { return cellRows; }

    public Set<Cell> getAllCells()
    {
        Set<Cell> cellList = new HashSet<>();
        for(int i = 0; i < cellCols; i++)
        {
            for(int j = 0; j < cellRows; j++)
            {
                cellList.add(cells[i][j]);
            }
        }
        return cellList;
    }

    public void clear()
    {
        for(Cell cell : occupiedCells)
        {
            cell.clear();
        }
        occupiedCells.clear();
    }
}
