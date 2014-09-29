package com.search;

import java.io.*;
import java.util.*;

public class Main {

    public static AreaMap readFromFileIntoMap(String filePath) throws Exception {
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
            AreaMap areaMap = new AreaMap(map, row, width);
            return areaMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void AttachAndEval(SearchNode successor, SearchNode parent) {
        successor.setParent(parent);
        successor.setG(parent.getG() + successor.getState().getGCost());
        successor.setH(successor.CalculateHeuristic());
        successor.setF(successor.getG() + successor.getH());
    }

    public static void PropagatePathImprovements(SearchNode successor) {
        for (SearchNode kid : successor.getKids()) {
            if (successor.getG() + successor.getState().getGCost() < kid.getG()) {
                kid.setParent(successor);
                kid.setG(successor.getG() + successor.getState().getGCost());
                kid.setF(kid.getG() + kid.getH());
                PropagatePathImprovements(kid);
            }
        }
    }

    public static ArrayList<SearchNode> generateAllSuccessors(SearchNode currentSearchNode, Problem problem) {
        ArrayList<SearchNode> children = new ArrayList<SearchNode>();
        for (Action action : Action.values()) {
            ResultFunction resultFunction = problem.getResultFunction();
            AreaMap state = resultFunction.result(action, currentSearchNode.getState());
            if (state != null) {
                SearchNode childSearchNode = new SearchNode(state,currentSearchNode.getG());
                children.add(childSearchNode);
            }
        }
        return children;
    }

    public static void main(String[] args) throws Exception {

        Problem problem = new Problem();
        String filePath = "C:\\Users\\Sondre\\Downloads\\boards\\boards\\board-2-4.txt";
        AreaMap map = readFromFileIntoMap(filePath);

        map.setCurrentPosition(map.getStart());
        HashMap<Integer, ArrayList<ArrayList<Character>>> uniqueStates = new HashMap<Integer, ArrayList<ArrayList<Character>>>();
        ArrayList<SearchNode> closed = new ArrayList<SearchNode>();
        ArrayList<SearchNode> open = new ArrayList<SearchNode>();
        SearchNode initialSearchNode = new SearchNode(map);
        initialSearchNode.open();
        open.add(initialSearchNode);
        uniqueStates.put(map.getCurrent().getX()*100+map.getCurrent().getY(), map.getState());

        int counter = 0;
        while (!open.isEmpty()) {
            //System.out.println("Counter: "+counter);
            SearchNode currentSearchNode = open.get(0);
            open.remove(0);
            currentSearchNode.close();
            closed.add(currentSearchNode);

            if (problem.getGoalTest().isGoalState(currentSearchNode.getState())) {
                SearchNode parent = currentSearchNode;
                ArrayList<Position> winningStreak = new ArrayList<Position>();
                while (parent != null) {
                    winningStreak.add(winningStreak.size(), parent.getState().getCurrent());
                    parent = parent.getParent();
                }
                GUI.createAndShowGUI(currentSearchNode.getState().getState(), winningStreak);
                break;
            }

            ArrayList<SearchNode> successors = generateAllSuccessors(currentSearchNode, problem);
            for (SearchNode successor : successors) {
                if (uniqueStates.get(successor.getState().getCurrent().getX()*100+successor.getState().getCurrent().getY()) == null) {
                    uniqueStates.put(successor.getState().getCurrent().getX()*100+successor.getState().getCurrent().getY(), map.getState());
                } else {
                    for (SearchNode node : closed) {
                        if (node.getState().getCurrent().equals(successor.getState().getCurrent())) {
                            successor = node;
                        }
                    }
                }
                currentSearchNode.getKids().add(successor); //TODO feil?
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
