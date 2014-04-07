package com.mazegame;

import info.gridworld.actor.*;

public class Winner extends Flower
{
	private boolean didWin = false;
	
	public Winner()
	{
		super();
	}
	
	public void act()
	{
		setDirection(getDirection() + 5);
		if(getGrid() == null || getLocation() == null)
			didWin = true;
	}
	
	public boolean won()
	{
		return didWin;
	}
	
	
}