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
package View;

import Model.HomeMenuModel;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Main function of this class is to display home menu scene
 */
public class HomeMenuView implements ImageObserver {
    /**
     * Display home menu scene
     * @param g Graphics
     */
    public HomeMenuView(Graphics g) {

        paint(g);
    }

    /**
     * Display home menu scene
     * @param g Graphics
     */
    public void paint(Graphics g) {
        drawMenu((Graphics2D) g);
    }


    /**
     * Draw menu
     * @param g2d Graphics
     */
    public void drawMenu(Graphics2D g2d) {

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = HomeMenuModel.getExitFace().getX();
        double y = HomeMenuModel.getExitFace().getY();

        g2d.translate(x, y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x, -y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    /**
     * Display background
     * @param g2d Graphics
     */
    private void drawContainer(Graphics2D g2d) {
        BufferedImage homeMenuImage;
        try {
            homeMenuImage = ImageIO.read(getClass().getResource("/homeMenu.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2d.drawImage(homeMenuImage, 0, 0, 600, 450, this);

    }

    /**
     * Display all the text
     * @param g2d Graphics
     */
    private void drawText(Graphics2D g2d) {

        g2d.setColor(HomeMenuModel.getTextColor());

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = HomeMenuModel.getGreetingsFont().getStringBounds(HomeMenuModel.getGreetings(), frc);
        Rectangle2D gameTitleRect = HomeMenuModel.getGameTitleFont().getStringBounds(HomeMenuModel.getGameTitle(), frc);
        Rectangle2D creditsRect = HomeMenuModel.getCreditsFont().getStringBounds(HomeMenuModel.getCredits(), frc);

        int sX, sY;

        sX = (int) (HomeMenuModel.getExitFace().getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int) (HomeMenuModel.getExitFace().getHeight() / 5);

        g2d.setFont(HomeMenuModel.getGreetingsFont());
        g2d.drawString(HomeMenuModel.getGreetings(), sX, sY);

        sX = (int) (HomeMenuModel.getExitFace().getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(HomeMenuModel.getGameTitleFont());
        g2d.drawString(HomeMenuModel.getGameTitle(), sX, sY);

        sX = (int) (HomeMenuModel.getExitFace().getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(HomeMenuModel.getCreditsFont());
        g2d.drawString(HomeMenuModel.getCredits(), sX, sY);


    }

    /**
     * Display all the buttons
     * @param g2d Graphics
     */
    private void drawButton(Graphics2D g2d) {

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = HomeMenuModel.getButtonFont().getStringBounds(HomeMenuModel.getStartText(), frc);
        Rectangle2D mTxtRect = HomeMenuModel.getButtonFont().getStringBounds(HomeMenuModel.getExitText(), frc);
        Rectangle2D infoTxtRect = HomeMenuModel.getButtonFont().getStringBounds(HomeMenuModel.getInfoText(), frc);
        Rectangle2D highScoreTxtRect = HomeMenuModel.getButtonFont().getStringBounds(HomeMenuModel.getHighScoreText(), frc);

        g2d.setFont(HomeMenuModel.getButtonFont());

        int x = (HomeMenuModel.getExitFace().width - HomeMenuModel.getStartButton().width) / 2;
        int y = (int) ((HomeMenuModel.getExitFace().height - HomeMenuModel.getStartButton().height) * 0.8);

        HomeMenuModel.getStartButton().setLocation(x, y);

        x = (int) (HomeMenuModel.getStartButton().getWidth() - txtRect.getWidth()) / 2;
        y = (int) (HomeMenuModel.getStartButton().getHeight() - txtRect.getHeight()) / 2;

        x += HomeMenuModel.getStartButton().x;
        y += HomeMenuModel.getStartButton().y + (HomeMenuModel.getStartButton().height * 0.9);

        if (HomeMenuModel.isStartClicked()) {
            Color tmp = g2d.getColor();
            g2d.setColor(HomeMenuModel.getClickedButtonColor());
            g2d.draw(HomeMenuModel.getStartButton());
            g2d.setColor(HomeMenuModel.getClickedText());
            g2d.drawString(HomeMenuModel.getStartText(), x, y);
            g2d.setColor(tmp);
        } else {
            g2d.draw(HomeMenuModel.getStartButton());
            g2d.drawString(HomeMenuModel.getStartText(), x, y);
        }

        x = HomeMenuModel.getStartButton().x;
        y = HomeMenuModel.getStartButton().y;

        y *= 1.2;

        HomeMenuModel.getExitButton().setLocation(x, y);

        x = (int) (HomeMenuModel.getExitButton().getWidth() - mTxtRect.getWidth()) / 2;
        y = (int) (HomeMenuModel.getExitButton().getHeight() - mTxtRect.getHeight()) / 2;

        x += HomeMenuModel.getExitButton().x;
        y += HomeMenuModel.getExitButton().y + (HomeMenuModel.getStartButton().height * 0.9);

        if (HomeMenuModel.isExitClicked()) {
            Color tmp = g2d.getColor();

            g2d.setColor(HomeMenuModel.getClickedButtonColor());
            g2d.draw(HomeMenuModel.getExitButton());
            g2d.setColor(HomeMenuModel.getClickedText());
            g2d.drawString(HomeMenuModel.getExitText(), x, y);
            g2d.setColor(tmp);
        } else {
            g2d.draw(HomeMenuModel.getExitButton());
            g2d.drawString(HomeMenuModel.getExitText(), x, y);
        }

        x = HomeMenuModel.getStartButton().x;
        y = HomeMenuModel.getStartButton().y;

        y *= 0.8;

        HomeMenuModel.getInfoButton().setLocation(x, y);

        x = (int) (HomeMenuModel.getInfoButton().getWidth() - infoTxtRect.getWidth()) / 2;
        y = (int) (HomeMenuModel.getInfoButton().getHeight() - infoTxtRect.getHeight()) / 2;

        x += HomeMenuModel.getInfoButton().x;
        y += HomeMenuModel.getInfoButton().y + (HomeMenuModel.getStartButton().height * 0.9);

        if (HomeMenuModel.isInfoClicked()) {
            Color tmp = g2d.getColor();
            g2d.setColor(HomeMenuModel.getClickedButtonColor());
            g2d.draw(HomeMenuModel.getInfoButton());
            g2d.setColor(HomeMenuModel.getClickedText());
            g2d.drawString(HomeMenuModel.getInfoText(), x, y);
            g2d.setColor(tmp);
        } else {
            g2d.draw(HomeMenuModel.getInfoButton());
            g2d.drawString(HomeMenuModel.getInfoText(), x, y);
        }

        x = HomeMenuModel.getStartButton().x;
        y = HomeMenuModel.getStartButton().y;

        y *= 0.6;

        HomeMenuModel.getHighScoreButton().setLocation(x, y);

        x = (int) (HomeMenuModel.getHighScoreButton().getWidth() - highScoreTxtRect.getWidth()) / 2;
        y = (int) (HomeMenuModel.getHighScoreButton().getHeight() - highScoreTxtRect.getHeight()) / 2;

        x += HomeMenuModel.getHighScoreButton().x;
        y += HomeMenuModel.getHighScoreButton().y + (HomeMenuModel.getStartButton().height * 0.9);

        if (HomeMenuModel.isHighScoreClicked()) {
            Color tmp = g2d.getColor();
            g2d.setColor(HomeMenuModel.getClickedButtonColor());
            g2d.draw(HomeMenuModel.getHighScoreButton());
            g2d.setColor(HomeMenuModel.getClickedText());
            g2d.drawString(HomeMenuModel.getHighScoreText(), x, y);
            g2d.setColor(tmp);
        } else {
            g2d.draw(HomeMenuModel.getHighScoreButton());
            g2d.drawString(HomeMenuModel.getHighScoreText(), x, y);
        }

    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}