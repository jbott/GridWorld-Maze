package com.mazegame;

import info.gridworld.actor.*;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

public class MazeGame
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld(new BoundedGrid<Actor>(11, 11));
        world.setMessage("Press the arrow keys to move!");
        int[][] rocks = {
                //0  1  2  3  4  5  6  7  8  9  10
                { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 }, // 0
                { 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, // 1
                { 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, // 2
                { 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, // 3
                { 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1 }, // 4
                { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, // 5
                { 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1 }, // 6
                { 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1 }, // 7
                { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 8
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 9
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } // 10
        };
        for(int row = 0; row < rocks.length; row++) {
            for(int col = 0; col < rocks[0].length; col++) {
                if (rocks[row][col] == 1)
                    world.add(new Location(row, col), new Rock());
            }
        }
        world.show();
    }
}
