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
package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";

    private static GameBoardModel gameBoardModel;
    private static HomeMenuModel homeMenuModel;
    private static InfoModel infoModel;
    private static HighScore highScore;

    private boolean gaming;

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

    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenuModel);
        this.add(gameBoardModel,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


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

    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoardModel.onLostFocus();

    }

    public void infotoHomeMenu(){
        this.dispose();
        this.remove(infoModel);
        this.add(homeMenuModel,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    public void enableInfo(){
        //infoModel = new InfoModel(this,new Dimension(700,450));
        this.dispose();
        this.remove(homeMenuModel);
        this.add(infoModel,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    public void pauseMenutoHomeMenu(){
        this.dispose();
        this.remove(gameBoardModel);
        this.add(homeMenuModel,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    public void enableHighScore(){
        this.dispose();
        this.remove(homeMenuModel);
        this.add(highScore,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    public void highScoretoHomeMenu(){
        this.dispose();
        this.remove(highScore);
        this.add(homeMenuModel,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    private void setInfoModel(InfoModel infoModel){
        GameFrame.infoModel = infoModel;
    }

    public static InfoModel getInfoModel(){
        return infoModel;
    }

    private void setHighScore(HighScore highScore){
        GameFrame.highScore = highScore;
    }

    public static HighScore getHighScore(){
        return highScore;
    }

    private void setHomeMenuModel(HomeMenuModel homeMenuModel){
        GameFrame.homeMenuModel = homeMenuModel;
    }

    public static HomeMenuModel getHomeMenuModel(){
        return homeMenuModel;
    }

    private void setGameBoardModel(GameBoardModel gameBoardModel){
        GameFrame.gameBoardModel = gameBoardModel;
    }

    public static GameBoardModel getGameBoardModel(){
        return gameBoardModel;
    }
}
