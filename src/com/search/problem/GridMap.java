package com.search.problem;

import com.search.State;

import java.util.ArrayList;

/**
 * Created by Sondre on 27.09.2014.
 */

/** Class representing the state of certain 2d grid problems */
public class GridMap extends State {

    private ArrayList<ArrayList<Character>> state;
    private int height;
    private int width;
    private Position goal;
    private Position start;
    private Position current;
    private static final char BLOCKED_NODE = '#';
    private static final char GOAL_NODE = 'B';
    private static final char START_NODE = 'A';

    public GridMap(ArrayList<ArrayList<Character>> state, int height, int width) {
        this.state = state;
        this.height = height;
        this.width = width;
        this.goal = findGoalPosition();
        this.start = findStartPosition();
        this.current = this.start;
    }

    public GridMap(ArrayList<ArrayList<Character>> state, int height, int width, Position current, Position goal, Position start) {
        this.state = state;
        this.height = height;
        this.width = width;
        this.goal = goal;
        this.start = start;
        this.current = current;
    }

    public GridMap(GridMap gridMap) {
        this(gridMap.getState(), gridMap.getHeight(), gridMap.getWidth(), gridMap.getCurrentPosition(), gridMap.getGoalPosition(), gridMap.getStart());
    }

    public ArrayList<ArrayList<Character>> getState() {
        return state;
    }

    public Position getCurrentPosition() {
        return this.current;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setCurrentPosition(Position position) {
        this.current = position;
    }

    public Position getGoalPosition() {
        return goal;
    }

    public Position getStart() {
        return start;
    }

    public boolean isBlocked(Position position) {
        return state.get(position.getX()).get(position.getY()) == BLOCKED_NODE;
    }

    public boolean isPositionWithinBounds(Position position) {
        return (position.getX() >= 0 && position.getX() < this.height
                && position.getY() >= 0 && position.getY() < this.width);
    }

    public boolean isValidPosition(Position position) {
        return isPositionWithinBounds(position) && !isBlocked(position);
    }

    public Position findGoalPosition() {
        for (int i = 0; i<state.size(); i++) {
            for (int j = 0; j<state.get(i).size(); j++) {
                if (state.get(i).get(j) == GOAL_NODE) return new Position(i,j);
            }
        }
        return null;
    }

    public Position findStartPosition() {
        for (int i = 0; i<state.size(); i++) {
            for (int j = 0; j<state.get(i).size(); j++) {
                if (state.get(i).get(j) == START_NODE) return new Position(i,j);
            }
        }
        return null;
    }

    public void move(Position position) {
        setCurrentPosition(position);
    }

    public float getGCost() {
        Position current = getCurrentPosition();
        char mapCell = getState().get(current.getX()).get(current.getY());
        switch (mapCell) {
            case 'w':
                return 100;
            case 'm':
                return 50;
            case 'f':
                return 10;
            case 'g':
                return 5;
            case 'r':
                return 1;
            default:
                return 1;
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i<state.size(); i++) {
            for (int j = 0; j<state.get(i).size(); j++) {
                result += state.get(i).get(j);
            }
            result += "\n";
        }
        return result;
    }
}
