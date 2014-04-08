package com.mazegame;

import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class MazeGame {
    public static void main(String[] args) {
        // Create root grid
        BoundedGrid<Actor> maze = new BoundedGrid<>(51, 51);

        // Load maze into maze grid here
        MazeLoader mazeLoader = new MazeLoader(MazeGame.class.getResourceAsStream("maze.txt"));
        mazeLoader.createGrid(maze);

        // Keep references to the important game pieces
        final Player player = mazeLoader.getPlayer();
        final Winner win = mazeLoader.getWinner();

        // Initialize the display grid
        if(args.length < 1)
        {
            System.out.println("Call with displayGrid size");
            return;
        }
        final DisplayGrid<Actor> displayGrid = new DisplayGrid<>(maze, Integer.parseInt(args[0]), Integer.parseInt(args[0]));
        displayGrid.setOffset(new Location(player.getLocation().getRow()-displayGrid.getNumRows()/2,
                player.getLocation().getCol()-displayGrid.getNumCols()/2));

        // Initialize world display
        final ActorWorld world = new ActorWorld(displayGrid);
        // Set a nice message
        world.setMessage("Press the arrow keys to move!");

        // Handle keyboard events
        java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new java.awt.KeyEventDispatcher() {
            public boolean dispatchKeyEvent(java.awt.event.KeyEvent event) {
                String key = javax.swing.KeyStroke.getKeyStrokeForEvent(event).toString();

                int dir = -1;
                if (!win.won()) {
                    if (key.equals("pressed UP")) dir = Location.NORTH;
                    if (key.equals("pressed RIGHT")) dir = Location.EAST;
                    if (key.equals("pressed DOWN")) dir = Location.SOUTH;
                    if (key.equals("pressed LEFT")) dir = Location.WEST;
                }
                if (dir != -1) {
                    player.move(dir);
                    displayGrid.setOffset(new Location(player.getLocation().getRow()-displayGrid.getNumRows()/2,
                                                        player.getLocation().getCol()-displayGrid.getNumCols()/2));
                }
				
				if(win.won())
					world.setMessage("YEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHH");

                return true;
            }
        });

        // Make sure to display the image
        world.show();
    }
}
