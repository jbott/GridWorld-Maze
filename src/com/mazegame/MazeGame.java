package com.mazegame;

import info.gridworld.actor.*;

public class MazeGame
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Bug());
        world.add(new Rock());
        world.show();
    }
}
