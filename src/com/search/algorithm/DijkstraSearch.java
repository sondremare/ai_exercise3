package com.search.algorithm;

import com.search.SearchNode;
import com.search.problem.Problem;
import com.search.problem.RushHourProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Sondre on 01.10.2014.
 */
public class DijkstraSearch extends BreadthFirstSearch{

    public DijkstraSearch(RushHourProblem problem, boolean shouldDrawOpenAndClosedNodes) {
        super(problem, shouldDrawOpenAndClosedNodes);
    }
    /**
     * Overrides the popNode method from BreadthFirstSearch and adds sorting of OPEN nodes
     * based primarily on lowest G-value.
     * @param nodes - the list of currently open nodes.
     * @return ascending sorted list.
     */
    @Override
    public SearchNode popNode(ArrayList<SearchNode> nodes) {
        Comparator<SearchNode> highestGValue = new Comparator<SearchNode>() {

            @Override
            public int compare(SearchNode sn1, SearchNode sn2) {
                if (sn1.getG() > sn2.getG()) return 1;
                else if (sn1.getG() < sn2.getG()) return -1;
                else return 0;
            }
        };

        Collections.sort(nodes, highestGValue);
        return nodes.get(0);
    }
}
