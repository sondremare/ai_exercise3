package com.search;

/**
 * Created by Sondre on 27.09.2014.
 */
public class Problem {

    private ResultFunction resultFunction;
    private GoalTest goalTest;
    private Heuristic heuristic;

    public Problem() {
        this.resultFunction = new ResultFunction();
        this.goalTest = new GoalTest();
        this.heuristic = new Heuristic();
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

