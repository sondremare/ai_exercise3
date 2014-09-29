package com.search.problem;

/**
 * Created by Sondre on 27.09.2014.
 */
public class Problem {

    private ResultFunction resultFunction;
    private GoalTest goalTest;
    private Heuristic heuristic;
    private GridMap gridMap;

    public Problem() {
        this.resultFunction = new ResultFunction();
        this.goalTest = new GoalTest();
        this.heuristic = new Heuristic();
    }

    public Problem(GridMap gridMap) {
        this.gridMap = gridMap;
        this.resultFunction = new ResultFunction();
        this.goalTest = new GoalTest();
        this.heuristic = new Heuristic();
    }

    public GridMap getGridMap() {
        return gridMap;
    }

    public void setGridMap(GridMap gridMap) {
        this.gridMap = gridMap;
    }

    public ResultFunction getResultFunction() {
        return this.resultFunction;
    }

    public GoalTest getGoalTest() {
        return this.goalTest;
    }

    public Heuristic getHeuristic() {
        return this.heuristic;
    }
}

