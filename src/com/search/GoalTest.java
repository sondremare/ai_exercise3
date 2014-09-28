package com.search;

/**
 * Created by Sondre on 27.09.2014.
 */
public class GoalTest {

    public GoalTest() {

    }

    public boolean isGoalState(AreaMap state) {
        return state.getGoal().getX() == state.getCurrent().getX() && state.getGoal().getY() == state.getCurrent().getY();
    }
}
