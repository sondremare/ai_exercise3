package com.search;

/**
 * Created by Sondre on 27.09.2014.
 */
public class ResultFunction {

    public ResultFunction() {

    }

    public AreaMap result(Action action, AreaMap areaMap) {
        Position currentPosition = areaMap.getCurrentPosition();
        Position newPosition;
        if (action == Action.NORTH) {
            newPosition = new Position(currentPosition.getX(), currentPosition.getY()-1);
            if (areaMap.isValidPosition(newPosition)) {
                AreaMap newAreaMap = new AreaMap(areaMap);
                newAreaMap.move(newPosition);
                return newAreaMap;
            }
        } else if (action == Action.SOUTH) {
            newPosition = new Position(currentPosition.getX(), currentPosition.getY()+1);
            if (areaMap.isValidPosition(newPosition)) {
                AreaMap newAreaMap = new AreaMap(areaMap);
                newAreaMap.move(newPosition);
                return newAreaMap;
            }
        } else if (action == Action.WEST) {
            newPosition = new Position(currentPosition.getX()-1, currentPosition.getY());
            if (areaMap.isValidPosition(newPosition)) {
                AreaMap newAreaMap = new AreaMap(areaMap);
                newAreaMap.move(newPosition);
                return newAreaMap;
            }
        } else if (action == Action.EAST) {
            newPosition = new Position(currentPosition.getX()+1, currentPosition.getY());
            if (areaMap.isValidPosition(newPosition)) {
                AreaMap newAreaMap = new AreaMap(areaMap);
                newAreaMap.move(newPosition);
                return newAreaMap;
            }
        }
        return null;
    }
}
