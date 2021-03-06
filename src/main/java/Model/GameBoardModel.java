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
package Model;

import Controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;

/**
 * Make model of game board.
 * @author Yong Wei Hian
 * @since 12/11/2021
 * @version 1.2
 */
public class GameBoardModel extends JComponent implements KeyListener,MouseListener,MouseMotionListener {
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


    /**
     * Set up the game board
     * @param owner JFrame
     */
    public GameBoardModel(JFrame owner){
        super();

        strLen = 0;
        showPauseMenu = false;



        setMenuFont(new Font("Monospaced",Font.PLAIN,TEXT_SIZE));


        this.initialize();
        message = "";
        timeMessage = "";
        brickMessage = "";
        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),new Point(300,430));
        Levels level = new Levels(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2, wall);

        time = new Time();
        setDebugConsole(new DebugConsole(owner,wall,this));
        //initialize the first level
        level.nextLevel();

        gameTimer = new Timer(10,e ->{
            wall.move();
            wall.findImpacts();
            time.setPlaying(true);
            message = String.format("Bricks: %d Balls %d",wall.getBrickCount(),wall.getBallCount());
            timeMessage = String.format("Time: %02dm %02ds", Time.getMinutes(), Time.getSeconds());
            brickMessage = String.format("Score: %d", Wall.getTotalBrickDestroyed());
            if(wall.isBallLost()){
                if(wall.ballEnd()){
                    wall.wallReset();
                    time.setPlaying(false);
                    message = "Game over";
                    showPauseMenu = true;
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
                    message = "Go to Next Level";
                    time.setTempMin(Time.getMinutes());
                    time.setTempSec(Time.getSeconds());
                    timeMessage = "";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    level.nextLevel();
                }
                else{
                    message = "ALL WALLS DESTROYED";
                    timeMessage = String.format("Time taken: %02dm %02ds", Time.getMinutes(), Time.getSeconds());
                    brickMessage = String.format("Score: %d", Wall.getTotalBrickDestroyed());
                    time.setPlaying(false);
                    gameTimer.stop();
                    HighScore.sortScore();
                }
            }

            repaint();
        });

    }


    /**
     * Initialize game board
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    /**
     * Paint the game board
     * @param g Graphics
     */
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message,250,225);
        g2d.drawString(timeMessage,250,245);
        g2d.drawString(brickMessage,250,265);

        drawBall(wall.getBall(),g2d);

        for(BrickModel b : wall.getBricks())
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(wall.getPlayer(),g2d);

        if(showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Clear the game board
     * @param g2d Graphics
     */
    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    /**
     * Draw model of brick
     * @param brickModel Model of brick
     * @param g2d Graphics
     */
    private void drawBrick(BrickModel brickModel, Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brickModel.getInnerColor());
        g2d.fill(brickModel.getBrick());

        g2d.setColor(brickModel.getBorderColor());
        g2d.draw(brickModel.getBrick());


        g2d.setColor(tmp);
    }

    /**
     * Draw model of ball
     * @param ballModel Model of ball
     * @param g2d Graphics
     */
    private void drawBall(BallModel ballModel, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ballModel.getBallFace();

        g2d.setColor(ballModel.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ballModel.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * Draw player
     * @param p Player
     * @param g2d Graphics
     */
    private void drawPlayer(PlayerController p, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(PlayerController.getInnerColor());
        g2d.fill(s);

        g2d.setColor(PlayerController.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * Draw menu
     * @param g2d Graphics
     */
    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * Pause the game board
     * @param g2d Graphics
     */
    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * Draw pause menu
     * @param g2d Graphics
     */
    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(homeMenuButtonRect == null){
            homeMenuButtonRect = (Rectangle) continueButtonRect.clone();
            homeMenuButtonRect.setLocation(x,y-homeMenuButtonRect.height);
        }

        g2d.drawString(HOMEMENU,x,y);



        y *= 1.25;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * change the ui according to the key pressed
     * @param keyEvent key pressed
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                wall.getPlayer().moveLeft();
                break;
            case KeyEvent.VK_D:
                wall.getPlayer().movRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                time.setPlaying(false);
                repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu)
                    if(gameTimer.isRunning()) {
                        time.setPlaying(false);
                        gameTimer.stop();
                    }
                    else {
                        gameTimer.start();
                    }
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsole.setVisible(true);
            default:
                wall.getPlayer().stop();
        }
    }

    /**
     * stop when the key is released
     * @param keyEvent key release
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.getPlayer().stop();
    }

    /**
     * when mouse clicked on button of the pause menu perform related function
     * @param mouseEvent mouse click
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;
        if(continueButtonRect.contains(p)){
            showPauseMenu = false;
            repaint();
        }
        else if(restartButtonRect.contains(p)){
            message = "Restarting Game...";
            timeMessage = "";
            brickMessage = "";
            wall.ballReset();
            wall.wallReset();
            time.setSeconds(time.getTempSec());
            time.setMinutes(time.getTempMin());
            Wall.setTotalBrickDestroyed((Levels.getLevel() - 1) * wall.getBrickCount());
            showPauseMenu = false;
            repaint();
        }
        else if(homeMenuButtonRect.contains(p)){
            System.out.println("Home Menu Button");
            showPauseMenu = false;
            GameFrame obj = new GameFrame();
            obj.pauseMenutoHomeMenu();
        }
        else if(exitButtonRect.contains(p)){
            time.resetGame();
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * set to hand cursor when the cursor is on the buttons
     * @param mouseEvent mouse move
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(exitButtonRect != null && showPauseMenu) {
            if (exitButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p) || homeMenuButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * Get message
     * @return S Message
     */
    public static String getMessage() {
        return message;
    }

    /**
     * Set message
     * @param message Message
     */
    public static void setMessage(String message) {
        GameBoardModel.message = message;
    }

    /**
     * Get time message
     * @return Time message
     */
    public static String getTimeMessage() {
        return timeMessage;
    }

    /**
     * Set time message
     * @param timeMessage Time message
     */
    public static void setTimeMessage(String timeMessage) {
        GameBoardModel.timeMessage = timeMessage;
    }

    /**
     * Get brick message
     * @return Brick message
     */
    public static String getBrickMessage() {
        return brickMessage;
    }

    /**
     * Set brick message
     * @param brickMessage Brick message
     */
    public static void setBrickMessage(String brickMessage) {
        GameBoardModel.brickMessage = brickMessage;
    }

    /**
     * Get pause menu need to show or not
     * @return showing of pause menu
     */
    public static boolean isShowPauseMenu() {
        return showPauseMenu;
    }

    /**
     * Determine the showing of pause menu
     * @param showPauseMenu showing of pause menu
     */
    public static void setShowPauseMenu(boolean showPauseMenu) {
        GameBoardModel.showPauseMenu = showPauseMenu;
    }

    /**
     * Get menu font
     * @return Menu font
     */
    public static Font getMenuFont() {
        return menuFont;
    }

    /**
     * Set menu font
     * @param menuFont Menu font
     */
    public static void setMenuFont(Font menuFont) {
        GameBoardModel.menuFont = menuFont;
    }

    /**
     * Get length of string
     * @return Length of string
     */
    public static int getStrLen() {
        return strLen;
    }

    /**
     * Set length of string
     * @param strLen Length of string
     */
    public static void setStrLen(int strLen) {
        GameBoardModel.strLen = strLen;
    }

    /**
     * Get continue button
     * @return Continue button
     */
    public static Rectangle getContinueButtonRect() {
        return continueButtonRect;
    }

    /**
     * Set continue button
     * @param continueButtonRect Continue button
     */
    public static void setContinueButtonRect(Rectangle continueButtonRect) {
        GameBoardModel.continueButtonRect = continueButtonRect;
    }

    /**
     * Get restart button
     * @return Restart button
     */
    public static Rectangle getRestartButtonRect() {
        return restartButtonRect;
    }

    /**
     * Set restart button
     * @param restartButtonRect Restart button
     */
    public static void setRestartButtonRect(Rectangle restartButtonRect) {
        GameBoardModel.restartButtonRect = restartButtonRect;
    }

    /**
     * Get home menu button
     * @return Home Menu button
     */
    public static Rectangle getHomeMenuButtonRect() {
        return homeMenuButtonRect;
    }

    /**
     * Set home menu button
     * @param homeMenuButtonRect Home menu button
     */
    public static void setHomeMenuButtonRect(Rectangle homeMenuButtonRect) {
        GameBoardModel.homeMenuButtonRect = homeMenuButtonRect;
    }

    /**
     * Get exit button
     * @return Exit button
     */
    public static Rectangle getExitButtonRect() {
        return exitButtonRect;
    }

    /**
     * Set exit button
     * @param exitButtonRect Exit button
     */
    public static void setExitButtonRect(Rectangle exitButtonRect) {
        GameBoardModel.exitButtonRect = exitButtonRect;
    }

    /**
     * Get debug console
     * @return Debug console
     */
    public static DebugConsole getDebugConsole() {
        return debugConsole;
    }

    /**
     * Set debug console
     * @param debugConsole Debug console
     */
    public static void setDebugConsole(DebugConsole debugConsole) {
        GameBoardModel.debugConsole = debugConsole;
    }

    /**
     * Get wall
     * @return Wall
     */
    public static Wall getWall(){
        return wall;
    }

    /**
     * Get background color
     * @return Background color
     */
    public static Color getBackgroundColor(){
        return BG_COLOR;
    }

    /**
     * Get menu color
     * @return Menu color
     */
    public static Color getMenuColor(){
        return MENU_COLOR;
    }

    /**
     * Get pause string
     * @return Pause string
     */
    public static String getPause(){
        return PAUSE;
    }

    /**
     * Get continue string
     * @return Continue string
     */
    public static String getContinue(){
        return CONTINUE;
    }

    /**
     * Get restart string
     * @return Restart string
     */
    public static String getRestart(){
        return RESTART;
    }

    /**
     * Get home menu string
     * @return Home menu string
     */
    public static String getHomeMenu(){
        return HOMEMENU;
    }

    /**
     * Get exit string
     * @return Exit string
     */
    public static String getExit(){
        return EXIT;
    }

    /**
     * Get time
     * @return Time
     */
    public static Time getTime(){
        return time;
    }

    /**
     * Get game timer
     * @return Timer
     */
    public static Timer getGameTimer(){
        return gameTimer;
    }

    /**
     * Stop the game if the focus is lost
     */
    public void onLostFocus(){
        gameTimer.stop();
        time.setPlaying(false);
        message = "Focus Lost";
        repaint();
    }
}
