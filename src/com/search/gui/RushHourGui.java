package com.search.gui;


import com.search.SearchNode;
import com.search.problem.Car;
import com.search.problem.Position;
import com.search.problem.RushHourGridMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Sondre on 27.09.2014.
 */
public class RushHourGui extends JPanel {
    private ArrayList<RushHourGridMap> solution;
    private int padding = 10;
    private int margin = 10;
    private int cellWidth = 50;
    private int counter;

    public RushHourGui(ArrayList<RushHourGridMap> solution) {
        this.solution = solution;
        this.counter = solution.size()-1;
    }

    public void increment() {
        if (counter > 0) {
            counter--;
        }
    }

    public void checkIfDone() {
        if (counter == 0) JOptionPane.showMessageDialog(this,"JUHUUUUUU!");
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /** Paints a basic 2d grid with obstacles (if any) and
         * certain background colors based on cell costs */
        for (int i = 0; i< solution.get(counter).getHeight(); i++) {
            for (int j = 0; j< solution.get(counter).getWidth(); j++) {
                g.setColor(Color.BLACK);
                g.drawRect(padding + j * cellWidth, padding + i * cellWidth, cellWidth, cellWidth);
                g.setColor(Color.WHITE);
            }
        }
        for (int j = 0; j< solution.get(counter).getCars().size(); j++) {
            Car car = solution.get(counter).getCars().get(j);
            g.setColor(getColor(j));
            int xPos = car.getPosition().getX() * cellWidth + padding + margin;
            int yPos = car.getPosition().getY() * cellWidth + padding + margin;
            int width, height;
            if (car.isHorizontal()) {
                width = cellWidth*car.getLength() - 2*margin;
                height = cellWidth - 2*margin;
            } else {
                width = cellWidth - 2*margin;
                height = cellWidth*car.getLength() - 2*margin;
            }
            g.fillRect(xPos, yPos, width, height);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(car.getNumber()), xPos+padding, yPos+2*padding);


        }
    }

    private Color getColor(int number) {
        switch (number) {
            case 0:
                return Color.RED;
            case 1:
                return Color.YELLOW;
            case 2:
                return Color.PINK;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.ORANGE;
            case 5:
                return Color.BLUE;
            case 6:
                return Color.MAGENTA;
            case 7:
                return Color.BLUE;
            case 8:
                return Color.CYAN;
            case 9:
                return Color.BLACK;
            case 10:
                return new Color (148,0,211);
            case 11:
                return new Color (34,139,34);
            case 12:
                return Color.LIGHT_GRAY;
            case 13:
                return new Color (191,128,64);
            default:
                return Color.WHITE;

        }
    }

    public static JFrame createAndShowGUI(final ArrayList<RushHourGridMap> solution) {
        JFrame frame = new JFrame("Rush Hour");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        final RushHourGui newContentPane = new RushHourGui(solution);
        newContentPane.setOpaque(true); //content panes must be opaque

        JButton stepButton = new JButton("Next step");
        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newContentPane.increment();
                newContentPane.validate();
                newContentPane.repaint();
                newContentPane.checkIfDone();
            }
        });

        newContentPane.add(stepButton);
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.setPreferredSize(new Dimension(1100,400));
        frame.pack();
        frame.setVisible(true);
        return frame;

    }
}
