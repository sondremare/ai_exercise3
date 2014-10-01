package com.search.algorithm;

import com.search.SearchNode;
import com.search.problem.Problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Sondre on 01.10.2014.
 */
public class DijkstraSearch extends BreadthFirstSearch{

    public DijkstraSearch(Problem problem, boolean shouldDrawOpenAndClosedNodes) {
        super(problem, shouldDrawOpenAndClosedNodes);
    }

    @Override
    public SearchNode popNode(ArrayList<SearchNode> nodes) {
        Comparator<SearchNode> hightestGValue = new Comparator<SearchNode>() {

            @Override
            public int compare(SearchNode sn1, SearchNode sn2) {
                if (sn1.getG() > sn2.getG()) return 1;
                else if (sn1.getG() < sn2.getG()) return -1;
                else return 0;
            }
        };

        Collections.sort(nodes, hightestGValue);
        return nodes.get(0);
    }
}
