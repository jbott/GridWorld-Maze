/**
 * @(#)MazeLoader.java
 *
 *
 * @author Will Hamilton
 * @version 1.00 2014/4/4
 */
package com.mazegame;

import info.gridworld.actor.*;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

import java.io.InputStream;
import java.util.Scanner;

public class MazeLoader {
    private InputStream file;
    private Scanner scan;
    private Player player;
    private Winner winner;

    public MazeLoader(InputStream file) {
        this.file = file;
    }

    public void createGrid(BoundedGrid<Actor> maze) {
        int row = 0;
        int col = 0;
        try {

            scan = new Scanner(file);
            while (scan.hasNextLine()) {

                String in = scan.nextLine();
                String[] rocks = in.split(" ");
                for (col = 0; col < rocks.length; col++) {
                    if (rocks[col].equals("1")) {
                        Rock r = new Rock();
                        r.putSelfInGrid(maze, new Location(row, col));
                    }
                    if (rocks[col].equals("2")) {
                        player = new Player();
                        player.putSelfInGrid(maze, new Location(row, col));
                    }
                    if (rocks[col].equals("3")) {
                        winner = new Winner();
                        winner.putSelfInGrid(maze, new Location(row, col));
                    }
					if (rocks[col].equals("4")) {
						(new Bug()).putSelfInGrid(maze, new Location(row, col));
					}
					if (rocks[col].equals("5")) {
						(new Critter()).putSelfInGrid(maze, new Location(row, col));
					}
                }
                row++;
                col = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Winner getWinner() {
        return winner;
    }
}