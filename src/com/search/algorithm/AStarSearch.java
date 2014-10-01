package com.search.algorithm;

import com.search.SearchNode;
import com.search.problem.Problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Sondre on 01.10.2014.
 */
public class AStarSearch extends BreadthFirstSearch{

    public AStarSearch(Problem problem, boolean shouldDrawOpenAndClosedNodes) {
        super(problem, shouldDrawOpenAndClosedNodes);
    }

    @Override
    public SearchNode popNode(ArrayList<SearchNode> nodes) {
        Comparator<SearchNode> lowestFAndHValues = new Comparator<SearchNode>() {

            @Override
            public int compare(SearchNode sn1, SearchNode sn2) {
                if (sn1.getF() > sn2.getF()) return 1;
                else if (sn1.getF() < sn2.getF()) return -1;
                else {
                    if (sn1.getH() > sn2.getH()) return 1;
                    else if (sn1.getH() < sn2.getH()) return -1;
                    else return 0;
                }
            }
        };

        Collections.sort(nodes, lowestFAndHValues);
        return nodes.get(0);
    }
}
