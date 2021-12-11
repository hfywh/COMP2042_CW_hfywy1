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

import Controller.GameFrame;
import Controller.HomeMenuController;
import View.HomeMenuView;

import javax.swing.*;
import java.awt.*;

/**
 * Consist all data regarding home menu scene.
 * @author Yong Wei Hian
 * @since 12/11/2021
 * @version 1.2
 */
public class HomeMenuModel extends JComponent{
    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 1.2";
    private static final String START_TEXT = "Start";
    private static final String EXIT_TEXT = "Exit";
    private static final String INFO_TEXT = "Info";
    private static final String HIGHSCORE_TEXT = "High Score";

    private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color TEXT_COLOR = new Color(58, 102, 189);
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;

    private static Rectangle exitFace;
    private static Rectangle startButton;
    private static Rectangle exitButton;
    private static Rectangle infoButton;
    private static Rectangle highScoreButton;

    private static Font greetingsFont;
    private static Font gameTitleFont;
    private static Font creditsFont;
    private static Font buttonFont;

    private static GameFrame owner;

    private static boolean startClicked;
    private static boolean exitClicked;
    private static boolean infoClicked;
    private static boolean highScoreClicked;

    /**
     * Set up home menu scene
     * @param owner Game frame
     * @param area Dimension of home menu
     */
    public HomeMenuModel(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(new HomeMenuController());
        this.addMouseMotionListener(new HomeMenuController());

        setOwner(owner);


        setExitFace(new Rectangle(new Point(0,0),area));
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        setStartButton(new Rectangle(btnDim));
        setExitButton(new Rectangle(btnDim));
        setInfoButton(new Rectangle(btnDim));
        setHighScoreButton(new Rectangle(btnDim));

        setGreetingsFont(new Font("Noto Mono",Font.PLAIN,25));
        setGameTitleFont(new Font("Noto Mono",Font.BOLD,40));
        setCreditsFont(new Font("Monospaced",Font.PLAIN,10));
        setButtonFont(new Font("Monospaced",Font.PLAIN,startButton.height-2));



    }

    /**
     * Get greetings font
     * @return Greetings font
     */
    public static Font getGreetingsFont() {
        return greetingsFont;
    }

    /**
     * Set greetings font
     * @param greetingsFont Greetings font
     */
    public static void setGreetingsFont(Font greetingsFont) {
        HomeMenuModel.greetingsFont = greetingsFont;
    }

    /**
     * Get game title font
     * @return Game title font
     */
    public static Font getGameTitleFont() {
        return gameTitleFont;
    }

    /**
     * Set game title font
     * @param gameTitleFont Game title font
     */
    public static void setGameTitleFont(Font gameTitleFont) {
        HomeMenuModel.gameTitleFont = gameTitleFont;
    }

    /**
     * Get credits font
     * @return Credits font
     */
    public static Font getCreditsFont() {
        return creditsFont;
    }

    /**
     * Set credits font
     * @param creditsFont Credits font
     */
    public static void setCreditsFont(Font creditsFont) {
        HomeMenuModel.creditsFont = creditsFont;
    }

    /**
     * Get button font
     * @return Button font
     */
    public static Font getButtonFont() {
        return buttonFont;
    }

    /**
     * Set button font
     * @param buttonFont Button font
     */
    public static void setButtonFont(Font buttonFont) {
        HomeMenuModel.buttonFont = buttonFont;
    }

    /**
     * Start button being clicked or not
     * @return Clicked of start button
     */
    public static boolean isStartClicked() {
        return startClicked;
    }

    /**
     * Set the status of start button
     * @param startClicked Clicked of start button
     */
    public static void setStartClicked(boolean startClicked) {
        HomeMenuModel.startClicked = startClicked;
    }

    /**
     * Get exit button
     * @return Exit button
     */
    public static Rectangle getExitButton() {
        return exitButton;
    }

    /**
     * Set exit button
     * @param exitButton Exit button
     */
    public static void setExitButton(Rectangle exitButton) {
        HomeMenuModel.exitButton = exitButton;
    }

    /**
     * Get info button
     * @return Info button
     */
    public static Rectangle getInfoButton() {
        return infoButton;
    }

    /**
     * Set info button
     * @param infoButton Info button
     */
    public static void setInfoButton(Rectangle infoButton) {
        HomeMenuModel.infoButton = infoButton;
    }

    /**
     * Get high score button
     * @return High score button
     */
    public static Rectangle getHighScoreButton() {
        return highScoreButton;
    }

    /**
     * Set high score button
     * @param highScoreButton High score button
     */
    public static void setHighScoreButton(Rectangle highScoreButton) {
        HomeMenuModel.highScoreButton = highScoreButton;
    }

    /**
     * Exit button being clicked or not
     * @return Clicked of exit button
     */
    public static boolean isExitClicked() {
        return exitClicked;
    }

    /**
     * Set the status of exit button
     * @param exitClicked Clicked of exit button
     */
    public static void setExitClicked(boolean exitClicked) {
        HomeMenuModel.exitClicked = exitClicked;
    }

    /**
     * Info button being clicked or not
     * @return Clicked of info button
     */
    public static boolean isInfoClicked() {
        return infoClicked;
    }

    /**
     * Set the status of info button
     * @param infoClicked Clicked of info button
     */
    public static void setInfoClicked(boolean infoClicked) {
        HomeMenuModel.infoClicked = infoClicked;
    }

    /**
     * High score button being clicked or not
     * @return Clicked of high score button
     */
    public static boolean isHighScoreClicked() {
        return highScoreClicked;
    }

    /**
     * Set the status of high score button
     * @param highScoreClicked Clicked of high score button
     */
    public static void setHighScoreClicked(boolean highScoreClicked) {
        HomeMenuModel.highScoreClicked = highScoreClicked;
    }

    /**
     * Get game frame
     * @return Game frame
     */
    public static GameFrame getOwner(){
        return owner;
    }

    /**
     * Set game frame
     * @param owner Game frame
     */
    public void setOwner(GameFrame owner){
        HomeMenuModel.owner = owner;
    }

    /**
     * Display home menu scene
     * @param g Graphics
     */
    public void paint(Graphics g){
        new HomeMenuView(g);
    }

    /**
     * Get exit face
     * @return Exit face
     */
    public static Rectangle getExitFace() {
        return exitFace;
    }

    /**
     * Set exit face
     * @param exitFace Exit face
     */
    public void setExitFace(Rectangle exitFace) {
        HomeMenuModel.exitFace = exitFace;
    }

    /**
     * Get start button
     * @return Start button
     */
    public static Rectangle getStartButton() {
        return startButton;
    }

    /**
     * Set start button
     * @param startButton Start button
     */
    public void setStartButton(Rectangle startButton) {
        HomeMenuModel.startButton = startButton;
    }

    /**
     * Get color of text
     * @return Color of text
     */
    public static Color getTextColor() {
        return TEXT_COLOR;
    }

    /**
     * Get color of clicked button
     * @return Color of clicked button
     */
    public static Color getClickedButtonColor() {
        return CLICKED_BUTTON_COLOR;
    }

    /**
     * Get color of clicked text
     * @return Color of clicked text
     */
    public static Color getClickedText() {
        return CLICKED_TEXT;
    }

    /**
     * Get greetings string
     * @return Greetings string
     */
    public static String getGreetings() {
        return GREETINGS;
    }

    /**
     * Get game title string
     * @return Game title string
     */
    public static String getGameTitle() {
        return GAME_TITLE;
    }

    /**
     * Get credits string
     * @return Credits string
     */
    public static String getCredits() {
        return CREDITS;
    }

    /**
     * Get start text
     * @return Start text
     */
    public static String getStartText() {
        return START_TEXT;
    }

    /**
     * Get exit text
     * @return Exit text
     */
    public static String getExitText() {
        return EXIT_TEXT;
    }

    /**
     * Get info text
     * @return Info text
     */
    public static String getInfoText() {
        return INFO_TEXT;
    }

    /**
     * Get high score text
     * @return High score text
     */
    public static String getHighScoreText() {
        return HIGHSCORE_TEXT;
    }
}
