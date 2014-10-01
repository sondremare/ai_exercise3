package com.search.problem;

import com.search.State;

/**
 * Created by Sondre on 28.09.2014.
 */
public class Heuristic {

    public Heuristic() {

    }

    /** Heuristic for 2d grids based on Manhattan distance */
    public float calculateHeuristic(State state) {
        if (state instanceof GridMap) {
            GridMap gridMap = (GridMap) state;
            Position current = gridMap.getCurrentPosition();
            Position goal = gridMap.getGoalPosition();
            return (Math.abs(current.getX() - goal.getX()) + Math.abs(current.getY() - goal.getY()));
        } else return 0;

    }
}
