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
            int movesToMoveObstacle = 0;
            for (int i = xStartPosition+carLength; i<=xGoalPosition; ++i) {
                if (occupied[i][primaryCar.getPosition().getY()]) {
                    /** we found part of a car, now we search vertically for the nose of the car **/
                    for (int j = primaryCar.getPosition().getY(); j>=0; j--) {
                        for (Car car : gridMap.getCars()) {
                            if (car.getPosition().equals(new Position(i,j))) {
                                int yDistance = primaryCar.getPosition().getY() - car.getPosition().getY();
                                int yMovesUp = car.getLength() - yDistance;
                                int yMovesDown = yDistance + 1;
                                if (yMovesDown < yMovesDown) {
                                    Position movingPosition = new Position(car.getPosition().getX(), car.getPosition().getY() + yMovesDown);
                                    if (gridMap.isPositionWithinBounds(car, movingPosition)) {
                                        movesToMoveObstacle += yMovesDown;
                                    } else {
                                        movesToMoveObstacle += yMovesUp;
                                    }
                                } else {
                                    Position movingPosition = new Position(car.getPosition().getX(), car.getPosition().getY() + yMovesUp);
                                    if (gridMap.isPositionWithinBounds(car, movingPosition)) {
                                        movesToMoveObstacle += yMovesUp;
                                    } else {
                                        movesToMoveObstacle += yMovesDown;
                                    }
                                }
                            }
                        }
                    }
                    numberOfObstacles++;
                }
                numberOfGridsToGoal++;
            }
            return numberOfObstacles + ((numberOfGridsToGoal > 0) ? 1 : 0) + movesToMoveObstacle;
        } else return 0;

    }
}
