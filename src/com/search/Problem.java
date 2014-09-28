package com.search;

/**
 * Created by Sondre on 27.09.2014.
 */
public class Problem {

    private ResultFunction resultFunction;
    private GoalTest goalTest;

    public Problem() {
        this.resultFunction = new ResultFunction();
        this.goalTest = new GoalTest();
    }

    public ResultFunction getResultFunction() {
        return this.resultFunction;
    }

    public GoalTest getGoalTest() {
        return this.goalTest;
    }
}

