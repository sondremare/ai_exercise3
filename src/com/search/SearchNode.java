package com.search;

import java.awt.geom.Area;
import java.util.ArrayList;

/**
 * Created by Sondre on 23.09.2014.
 */
public class SearchNode implements Comparable<SearchNode>{
    AreaMap state;
    private float g;
    private float h;
    private float f;
    private Status status;
    private SearchNode parent;
    private ArrayList<SearchNode> kids = new ArrayList<SearchNode>();

    public SearchNode(AreaMap state) {
        this.state = state;
        this.g = 0;
        this.h = CalculateHeuristic();
        this.f = this.g + this.h;
    }

    public SearchNode(AreaMap state, float g) {
        this.state = state;
        this.g = g;
        this.h = CalculateHeuristic();
        this.f = this.g + this.h;
    }

    public AreaMap getState() {
        return state;
    }

    public void setState(AreaMap state) {
        this.state = state;
    }

    /** Calculates heuristic based on Manhattan distance */
    public float CalculateHeuristic () {
        Position current = state.getCurrentPosition();
        Position goal = state.getGoal();
        return (Math.abs(current.getX() - goal.getX()) + Math.abs(current.getY() - goal.getY()));
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void open() {
        setStatus(Status.OPEN);
    }

    public void close() {
        setStatus(Status.CLOSED);
    }

    public SearchNode getParent() {
        return parent;
    }

    public void setParent(SearchNode parent) {
        this.parent = parent;
    }

    public ArrayList<SearchNode> getKids() {
        return kids;
    }

    public void setKids(ArrayList<SearchNode> kids) {
        this.kids = kids;
    }

    @Override
    public int compareTo(SearchNode sn) {
        if (this.getF() > sn.getF()) return 1;
        else if (this.getF() < sn.getF()) return -1;
        else return 0;
    }
}
