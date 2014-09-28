package com.search;

/**
 * Created by Sondre on 28.09.2014.
 */
public class Heuristic {

    public Heuristic() {

    }

    public float calculateHeuristic(State state) {
        if (state instanceof AreaMap) {
            AreaMap areaMap = (AreaMap) state;
            Position current = areaMap.getCurrentPosition();
            Position goal = areaMap.getGoal();
            return (Math.abs(current.getX() - goal.getX()) + Math.abs(current.getY() - goal.getY()));
        } else return 0;

    }
}
