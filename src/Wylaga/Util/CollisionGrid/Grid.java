package Wylaga.Util.CollisionGrid;

import Wylaga.Overstates.Game.Entities.Projectiles.Projectile;
import Wylaga.Overstates.Game.Entities.Ships.PlayerShip;
import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Grid
{
    private List<Cell> cellList;
    private Cell[][] cells;

    private int cellWidth;
    private int cellHeight;

    public Grid(int width, int height, int cellCols, int cellRows)
    {
        cellWidth = width / cellCols;
        cellHeight = height / cellRows;

        System.out.println("cellsize = " + cellWidth + " , " + cellHeight);

        Dimension cellSize = new Dimension(cellWidth, cellHeight);

        cells = new Cell[cellCols][cellRows];
        cellList = new ArrayList<>();
        for(int i = 0; i < cellCols; i++)
        {
            for(int j = 0; j < cellRows; j++)
            {
                //System.out.println("i, j = " + i + " , " + j);
                Point origin = new Point(i * cellWidth, j * cellHeight);
                cellList.add(cells[i][j] = new Cell(origin, cellSize));
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

    public void addProjectiles(List<Projectile> projectiles)
    {
        for(Projectile projectile : projectiles)
        {
            add(projectile);
        }
    }

    public void add(Projectile projectile)
    {
        Point originCell = getCell(projectile.getOrigin());
        Point terminusCell = getCell(projectile.getTerminus());

        for(int i = originCell.x; i >= 0 && i < 16 && i <= terminusCell.x; i++)
        {
            for(int j = originCell.y; j >= 0 && j < 9 && j <= terminusCell.y; j++)
            {
                cells[i][j].addProjectile(projectile);
            }
        }
    }

    public void addShips(List<Ship> ships)
    {
        for(Ship ship : ships)
        {
            add(ship);
        }
    }

    public void add(Ship ship)
    {
        Point originCell = getCell(ship.getOrigin());
        Point terminusCell = getCell(ship.getTerminus());

        System.out.println(originCell.toString() + terminusCell.toString());

        for(int i = originCell.x; i >= 0 && i < 16 && i <= terminusCell.x; i++)
        {
            for(int j = originCell.y; j >= 0 && j < 9 && j <= terminusCell.y; j++)
            {
                //System.out.println("Adding " + ship.toString() + " to " + i + " , " + j);
                cells[i][j].addShip(ship);
            }
        }
    }

    private Point getCell(Point point)
    {
        return new Point(point.x / cellWidth, point.y / cellHeight);
    }
}
