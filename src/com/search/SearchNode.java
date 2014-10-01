package com.search;

import com.search.problem.Action;
import com.search.problem.GridMap;
import com.search.problem.Position;
import com.search.problem.Status;

import java.util.ArrayList;

/**
 * Created by Sondre on 23.09.2014.
 *
 */
public class SearchNode {
    GridMap state;
    private float g;
    private float h;
    private float f;
    private Status status;
    private SearchNode parent;
    private ArrayList<SearchNode> kids = new ArrayList<SearchNode>();

    public SearchNode(GridMap state, float g, float h) {
        this.state = state;
        this.g = g;
        this.h = h;
        this.f = this.g + this.h;
    }

    public GridMap getState() {
        return state;
    }

    public void setState(GridMap state) {
        this.state = state;
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
}
