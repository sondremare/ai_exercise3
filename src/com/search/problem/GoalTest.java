package com.search.problem;

/**
 * Created by Sondre on 27.09.2014.
 */
public class GoalTest {

    public GoalTest() {

    }

    public boolean isGoalState(GridMap state) {
        return state.getGoalPosition().getX() == state.getCurrentPosition().getX() && state.getGoalPosition().getY() == state.getCurrentPosition().getY();
    }
}
