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
            if (!car.isHorizontal()){
                Position checkingPosition = new Position(currentPosition.getX(), currentPosition.getY()+car.getLength());
                if (gridMap.isValidPosition(car,checkingPosition)) {
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
            if (car.isHorizontal()){
                Position checkingPosition = new Position(currentPosition.getX()+car.getLength(), currentPosition.getY());
                if (gridMap.isValidPosition(car, checkingPosition)) {
                    RushHourGridMap newGridMap = new RushHourGridMap(gridMap);
                    newGridMap.move(car, newPosition);
                    return newGridMap;
                }
            }
        }
        return null;
    }
}
