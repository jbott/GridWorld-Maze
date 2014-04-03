package com.mazegame;

import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class DisplayGrid<E> extends BoundedGrid<E> {
    private Location offset = new Location(0, 0);
    private Grid<E> rootGrid;

    DisplayGrid(Grid<E> rootGrid, int row, int col) {
        super(row, col);
        this.rootGrid = rootGrid;
        setOffset(new Location((rootGrid.getNumRows() / 2) - (row / 2), (rootGrid.getNumCols() / 2) - (col / 2)));
    }

    public void setOffset(Location location) {
        offset = location;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return (E) rootGrid.get(new Location(loc.getRow() + offset.getRow(), loc.getCol() + offset.getCol()));
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        return rootGrid.put(new Location(loc.getRow() + offset.getRow(), loc.getCol() + offset.getCol()), obj);
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");

        // Remove the object from the grid.
        return rootGrid.remove(new Location(loc.getRow() + offset.getRow(), loc.getCol() + offset.getCol()));
    }


    public void move(int dir) {
        offset = offset.getAdjacentLocation(dir);
    }

}
