package com.search;

import com.search.algorithm.AStarSearch;
import com.search.algorithm.BreadthFirstSearch;
import com.search.algorithm.DijkstraSearch;
import com.search.gui.RushHourGui;
import com.search.problem.*;

import javax.swing.*;
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

    public static int nthIndexOf(String str, char c, int n) {
        int pos = str.indexOf(c, 0);
        while (n-- > 0 && pos != -1)
            pos = str.indexOf(c, pos+1);
        return pos;
    }

    public static RushHourGridMap readFromFileIntoRushHourPuzzle(String filePath) {
        BufferedReader bufferedReader = null;
        try {
            String currentLine;
            bufferedReader = new BufferedReader(new FileReader(filePath));
            ArrayList<Car> cars = new ArrayList<Car>();
            int counter = 0;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String orientation = currentLine.substring(nthIndexOf(currentLine,'(',0)+1, nthIndexOf(currentLine,',',0));
                String x = currentLine.substring(nthIndexOf(currentLine,',',0)+1, nthIndexOf(currentLine,',',1));
                String y = currentLine.substring(nthIndexOf(currentLine,',',1)+1, nthIndexOf(currentLine,',',2));
                String length = currentLine.substring(nthIndexOf(currentLine,',',2)+1, nthIndexOf(currentLine,')',0));

                boolean isHorizontal = (orientation.equals("0"));
                Position position = new Position(Integer.parseInt(x), Integer.parseInt(y));
                int lengthOfCar = Integer.parseInt(length);
                Car car = new Car(counter, position, lengthOfCar, isHorizontal);
                cars.add(car);
                counter++;
            }
            return new RushHourGridMap(cars);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Here one specifies the location of the 2d board, and which search function to use **/
    public static void main(String[] args) throws Exception {
        //String filePath = "D:\\Prosjekt\\School\\AI\\exercise3\\resources\\rushhourpuzzles\\easy-3.txt";
        //String filePath = "D:\\Prosjekt\\School\\AI\\exercise3\\resources\\rushhourpuzzles\\medium-1.txt";
        //String filePath = "D:\\Prosjekt\\School\\AI\\exercise3\\resources\\rushhourpuzzles\\hard-3.txt";
        String filePath = "D:\\Prosjekt\\School\\AI\\exercise3\\resources\\rushhourpuzzles\\expert-2.txt";
        RushHourGridMap gridMap = readFromFileIntoRushHourPuzzle(filePath);
        //RushHourGui.createAndShowGUI(gridMap);
        RushHourProblem problem = new RushHourProblem(gridMap);
        boolean shouldDrawOpenAndClosedNodes = true;
        //AStarSearch search = new AStarSearch(problem, shouldDrawOpenAndClosedNodes);
        //BreadthFirstSearch search = new BreadthFirstSearch(problem, shouldDrawOpenAndClosedNodes);
        DijkstraSearch search = new DijkstraSearch(problem, shouldDrawOpenAndClosedNodes);
        ArrayList<RushHourGridMap> solution = search.search();
        System.out.println(solution.size());
        JFrame frame = RushHourGui.createAndShowGUI(solution);
    }
}
