package com.mazegame;

import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class MazeGame {
    public static void main(String[] args) {
        BoundedGrid<Actor> maze = new BoundedGrid<>(51, 51);
        // Load maze into maze grid here
        final DisplayGrid<Actor> displayGrid = new DisplayGrid<>(maze, 11, 11);
        final ActorWorld world = new ActorWorld(displayGrid);
        world.addOccupantClass(Player.class.getName());
        world.setMessage("Press the arrow keys to move!");
        final Player player = new Player();
        int[][] rocks = {
                //0  1  2  3  4  5  6  7  8  9  10
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // 0
                {1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1}, // 1
                {1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1}, // 2
                {1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1}, // 3
                {1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1}, // 4
                {1, 1, 0, 0, 0, 2, 0, 0, 0, 0, 1}, // 5
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1}, // 6
                {1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1}, // 7
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 8
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 9
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1} // 10
        };
        for (int row = 0; row < rocks.length; row++) {
            for (int col = 0; col < rocks[0].length; col++) {
                if (rocks[row][col] == 1)
//                  world.add(new Location(row, col), new Rock());
                (new Rock()).putSelfInGrid(maze, new Location(row, col));
                else if(rocks[row][col] == 2)
                    player.putSelfInGrid(maze, new Location(row, col));
            }
        }

        //world.add(new Location(world.getGrid().getNumRows() / 2, world.getGrid().getNumCols() / 2), player);
        displayGrid.setOffset(new Location(player.getLocation().getRow()-displayGrid.getNumRows()/2,
                                                        player.getLocation().getCol()-displayGrid.getNumCols()/2));

        java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new java.awt.KeyEventDispatcher() {
            public boolean dispatchKeyEvent(java.awt.event.KeyEvent event) {
                String key = javax.swing.KeyStroke.getKeyStrokeForEvent(event).toString();

                int dir = -1;
                if (key.equals("pressed UP"))    dir = Location.NORTH;
                if (key.equals("pressed RIGHT")) dir = Location.EAST;
                if (key.equals("pressed DOWN"))  dir = Location.SOUTH;
                if (key.equals("pressed LEFT"))  dir = Location.WEST;

                if (dir != -1) {
                    player.move(dir);
                    displayGrid.setOffset(new Location(player.getLocation().getRow()-displayGrid.getNumRows()/2,
                                                        player.getLocation().getCol()-displayGrid.getNumCols()/2));
                    //displayGrid.move(dir);
                }

                return true;
            }
        });

        world.show();
    }
}
