package com.search.problem;

/**
 * Created by Sondre on 27.09.2014.
 */
public class RushHourProblem {

    private RushHourResultFunction resultFunction;
    private RushHourGoalTest goalTest;
    private RushHourHeuristic heuristic;
    private RushHourGridMap gridMap;

    public RushHourProblem() {
        this.resultFunction = new RushHourResultFunction();
        this.goalTest = new RushHourGoalTest();
        this.heuristic = new RushHourHeuristic();
    }

    public RushHourProblem(RushHourGridMap gridMap) {
        this.gridMap = gridMap;
        this.resultFunction = new RushHourResultFunction();
        this.goalTest = new RushHourGoalTest();
        this.heuristic = new RushHourHeuristic();
    }

    public RushHourGridMap getGridMap() {
        return gridMap;
    }

    public void setGridMap(RushHourGridMap gridMap) {
        this.gridMap = gridMap;
    }

    public RushHourResultFunction getResultFunction() {
        return this.resultFunction;
    }

    public RushHourGoalTest getGoalTest() {
        return this.goalTest;
    }

    public RushHourHeuristic getHeuristic() {
        return this.heuristic;
    }
}

