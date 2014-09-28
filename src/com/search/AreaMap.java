package com.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Sondre on 27.09.2014.
 */
public class AreaMap extends State{

    ArrayList<ArrayList<Character>> state;
    int height;
    int width;
    Position goal;
    Position start;
    Position current;
    char BLOCKED = '#';
    char GOAL = 'B';
    char START_NODE = 'A';

    public AreaMap(ArrayList<ArrayList<Character>> state, int height, int width) {
        this.state = state;
        this.height = height;
        this.width = width;
        this.goal = findGoalPosition();
        this.start = findStartPosition();
        this.current = this.start;
    }

    public AreaMap(ArrayList<ArrayList<Character>> state, int height, int width, Position current, Position goal, Position start) {
        this.state = state;
        this.height = height;
        this.width = width;
        this.goal = goal;
        this.start = start;
        this.current = current;
    }

    public ArrayList<ArrayList<Character>> getState() {
        return state;
    }

    public void setState(ArrayList<ArrayList<Character>> state) {
        this.state = state;
    }

    public AreaMap (AreaMap areaMap) {
        this(areaMap.getState(), areaMap.getHeight(), areaMap.getWidth(), areaMap.getCurrentPosition(), areaMap.getGoal(), areaMap.getStart());
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

    public Position getCurrent() {
        return current;
    }

    public void setCurrentPosition(Position position) {
        this.current = position;
    }

    public Position getGoal() {
        return goal;
    }

    public Position getStart() {
        return start;
    }

    public boolean isBlocked(Position position) {
        return state.get(position.getX()).get(position.getY()) == BLOCKED;
    }

    public boolean isPositionWithinBounds(Position position) {
        boolean vari = (position.getX() >= 0 && position.getX() < this.height
                && position.getY() >= 0 && position.getY() < this.width);
        return vari;
    }

    public boolean isValidPosition(Position position) {
        return isPositionWithinBounds(position) && !isBlocked(position);
    }

    public Position findGoalPosition() {
        for (int i = 0; i<state.size(); i++) {
            for (int j = 0; j<state.get(i).size(); j++) {
                if (state.get(i).get(j) == 'B') return new Position(i,j);
            }
        }
        /*Iterator iterator = state.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry pairs = (Map.Entry)iterator.next();
            if ((Character)pairs.getValue() == 'B') return (Position) pairs.getKey();
            //iterator.remove();
        }*/
        return null;
        //throw new Exception("Unable to find goal position");
    }

    public Position findStartPosition() {
        for (int i = 0; i<state.size(); i++) {
            for (int j = 0; j<state.get(i).size(); j++) {
                if (state.get(i).get(j) == 'A') return new Position(i,j);
            }
        }
       /* Iterator iterator = state.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry pairs = (Map.Entry)iterator.next();
            if ((Character)pairs.getValue() == 'A') return (Position) pairs.getKey();
            //iterator.remove();
        }*/
        return null;
        //throw new Exception("Unable to find start position");
    }

    public void move(Position position) {
        setCurrentPosition(position);
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
