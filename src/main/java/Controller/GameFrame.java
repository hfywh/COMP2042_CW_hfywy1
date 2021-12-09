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

import Model.GameBoardModel;
import Model.HighScore;
import Model.HomeMenuModel;
import Model.InfoModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


/**
 * set up JFrame for the game
 */
public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";

    private static GameBoardModel gameBoardModel;
    private static HomeMenuModel homeMenuModel;
    private static InfoModel infoModel;
    private static HighScore highScore;

    private boolean gaming;

    /**
     * set the UI for the whole game
     */
    public GameFrame(){
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        setGameBoardModel(new GameBoardModel(this));

        setHomeMenuModel(new HomeMenuModel(this,new Dimension(600,450)));

        setHighScore(new HighScore(this,new Dimension(600,450)));

        setInfoModel(new InfoModel(this,new Dimension(700,450)));

        this.add(homeMenuModel,BorderLayout.CENTER);

        this.setUndecorated(true);


    }

    /**
     * initialize game frame
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * enable game board
     */
    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenuModel);
        this.add(gameBoardModel,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    /**
     * auto locate the ui
     */
    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


    /**
     * set gaming to true when the window gained focus
     * @param windowEvent window gained focus
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    /**
     * when the window lost focus it will tell the game board to pause the game
     * @param windowEvent window lost focus
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoardModel.onLostFocus();

    }

    /**
     * change from info view to home menu view
     */
    public void infotoHomeMenu(){
        this.dispose();
        this.remove(infoModel);
        this.add(homeMenuModel,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * enable the info view
     */
    public void enableInfo(){
        this.dispose();
        this.remove(homeMenuModel);
        this.add(infoModel,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * back to home menu view through pause menu
     */
    public void pauseMenutoHomeMenu(){
        this.dispose();
        this.remove(gameBoardModel);
        this.add(homeMenuModel,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * display the high score
     */
    public void enableHighScore(){
        this.dispose();
        this.remove(homeMenuModel);
        this.add(highScore,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * switch back to home menu view from high score view
     */
    public void highScoretoHomeMenu(){
        this.dispose();
        this.remove(highScore);
        this.add(homeMenuModel,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * set the info model
     * @param infoModel info model
     */
    private void setInfoModel(InfoModel infoModel){
        GameFrame.infoModel = infoModel;
    }

    /**
     * get info model
     * @return info model
     */
    public static InfoModel getInfoModel(){
        return infoModel;
    }

    /**
     * set high score class
     * @param highScore high score
     */
    private void setHighScore(HighScore highScore){
        GameFrame.highScore = highScore;
    }

    /**
     * get high score class
     * @return high score
     */
    public static HighScore getHighScore(){
        return highScore;
    }

    /**
     * set home menu model
     * @param homeMenuModel home menu model
     */
    private void setHomeMenuModel(HomeMenuModel homeMenuModel){
        GameFrame.homeMenuModel = homeMenuModel;
    }

    /**
     * get home menu model
     * @return home menu model
     */
    public static HomeMenuModel getHomeMenuModel(){
        return homeMenuModel;
    }

    /**
     * set game board model
     * @param gameBoardModel game board model
     */
    private void setGameBoardModel(GameBoardModel gameBoardModel){
        GameFrame.gameBoardModel = gameBoardModel;
    }

    /**
     * get game board model
     * @return game board model
     */
    public static GameBoardModel getGameBoardModel(){
        return gameBoardModel;
    }
}
