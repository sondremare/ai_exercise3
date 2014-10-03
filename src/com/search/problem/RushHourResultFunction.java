package com.search.problem;

/**
 * Created by Sondre on 27.09.2014.
 */
public class RushHourResultFunction {

    public RushHourResultFunction() {

    }

    /**
     * Returns a new state based on given action, if that action is valid for a given state.
     * @param action - Domain specific action
     * @param gridMap - State to perform the action on
     * @return new State based on the action
     */
    public RushHourGridMap result(Action action, Car car, RushHourGridMap gridMap) {
        Position currentPosition = car.getPosition();
        Position newPosition;
        if (action == Action.NORTH) {
            newPosition = new Position(currentPosition.getX(), currentPosition.getY()-1);
            if (!car.isHorizontal() && gridMap.isValidPosition(car, newPosition)) {
                RushHourGridMap newGridMap = new RushHourGridMap(gridMap);
                newGridMap.move(car, newPosition);
                return newGridMap;
            }
        } else if (action == Action.SOUTH) {
            newPosition = new Position(currentPosition.getX(), currentPosition.getY()+1);
            if (!car.isHorizontal()) {
                boolean valid = true;
                for (int i = 0; i<car.getLength(); i++) {
                    Position carPosition = new Position(currentPosition.getX(), currentPosition.getY()+1+i);
                    if (!gridMap.isValidPosition(car, carPosition)) {
                        valid = false;
                    }
                }
                if (valid) {
                    RushHourGridMap newGridMap = new RushHourGridMap(gridMap);
                    newGridMap.move(car, newPosition);
                    return newGridMap;
                }
            }
        } else if (action == Action.WEST) {
            newPosition = new Position(currentPosition.getX()-1, currentPosition.getY());
            if (car.isHorizontal() && gridMap.isValidPosition(car, newPosition)) {
                RushHourGridMap newGridMap = new RushHourGridMap(gridMap);
                newGridMap.move(car, newPosition);
                return newGridMap;
            }
        } else if (action == Action.EAST) {
            newPosition = new Position(currentPosition.getX()+1, currentPosition.getY());
            if (car.isHorizontal()) {
                boolean valid = true;
                for (int i = 0; i<car.getLength(); i++) {
                    Position carPosition = new Position(currentPosition.getX()+car.getLength()+i, currentPosition.getY());
                    if (!gridMap.isValidPosition(car, carPosition)) {
                        valid = false;
                    }
                }
                if (valid) {
                    RushHourGridMap newGridMap = new RushHourGridMap(gridMap);
                    newGridMap.move(car, newPosition);
                    return newGridMap;
                }
            }
        }
        return null;
    }
}
