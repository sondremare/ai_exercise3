package com.search.problem;

/**
 * Created by Sondre on 27.09.2014.
 */
public class GoalTest {

    public GoalTest() {

    }

    public boolean isGoalState(GridMap state) {
        return state.getGoalPosition().equals(state.getCurrentPosition());
    }
}
