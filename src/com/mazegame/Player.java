package com.mazegame;
import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class Player extends Actor {
    private String imageSuffix = "S";

    Player() {
        setColor(null);
    }

    public void move(int dir) {
        Location newLocation = getLocation().getAdjacentLocation(dir);
        if(getGrid().isValid(newLocation))
            moveTo(newLocation);
        setDirection(dir);
    }

    // Display grid handled elsewhere, player is unaware of how the grid functions, it's absolute to him
    /*
    @Override
    public void moveTo(Location newLocation)
    {
        Grid<Actor> grid = getGrid();
        // We will actually be in the center of the display
        Location loc = new Location(getGrid().getNumRows() / 2, getGrid().getNumCols() / 2);
        if (grid == null)
            throw new IllegalStateException("This actor is not in a grid.");
        if (grid.get(location) != this)
            throw new IllegalStateException(
                    "The grid contains a different actor at location "
                            + location + ".");
        if (!grid.isValid(newLocation))
            throw new IllegalArgumentException("Location " + newLocation
                    + " is not valid.");

        if (newLocation.equals(location))
            return;
        grid.remove(location);
        Actor other = grid.get(newLocation);
        if (other != null)
            other.removeSelfFromGrid();
        location = newLocation;
        grid.put(location, this);
    }
    */

    // We do not actually change direction, but rather change which image we display
    @Override
    public void setDirection(int dir) {
        super.setDirection(0);
        setImageSuffix(dir);
    }

    public int getImageDirection() {
        switch (getImageSuffix()) {
            case "N":
                return Location.NORTH;
            case "E":
                return Location.EAST;
            default:
            case "S":
                return Location.SOUTH;
            case "W":
                return Location.WEST;
        }
    }


    private void setImageSuffix(int direction) {
        if (direction % 90 != 0)
            direction += direction % 90;
        int adjustedDirection = direction % Location.FULL_CIRCLE;
        if (adjustedDirection < 0)
            adjustedDirection += Location.FULL_CIRCLE;

        switch(adjustedDirection) {
            case Location.NORTH:
                setImageSuffix("N");
                break;
            case Location.EAST:
                setImageSuffix("E");
                break;
            default:
            case Location.SOUTH:
                setImageSuffix("S");
                break;
            case Location.WEST:
                setImageSuffix("W");
                break;
        }
    }

    @Override
    public void act() {
        // Do nothing
    }

    public String getImageSuffix() {
        return imageSuffix;
    }

    public void setImageSuffix(String imageSuffix) {
        this.imageSuffix = imageSuffix;
    }
}
