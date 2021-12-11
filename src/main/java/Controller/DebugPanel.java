/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Controller;

import Model.Wall;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * use to check the game
 */
public class DebugPanel extends JPanel {

    private static final Color DEF_BKG = Color.WHITE;


    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    /**
     * set up the game
     * @param wall Wall
     */
    public DebugPanel(Wall wall){

        initialize();

        Levels level = new Levels(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2, wall);

        JButton skipLevel = makeButton("Skip Level", e -> level.nextLevel());
        JButton resetBalls = makeButton("Reset Balls", e -> wall.resetBallCount());

        ballXSpeed = makeSlider(e -> wall.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(e -> wall.setBallYSpeed(ballYSpeed.getValue()));

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);

    }

    /**
     * initialize debug panel
     */
    private void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    /**
     * make JButton and add action listener
     * @param title name of the title
     * @param e action listener
     * @return
     */
    private JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return  out;
    }

    /**
     * make JSlider
     * @param e change listener
     * @return JSlider
     */
    private JSlider makeSlider(ChangeListener e){
        JSlider out = new JSlider(-4, 4);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    /**
     * set the speed of the ball
     * @param x speedX of ball
     * @param y speedY of ball
     */
    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }

}