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
        int[][] rocks = {
                //0  1  2  3  4  5  6  7  8  9  10
                {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0}, // 0
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0}, // 1
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0}, // 2
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0}, // 3
                {0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1}, // 4
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 5
                {0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1}, // 6
                {0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1}, // 7
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 8
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 9
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0} // 10
        };
        for (int row = 0; row < rocks.length; row++) {
            for (int col = 0; col < rocks[0].length; col++) {
                if (rocks[row][col] == 1)
                    world.add(new Location(row, col), new Rock());
            }
        }

        final Player player = new Player();
        world.add(new Location(world.getGrid().getNumRows() / 2, world.getGrid().getNumCols() / 2), player);

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
                    displayGrid.move(dir);
                }

                return true;
            }
        });

        world.show();
    }
}
