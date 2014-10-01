package com.search;

import com.search.algorithm.AStarSearch;
import com.search.algorithm.BreadthFirstSearch;
import com.search.algorithm.DijkstraSearch;
import com.search.problem.GridMap;
import com.search.problem.Problem;

import java.io.*;
import java.util.*;

public class Main {

    /** Function for reading .txt files of 2d grid maps into the GridMap state class */
    public static GridMap readFromFileIntoMap(String filePath) throws Exception {
        BufferedReader bufferedReader = null;
        try {
            String currentLine;
            bufferedReader = new BufferedReader(new FileReader(filePath));
            ArrayList<ArrayList<Character>> map = new ArrayList<ArrayList<Character>>();
            int row = 0;
            int width = 0;
            while ((currentLine = bufferedReader.readLine()) != null) {
                width = currentLine.length();
                ArrayList<Character> mapLine = new ArrayList<Character>();
                for (int col = 0; col<currentLine.length(); col++) {
                    mapLine.add(currentLine.charAt(col));
                }
                map.add(mapLine);
                row++;
            }

            map.toArray();
            GridMap gridMap = new GridMap(map, row, width);
            return gridMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Here one specifies the location of the 2d board, and which search function to use **/
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\Sondre\\Downloads\\boards\\boards\\board-2-4.txt";
        GridMap map = readFromFileIntoMap(filePath);
        Problem problem = new Problem(map);
        boolean shouldDrawOpenAndClosedNodes = true;
        AStarSearch search = new AStarSearch(problem, shouldDrawOpenAndClosedNodes);
        //BreadthFirstSearch search = new BreadthFirstSearch(problem, shouldDrawOpenAndClosedNodes);
        //DijkstraSearch search = new DijkstraSearch(problem, shouldDrawOpenAndClosedNodes);
        search.search();

    }
}
