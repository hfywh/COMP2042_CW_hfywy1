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
import java.awt.event.*;
import java.awt.font.FontRenderContext;

public class GameBoardModel extends JComponent {
    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String HOMEMENU = "Home Menu";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private static Timer gameTimer;

    private static Time time;

    private static Wall wall;

    private static String message;
    private static String timeMessage;
    private static String brickMessage;

    private static boolean showPauseMenu;

    private static Font menuFont;

    private static Rectangle continueButtonRect;
    private static Rectangle exitButtonRect;
    private static Rectangle restartButtonRect;
    private static Rectangle homeMenuButtonRect;
    private static int strLen;

    private static DebugConsole debugConsole;



    public GameBoardModel(JFrame owner){
        super();

        strLen = 0;
        setShowPauseMenu(false);



        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);


        this.initialize();
        setMessage("");
        setTimeMessage("");
        setBrickMessage("");
        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),new Point(300,430));
        Levels level = new Levels(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2, wall);

        time = new Time();
        //debugConsole = new DebugConsole(owner,wall,this);
        //initialize the first level
        level.nextLevel();

        gameTimer = new Timer(10,e ->{
            wall.move();
            wall.findImpacts();
            time.setPlaying(true);
            setMessage(String.format("Bricks: %d Balls %d",wall.getBrickCount(),wall.getBallCount()));
            setTimeMessage(String.format("Time: %02dm %02ds", time.getMinutes(), time.getSeconds()));
            setBrickMessage( String.format("Score: %d", Wall.getTotalBrickDestroyed()));
            if(wall.isBallLost()){
                if(wall.ballEnd()){
                    wall.wallReset();
                    time.setPlaying(false);
                    setMessage("Game over");
                    setShowPauseMenu(true);
                    HighScore.sortScore();
                    Wall.setTotalBrickDestroyed(0);
                    time.resetGame();
                }
                time.setPlaying(false);
                wall.ballReset();
                gameTimer.stop();
            }
            else if(wall.isDone()){
                if(level.hasLevel()){
                    setMessage("Go to Next Level");
                    time.setTempMin(time.getMinutes());
                    time.setTempSec(time.getSeconds());
                    setTimeMessage("");
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    level.nextLevel();
                }
                else{
                    setMessage("ALL WALLS DESTROYED");
                    setTimeMessage(String.format("Time taken: %02dm %02ds", time.getMinutes(), time.getSeconds()));
                    setBrickMessage(String.format("Score: %d", Wall.getTotalBrickDestroyed()));
                    time.setPlaying(false);
                    gameTimer.stop();
                    HighScore.sortScore();
                }
            }

            repaint();
        });

    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        GameBoardModel.message = message;
    }

    public static String getTimeMessage() {
        return timeMessage;
    }

    public static void setTimeMessage(String timeMessage) {
        GameBoardModel.timeMessage = timeMessage;
    }

    public static String getBrickMessage() {
        return brickMessage;
    }

    public static void setBrickMessage(String brickMessage) {
        GameBoardModel.brickMessage = brickMessage;
    }

    public static boolean isShowPauseMenu() {
        return showPauseMenu;
    }

    public static void setShowPauseMenu(boolean showPauseMenu) {
        GameBoardModel.showPauseMenu = showPauseMenu;
    }

    public static Font getMenuFont() {
        return menuFont;
    }

    public static void setMenuFont(Font menuFont) {
        GameBoardModel.menuFont = menuFont;
    }

    public static int getStrLen() {
        return strLen;
    }

    public static void setStrLen(int strLen) {
        GameBoardModel.strLen = strLen;
    }

    public static Rectangle getContinueButtonRect() {
        return continueButtonRect;
    }

    public static void setContinueButtonRect(Rectangle continueButtonRect) {
        GameBoardModel.continueButtonRect = continueButtonRect;
    }

    public static Rectangle getRestartButtonRect() {
        return restartButtonRect;
    }

    public static void setRestartButtonRect(Rectangle restartButtonRect) {
        GameBoardModel.restartButtonRect = restartButtonRect;
    }

    public static Rectangle getHomeMenuButtonRect() {
        return homeMenuButtonRect;
    }

    public static void setHomeMenuButtonRect(Rectangle homeMenuButtonRect) {
        GameBoardModel.homeMenuButtonRect = homeMenuButtonRect;
    }

    public static Rectangle getExitButtonRect() {
        return exitButtonRect;
    }

    public static void setExitButtonRect(Rectangle exitButtonRect) {
        GameBoardModel.exitButtonRect = exitButtonRect;
    }

    public static DebugConsole getDebugConsole() {
        return debugConsole;
    }

    public static void setDebugConsole(DebugConsole debugConsole) {
        GameBoardModel.debugConsole = debugConsole;
    }

    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new GameBoardController());
        this.addMouseListener(new GameBoardController());
        this.addMouseMotionListener(new GameBoardController());
    }

    public void paint(Graphics g){
        new GameBoardView(g);
    }

    public static Wall getWall(){
        return wall;
    }

    public static Color getBackgroundColor(){
        return BG_COLOR;
    }

    public static Color getMenuColor(){
        return MENU_COLOR;
    }

    public static String getPause(){
        return PAUSE;
    }

    public static String getContinue(){
        return CONTINUE;
    }

    public static String getRestart(){
        return RESTART;
    }

    public static String getHomeMenu(){
        return HOMEMENU;
    }

    public static String getExit(){
        return EXIT;
    }

    public static Time getTime(){
        return time;
    }

    public static Timer getGameTimer(){
        return gameTimer;
    }

    public void onLostFocus(){
        gameTimer.stop();
        time.setPlaying(false);
        message = "Focus Lost";
        repaint();
    }
}
