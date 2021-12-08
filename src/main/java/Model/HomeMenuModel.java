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

public class HomeMenuModel extends JComponent{
    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 1.1";
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

    public static Font getGreetingsFont() {
        return greetingsFont;
    }

    public static void setGreetingsFont(Font greetingsFont) {
        HomeMenuModel.greetingsFont = greetingsFont;
    }

    public static Font getGameTitleFont() {
        return gameTitleFont;
    }

    public static void setGameTitleFont(Font gameTitleFont) {
        HomeMenuModel.gameTitleFont = gameTitleFont;
    }

    public static Font getCreditsFont() {
        return creditsFont;
    }

    public static void setCreditsFont(Font creditsFont) {
        HomeMenuModel.creditsFont = creditsFont;
    }

    public static Font getButtonFont() {
        return buttonFont;
    }

    public static void setButtonFont(Font buttonFont) {
        HomeMenuModel.buttonFont = buttonFont;
    }

    public static boolean isStartClicked() {
        return startClicked;
    }

    public static void setStartClicked(boolean startClicked) {
        HomeMenuModel.startClicked = startClicked;
    }

    public static Rectangle getExitButton() {
        return exitButton;
    }

    public static void setExitButton(Rectangle exitButton) {
        HomeMenuModel.exitButton = exitButton;
    }

    public static Rectangle getInfoButton() {
        return infoButton;
    }

    public static void setInfoButton(Rectangle infoButton) {
        HomeMenuModel.infoButton = infoButton;
    }

    public static Rectangle getHighScoreButton() {
        return highScoreButton;
    }

    public static void setHighScoreButton(Rectangle highScoreButton) {
        HomeMenuModel.highScoreButton = highScoreButton;
    }

    public static boolean isExitClicked() {
        return exitClicked;
    }

    public static void setExitClicked(boolean exitClicked) {
        HomeMenuModel.exitClicked = exitClicked;
    }

    public static boolean isInfoClicked() {
        return infoClicked;
    }

    public static void setInfoClicked(boolean infoClicked) {
        HomeMenuModel.infoClicked = infoClicked;
    }

    public static boolean isHighScoreClicked() {
        return highScoreClicked;
    }

    public static void setHighScoreClicked(boolean highScoreClicked) {
        HomeMenuModel.highScoreClicked = highScoreClicked;
    }

    public static GameFrame getOwner(){
        return owner;
    }

    public void setOwner(GameFrame owner){
        HomeMenuModel.owner = owner;
    }

    public void paint(Graphics g){
        new HomeMenuView(g);
    }

    public static Rectangle getExitFace() {
        return exitFace;
    }

    public void setExitFace(Rectangle exitFace) {
        HomeMenuModel.exitFace = exitFace;
    }

    public static Rectangle getStartButton() {
        return startButton;
    }

    public void setStartButton(Rectangle startButton) {
        HomeMenuModel.startButton = startButton;
    }

    public static Color getTextColor() {
        return TEXT_COLOR;
    }

    public static Color getClickedButtonColor() {
        return CLICKED_BUTTON_COLOR;
    }

    public static Color getClickedText() {
        return CLICKED_TEXT;
    }

    public static String getGreetings() {
        return GREETINGS;
    }

    public static String getGameTitle() {
        return GAME_TITLE;
    }

    public static String getCredits() {
        return CREDITS;
    }

    public static String getStartText() {
        return START_TEXT;
    }

    public static String getExitText() {
        return EXIT_TEXT;
    }

    public static String getInfoText() {
        return INFO_TEXT;
    }

    public static String getHighScoreText() {
        return HIGHSCORE_TEXT;
    }
}
