package com.search.problem;

/**
 * Created by Sondre on 27.09.2014.
 */
public class RushHourGoalTest {

    public RushHourGoalTest() {

    }

    public boolean isGoalState(RushHourGridMap state) {
        return state.getGoal().equals(state.getCars().get(0).getPosition());
    }
}
