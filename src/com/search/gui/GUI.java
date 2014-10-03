package com.search.gui;


import com.search.problem.Position;
import com.search.SearchNode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Sondre on 27.09.2014.
 */
public class GUI extends JPanel {
    private ArrayList<ArrayList<Character>> state;
    private ArrayList<Position> solutionChain;
    private ArrayList<SearchNode> open;
    private ArrayList<SearchNode> closed;
    private boolean shouldDrawOpenAndClosedNodes;
    private int margin = 10;
    private int cellWidth = 25;

    public GUI(ArrayList<ArrayList<Character>> state, ArrayList<Position> solutionChain,
               ArrayList<SearchNode> open,  ArrayList<SearchNode> closed, boolean shouldDrawOpenAndClosedNodes) {
        this.state = state;
        this.solutionChain = solutionChain;
        this.open = open;
        this.closed = closed;
        this.shouldDrawOpenAndClosedNodes = shouldDrawOpenAndClosedNodes;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /** Paints a basic 2d grid with obstacles (if any) and
         * certain background colors based on cell costs */
        for (int i = 0; i<state.size(); i++) {
            for (int j = 0; j<state.get(i).size(); j++) {
                g.setColor(getColor(state.get(i).get(j)));
                g.fillRect(margin + j*cellWidth, margin + i*cellWidth, cellWidth, cellWidth);
                g.setColor(Color.BLACK);
                g.drawRect(margin + j*cellWidth, margin + i*cellWidth, cellWidth, cellWidth);
            }
        }
        /** Paints the solution chain into the grid */
        for (int k = 0; k< solutionChain.size(); k++) {
            Position pos = solutionChain.get(k);
            g.setColor(Color.BLACK);
            g.fillOval(20 + pos.getY()*cellWidth, 20 + (pos.getX())*cellWidth, 8, 8);
        }

        /** Draws the position of the open and closed nodes if the option is enabled **/
      /*  if (shouldDrawOpenAndClosedNodes) {
            for (int l = 0; l < open.size(); l++) {
                Position pos = open.get(l).getState().getCurrentPosition();
                g.setColor(Color.BLACK);
                g.drawString("*", 20 + (pos.getY()) * cellWidth, 30 + (pos.getX()) * cellWidth);
            }
            for (int m = 0; m < closed.size(); m++) {
                Position pos = closed.get(m).getState().getCurrentPosition();
                boolean closedAndAlsoPartOfSolution = false;
                for (int n = 0; n < solutionChain.size(); n++) {
                    Position solutionPos = solutionChain.get(n);
                    if (solutionPos.equals(pos)) closedAndAlsoPartOfSolution = true;
                }
                if (!closedAndAlsoPartOfSolution) {
                    g.setColor(Color.BLACK);
                    g.drawString("x", 20 + (pos.getY()) * cellWidth, 30 + (pos.getX()) * cellWidth);
                }

            }
        }*/
    }

    private Color getColor(char character) {
        switch (character) {
            case '.':
                return Color.WHITE;
            case '#':
                return Color.BLACK;
            case 'A':
                return Color.RED;
            case 'B':
                return Color.GREEN;
            case 'X':
                return Color.YELLOW;
            case 'w':
                return new Color (77,77,255);
            case 'm':
                return new Color (166,166,166);
            case 'f':
                return new Color (0,128,0);
            case 'g':
                return new Color (128,255,128);
            case 'r':
                return new Color (191,128,64);
            default:
                return Color.WHITE;

        }
    }

    public static void createAndShowGUI(ArrayList<ArrayList<Character>> state, ArrayList<Position> solutionChain,
                                        ArrayList<SearchNode> open, ArrayList<SearchNode> closed, boolean shouldDrawOpenAndClosedNodes) {
        JFrame frame = new JFrame("A* Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        GUI newContentPane = new GUI(state, solutionChain, open, closed, shouldDrawOpenAndClosedNodes);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.setPreferredSize(new Dimension(1100,400));
        frame.pack();
        frame.setVisible(true);

    }
}
