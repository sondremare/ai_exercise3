package com.search.problem;

/**
 * Created by Sondre on 27.09.2014.
 */
public class ResultFunction {

    public ResultFunction() {

    }

    /**
     * Returns a new state based on given action, if that action is valid for a given state.
     * @param action - Domain specific action
     * @param gridMap - State to perform the action on
     * @return new State based on the action
     */
    public GridMap result(Action action, GridMap gridMap) {
        Position currentPosition = gridMap.getCurrentPosition();
        Position newPosition;
        if (action == Action.NORTH) {
            newPosition = new Position(currentPosition.getX(), currentPosition.getY()-1);
            if (gridMap.isValidPosition(newPosition)) {
                GridMap newGridMap = new GridMap(gridMap);
                newGridMap.move(newPosition);
                return newGridMap;
            }
        } else if (action == Action.SOUTH) {
            newPosition = new Position(currentPosition.getX(), currentPosition.getY()+1);
            if (gridMap.isValidPosition(newPosition)) {
                GridMap newGridMap = new GridMap(gridMap);
                newGridMap.move(newPosition);
                return newGridMap;
            }
        } else if (action == Action.WEST) {
            newPosition = new Position(currentPosition.getX()-1, currentPosition.getY());
            if (gridMap.isValidPosition(newPosition)) {
                GridMap newGridMap = new GridMap(gridMap);
                newGridMap.move(newPosition);
                return newGridMap;
            }
        } else if (action == Action.EAST) {
            newPosition = new Position(currentPosition.getX()+1, currentPosition.getY());
            if (gridMap.isValidPosition(newPosition)) {
                GridMap newGridMap = new GridMap(gridMap);
                newGridMap.move(newPosition);
                return newGridMap;
            }
        }
        return null;
    }
}
