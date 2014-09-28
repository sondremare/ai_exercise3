package com.search;

import java.io.*;
import java.util.*;

public class Main {

    public static AreaMap readFromFileIntoMap(String filePath) throws Exception {
        BufferedReader bufferedReader = null;
        try {
            String currentLine;
            bufferedReader = new BufferedReader(new FileReader(filePath));
            //HashMap<Position, Character> map = new HashMap<Position, Character>();
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
        successor.setG(parent.getG() + 1);
        successor.setH(successor.CalculateHeuristic());
        successor.setF(successor.getG() + successor.getH());
    }

    public static void PropagatePathImprovements(SearchNode successor) {
        for (SearchNode kid : successor.getKids()) {
            if (successor.getG() + 1 < kid.getG()) {
                kid.setParent(successor);
                kid.setG(successor.getG() + 1);
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
        String filePath = "C:\\Users\\Sondre\\Downloads\\boards\\boards\\board-1-3.txt";
        AreaMap map = readFromFileIntoMap(filePath);
        GUI.createAndShowGUI(map.getState());
        map.setCurrentPosition(map.getStart());
        HashMap<Integer, ArrayList<ArrayList<Character>>> uniqueStates = new HashMap<Integer, ArrayList<ArrayList<Character>>>();
        ArrayList<SearchNode> closed = new ArrayList<SearchNode>();
        ArrayList<SearchNode> open = new ArrayList<SearchNode>();
        SearchNode initialSearchNode = new SearchNode(map);
        initialSearchNode.open();
        open.add(initialSearchNode);
        //uniqueStates.put(map.getState().hashCode(), map.getState());
        uniqueStates.put(map.getCurrent().getX()*100+map.getCurrent().getY(), map.getState());

        int counter = 0;
        while (!open.isEmpty()) {
            System.out.println("Counter: "+counter);
            //if (open.size() == 0) break;
            SearchNode currentSearchNode = open.get(0);
            open.remove(0);
            currentSearchNode.close();
            closed.add(currentSearchNode);

            if (problem.getGoalTest().isGoalState(currentSearchNode.getState())) {
                //TODO DO A BACKTRACEfoundSolution = true;
                //System.out.println("WINNING");
                //System.out.println(currentSearchNode.getState().getCurrent());
                SearchNode parent = currentSearchNode;
                ArrayList<Position> winningStreak = new ArrayList<Position>();
                while (parent != null) {
                    //System.out.println(parent.getState().getCurrent());
                    winningStreak.add(winningStreak.size(), parent.getState().getCurrent());
                    parent = parent.getParent();
                }
                for (Position pos : winningStreak) {
                    currentSearchNode.getState().getState().get(pos.getX()).set(pos.getY(), 'X');

                }
                GUI.repaint();
                //System.out.println(currentSearchNode.getState());
                break;
            }

            ArrayList<SearchNode> successors = generateAllSuccessors(currentSearchNode, problem);
            for (SearchNode successor : successors) {
                //if (uniqueStates.get(successor.getState().hashCode()) == null) {
                if (uniqueStates.get(successor.getState().getCurrent().getX()*100+successor.getState().getCurrent().getY()) == null) {
                    //We have a unique state
                    uniqueStates.put(successor.getState().getCurrent().getX()*100+successor.getState().getCurrent().getY(), map.getState());

                }else {
                    for (SearchNode node : closed) {
                        if (node.getState().getCurrent().equals(successor.getState().getCurrent())) {
                            int torsk = 3;
                            successor = node;
                        }
                    }
                    if (closed.contains(successor)) {
                        int tore = 2;
                    }else {
                        int tommy = 2;
                    }
                }
                currentSearchNode.getKids().add(successor);
                if (successor.getStatus() != Status.OPEN && successor.getStatus() != Status.CLOSED) {
                    AttachAndEval(successor, currentSearchNode);
                    successor.open();
                    open.add(successor);
                    Collections.sort(open);
                } else if (currentSearchNode.getG() + 1 < successor.getG()) {
                    AttachAndEval(successor, currentSearchNode);
                    if (successor.getStatus() == Status.CLOSED) {
                        PropagatePathImprovements(successor);
                    }
                }
            }
            counter++;

            //System.out.println("current position:" +currentSearchNode.getState().getCurrent());
           // Thread.sleep(1000);
        }


    }
}
