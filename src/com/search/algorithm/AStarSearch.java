package com.search.algorithm;

import com.search.SearchNode;
import com.search.gui.GUI;
import com.search.problem.*;

import java.util.*;

public class AStarSearch {
    private Problem problem;
    private boolean shouldDrawOpenAndClosedNodes;

    public AStarSearch(Problem problem, boolean shouldDrawOpenAndClosedNodes) {
        this.problem = problem;
        this.shouldDrawOpenAndClosedNodes = shouldDrawOpenAndClosedNodes;
    }

    public Problem getProblem() {
        return this.problem;
    }

    public boolean isShouldDrawOpenAndClosedNodes() {
        return this.shouldDrawOpenAndClosedNodes;
    }

    public void AttachAndEval(SearchNode successor, SearchNode parent) {
        Heuristic heuristic = problem.getHeuristic();
        successor.setParent(parent);
        successor.setG(parent.getG() + successor.getState().getGCost());
        successor.setH(heuristic.calculateHeuristic(successor.getState()));
        successor.setF(successor.getG() + successor.getH());
    }

    public void PropagatePathImprovements(SearchNode successor) {
        for (SearchNode kid : successor.getKids()) {
            if (successor.getG() + successor.getState().getGCost() < kid.getG()) {
                kid.setParent(successor);
                kid.setG(successor.getG() + successor.getState().getGCost());
                kid.setF(kid.getG() + kid.getH());
                PropagatePathImprovements(kid);
            }
        }
    }

    public ArrayList<SearchNode> generateAllSuccessors(SearchNode currentSearchNode, Problem problem) {
        ArrayList<SearchNode> children = new ArrayList<SearchNode>();
        for (Action action : Action.values()) {
            ResultFunction resultFunction = problem.getResultFunction();
            GridMap state = resultFunction.result(action, currentSearchNode.getState());
            if (state != null) {
                SearchNode childSearchNode = new SearchNode(state,currentSearchNode.getG());
                children.add(childSearchNode);
            }
        }
        return children;
    }

    public void search() throws Exception {

        Problem problem = getProblem();
        GridMap map = problem.getGridMap();

        map.setCurrentPosition(map.getStart());
        HashMap<Integer, ArrayList<ArrayList<Character>>> uniqueStates = new HashMap<Integer, ArrayList<ArrayList<Character>>>();
        ArrayList<SearchNode> closed = new ArrayList<SearchNode>();
        ArrayList<SearchNode> open = new ArrayList<SearchNode>();
        SearchNode initialSearchNode = new SearchNode(map);
        initialSearchNode.open();
        open.add(initialSearchNode);
        uniqueStates.put(map.getCurrentPosition().getX()*100+map.getCurrentPosition().getY(), map.getState());

        int counter = 0;
        while (!open.isEmpty()) {
            //System.out.println("Counter: "+counter);
            SearchNode currentSearchNode = open.get(0);
            open.remove(0);
            currentSearchNode.close();
            closed.add(currentSearchNode);

            if (problem.getGoalTest().isGoalState(currentSearchNode.getState())) {
                SearchNode parent = currentSearchNode;
                ArrayList<Position> solutionChain = new ArrayList<Position>();
                while (parent != null) {
                    solutionChain.add(solutionChain.size(), parent.getState().getCurrentPosition());
                    parent = parent.getParent();
                }
                GUI.createAndShowGUI(currentSearchNode.getState().getState(), solutionChain, open, closed, isShouldDrawOpenAndClosedNodes());
                System.out.println("OPEN: "+open.size());
                System.out.println("CLOSED: "+closed.size());
                break;
            }

            ArrayList<SearchNode> successors = generateAllSuccessors(currentSearchNode, problem);
            for (SearchNode successor : successors) {
                if (uniqueStates.get(successor.getState().getCurrentPosition().getX()*100+successor.getState().getCurrentPosition().getY()) == null) {
                    uniqueStates.put(successor.getState().getCurrentPosition().getX()*100+successor.getState().getCurrentPosition().getY(), map.getState());
                } else {
                    for (SearchNode closedNode : closed) {
                        if (closedNode.getState().getCurrentPosition().equals(successor.getState().getCurrentPosition())) {
                            successor = closedNode;
                        }
                    }
                    for (SearchNode openNode : open) {
                        if (openNode.getState().getCurrentPosition().equals(successor.getState().getCurrentPosition())) {
                            successor = openNode;
                        }
                    }
                }
                currentSearchNode.getKids().add(successor);
                if (successor.getStatus() != Status.OPEN && successor.getStatus() != Status.CLOSED) {
                    AttachAndEval(successor, currentSearchNode);
                    successor.open();
                    open.add(successor);
                    Collections.sort(open);
                } else if (currentSearchNode.getG() + successor.getState().getGCost() < successor.getG()) {
                    AttachAndEval(successor, currentSearchNode);
                    if (successor.getStatus() == Status.CLOSED) {
                        PropagatePathImprovements(successor);
                    }
                }
            }
            counter++;
        }


    }
}
