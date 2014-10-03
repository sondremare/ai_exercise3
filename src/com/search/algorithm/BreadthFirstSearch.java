package com.search.algorithm;

import com.search.SearchNode;
import com.search.gui.GUI;
import com.search.gui.RushHourGui;
import com.search.problem.*;
import com.search.problem.Action;

import javax.swing.*;
import java.util.*;

public class BreadthFirstSearch {
    private RushHourProblem problem;
    private boolean shouldDrawOpenAndClosedNodes;

    public BreadthFirstSearch(RushHourProblem problem, boolean shouldDrawOpenAndClosedNodes) {
        this.problem = problem;
        this.shouldDrawOpenAndClosedNodes = shouldDrawOpenAndClosedNodes;
    }

    public RushHourProblem getProblem() {
        return this.problem;
    }

    /** Used to decide if the GUI component should draw the open and closed nodes
     * TODO: move GUI code out of this class */
    public boolean isShouldDrawOpenAndClosedNodes() {
        return this.shouldDrawOpenAndClosedNodes;
    }

    /** Used to attach a node to a given parent, and calculate its G, H and F-values */
    public void AttachAndEval(SearchNode successor, SearchNode parent) {
        RushHourHeuristic heuristic = problem.getHeuristic();
        successor.setParent(parent);
        successor.setG(parent.getG() + successor.getState().getGCost());
        successor.setH(heuristic.calculateHeuristic(successor.getState()));
        successor.setF(successor.getG() + successor.getH());
    }

    /** If a "better" parent node is found for a given node, we set this new "better" node as parent,
     * recalculate the F and G values. This is done recursively for all nodes' children, and their children.
     */
    public void PropagatePathImprovements(SearchNode successor) {
        for (SearchNode kid : successor.getKids()) {
            if (successor.getG() + kid.getState().getGCost() < kid.getG()) {
                kid.setParent(successor);
                kid.setG(successor.getG() + kid.getState().getGCost());
                kid.setF(kid.getG() + kid.getH());
                PropagatePathImprovements(kid);
            }
        }
    }

    /** Generates all possible neighbouring nodes from a current node,
     * based on a set of problem specific actions, and a problem specific result function
     */
    public ArrayList<SearchNode> generateAllSuccessors(SearchNode currentSearchNode, RushHourProblem problem) {
        ArrayList<SearchNode> children = new ArrayList<SearchNode>();
        for (Car car : currentSearchNode.getState().getCars()) {
            for (Action action : Action.values()) {
                RushHourResultFunction resultFunction = problem.getResultFunction();
                RushHourGridMap state = resultFunction.result(action, car, currentSearchNode.getState());
                if (state != null) {
                    SearchNode childSearchNode = new SearchNode(state, currentSearchNode.getG(), problem.getHeuristic().calculateHeuristic(state));
                    children.add(childSearchNode);
                }
            }
        }
        return children;
    }

    /** Used to retrieve the next node to evaluate from the list of open nodes **/
    public SearchNode popNode(ArrayList<SearchNode> nodes) {
        return nodes.get(0);
    }

    /** This function tries to find a solution to a given problem using search */
    public void search() throws Exception {

        /** Initialization of the state, lists, and search node */
        RushHourProblem problem = getProblem();
        RushHourGridMap gridMap = problem.getGridMap();
        HashMap<Integer, RushHourGridMap> uniqueStates = new HashMap<Integer, RushHourGridMap>();
        ArrayList<SearchNode> closed = new ArrayList<SearchNode>();
        ArrayList<SearchNode> open = new ArrayList<SearchNode>();
        SearchNode initialSearchNode = new SearchNode(gridMap, 0, problem.getHeuristic().calculateHeuristic(gridMap));
        initialSearchNode.open();
        open.add(initialSearchNode);
        uniqueStates.put(gridMap.hashCode(), gridMap);
        //JFrame frame = RushHourGui.createAndShowGUI(gridMap);
        int counter = 0;
        while (!open.isEmpty()) {
            System.out.println("Counter: "+counter);
            SearchNode currentSearchNode = popNode(open);
            open.remove(currentSearchNode);
            currentSearchNode.close();
            closed.add(currentSearchNode);

            for (Car car : currentSearchNode.getState().getCars()) {
                //System.out.println(car);
            }

            /** only for painting **/
            gridMap = currentSearchNode.getState();
           //frame.validate();
           //frame.repaint();
            //frame.invalidate();

            /** We check if the current search node is in the desired goal state */
            if (problem.getGoalTest().isGoalState(currentSearchNode.getState())) {
                SearchNode parent = currentSearchNode;
                ArrayList<Position> solutionChain = new ArrayList<Position>();
                /** We iterate backwards from the goal state via the nodes' parents to find a chain of nodes
                 * which is the solution to the problem
                 */
                while (parent != null) {
                   // solutionChain.add(solutionChain.size(), parent.getState().getCurrentPosition());
                    parent = parent.getParent();
                }

                /** After the solution is found, we draw it visually */
               // GUI.createAndShowGUI(currentSearchNode.getState().getState(), solutionChain, open, closed, isShouldDrawOpenAndClosedNodes());
                break;
            }

            /** We generate all neighbouring/successor nodes of the current node */
            ArrayList<SearchNode> successors = generateAllSuccessors(currentSearchNode, problem);
            for (SearchNode successor : successors) {
                /** If the successor node has a unique state, we add it to the list of unique states
                 * if not, we fetch the node from either the OPEN list or the CLOSED list.
                 */
                if (uniqueStates.get(successor.getState().hashCode()) != null) {
                    uniqueStates.put(successor.hashCode(), successor.getState());
                } else {
                    for (SearchNode closedNode : closed) {
                        if (closedNode.getState().equals(successor.getState())) {
                            successor = closedNode;
                        }
                    }
                    for (SearchNode openNode : open) {
                        if (openNode.getState().equals(successor.getState())) {
                            successor = openNode;
                        }
                    }
                }
                currentSearchNode.getKids().add(successor);

                if (successor.getStatus() != Status.OPEN && successor.getStatus() != Status.CLOSED) {
                    AttachAndEval(successor, currentSearchNode);
                    successor.open();
                    open.add(successor);
                /** We check if the current search node is a "better" parent node to the successor node
                 * than that previous parent node */
                } else if (currentSearchNode.getG() + successor.getState().getGCost() < successor.getG()) {
                    AttachAndEval(successor, currentSearchNode);
                    /** If the successor node is closed, we need to propagate this "better" parent to all of its
                     * successor nodes */
                    if (successor.getStatus() == Status.CLOSED) {
                        PropagatePathImprovements(successor);
                    }
                }
            }
            counter++;
        }
    }
}
