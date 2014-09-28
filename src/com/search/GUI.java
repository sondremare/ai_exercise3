package com.search;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Sondre on 27.09.2014.
 */
public class GUI extends JPanel {
    ArrayList<ArrayList<Character>> state;
    ArrayList<Position> winningStreak;
    private int margin = 10;
    private int cellWidth = 25;

    public GUI(ArrayList<ArrayList<Character>> state, ArrayList<Position> winningStreak) {
        this.state = state;
        this.winningStreak = winningStreak;
    }

    public ArrayList<ArrayList<Character>> getState() {
        return state;
    }

    public void setState(ArrayList<ArrayList<Character>> state) {
        this.state = state;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i<state.size(); i++) {
            for (int j = 0; j<state.get(i).size(); j++) {
                g.setColor(getColor(state.get(i).get(j)));
                g.fillRect(margin + j*cellWidth, margin + i*cellWidth, cellWidth, cellWidth);
                g.setColor(Color.BLACK);
                g.drawRect(margin + j*cellWidth, margin + i*cellWidth, cellWidth, cellWidth);
            }
        }
        for (int k = 0; k<winningStreak.size(); k++) {
            Position pos = winningStreak.get(k);
            g.setColor(Color.BLACK);
            g.drawString("X",15 + (pos.getY())*cellWidth, 5 + (pos.getX()+1)*cellWidth);
        }
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

    public static void createAndShowGUI(ArrayList<ArrayList<Character>> state, ArrayList<Position> winningStreak) {
        JFrame frame = new JFrame("A* Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        GUI newContentPane = new GUI(state, winningStreak);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.setPreferredSize(new Dimension(1100,400));
        frame.pack();
        frame.setVisible(true);

    }
}
