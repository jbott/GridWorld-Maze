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
		Actor winner;
        if(getGrid().isValid(newLocation))
		{
			if((winner = getGrid().get(newLocation)) == null)
				moveTo(newLocation);
			else if(winner instanceof Flower)
			{
				moveTo(newLocation);
				winner.act();
			}
		}
        setDirection(dir);
    }

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
