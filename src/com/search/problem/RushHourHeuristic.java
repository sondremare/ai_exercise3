package com.search.problem;

import com.search.State;

/**
 * Created by Sondre on 28.09.2014.
 */
public class RushHourHeuristic {

    public RushHourHeuristic() {

    }

    /** Heuristic for 2d grids based on Manhattan distance + number of cars in the way*/
    public float calculateHeuristic(State state) {
        if (state instanceof RushHourGridMap) {
            RushHourGridMap gridMap = (RushHourGridMap) state;
            Car primaryCar = gridMap.getCars().get(0);
            int numberOfObstacles = 0;
            int numberOfGridsToGoal = 0;
            boolean[][] occupied = gridMap.findOccupiedSpots(primaryCar);
            int xStartPosition = primaryCar.getPosition().getX();
            int xGoalPosition = gridMap.getGoal().getX();
            int carLength = primaryCar.getLength();
            for (int i = xStartPosition+carLength; i<=xGoalPosition; ++i) {
                if (occupied[i][primaryCar.getPosition().getY()]) numberOfObstacles++;
                numberOfGridsToGoal++;
            }
            return numberOfObstacles + 1;
        } else return 0;

    }
}
