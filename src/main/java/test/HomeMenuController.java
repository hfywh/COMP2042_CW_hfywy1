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

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class HomeMenuController implements MouseListener, MouseMotionListener{
    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if(HomeMenuModel.getStartButton().contains(p)){
            HomeMenuModel.getOwner().enableGameBoard();

        }
        else if(HomeMenuModel.getExitButton().contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        else if(HomeMenuModel.getInfoButton().contains(p)) {
            System.out.println("info button");
            HomeMenuModel.getOwner().enableInfo();
        }
        else if(HomeMenuModel.getHighScoreButton().contains(p)) {
            System.out.println("high score button");
            HomeMenuModel.getOwner().enableHighScore();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        if(HomeMenuModel.getStartButton().contains(p)){
            HomeMenuModel.setStartClicked(true);
            repaintStartBtn();

        }
        else if(HomeMenuModel.getExitButton().contains(p)){
            HomeMenuModel.setExitClicked(true);
            repaintExitBtn();
        }
        else if(HomeMenuModel.getInfoButton().contains(p)) {
            HomeMenuModel.setInfoClicked(true);
            repaintInfoBtn();
        }
        else if(HomeMenuModel.getHighScoreButton().contains(p)) {
            HomeMenuModel.setHighScoreClicked(true);
            repaintHighScoreBtn();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(HomeMenuModel.isStartClicked() ){
            HomeMenuModel.setStartClicked(false);
            repaintStartBtn();
        }
        else if(HomeMenuModel.isExitClicked()){
            HomeMenuModel.setExitClicked(false);
            repaintExitBtn();
        }
        else if(HomeMenuModel.isInfoClicked()) {
            HomeMenuModel.setInfoClicked(false);
            repaintInfoBtn();
        }
        else if(HomeMenuModel.isHighScoreClicked()) {
            HomeMenuModel.setHighScoreClicked(false);
            repaintHighScoreBtn();
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();
        if(HomeMenuModel.getStartButton().contains(p) || HomeMenuModel.getExitButton().contains(p) || HomeMenuModel.getInfoButton().contains(p) || HomeMenuModel.getHighScoreButton().contains(p))
            GameFrame.getHomeMenuModel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            GameFrame.getHomeMenuModel().setCursor(Cursor.getDefaultCursor());

    }

    private void repaintStartBtn(){
        GameFrame.getHomeMenuModel().repaint(HomeMenuModel.getStartButton().x,HomeMenuModel.getStartButton().y,HomeMenuModel.getStartButton().width+1,HomeMenuModel.getStartButton().height+1);
    }

    private void repaintExitBtn(){
        GameFrame.getHomeMenuModel().repaint(HomeMenuModel.getExitButton().x,HomeMenuModel.getExitButton().y,HomeMenuModel.getExitButton().width+1,HomeMenuModel.getExitButton().height+1);
    }

    private void repaintInfoBtn(){
        GameFrame.getHomeMenuModel().repaint(HomeMenuModel.getInfoButton().x, HomeMenuModel.getInfoButton().y, HomeMenuModel.getInfoButton().width + 1, HomeMenuModel.getInfoButton().height + 1);
    }

    private void repaintHighScoreBtn(){
        GameFrame.getHomeMenuModel().repaint(HomeMenuModel.getHighScoreButton().x, HomeMenuModel.getHighScoreButton().y, HomeMenuModel.getHighScoreButton().width + 1, HomeMenuModel.getHighScoreButton().height + 1);
    }
}
