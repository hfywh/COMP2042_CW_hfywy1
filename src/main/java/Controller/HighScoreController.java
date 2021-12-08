package Controller;

import Controller.GameFrame;
import Model.HighScore;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class HighScoreController implements MouseListener, MouseMotionListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if(HighScore.getBackButton().contains(p)){
            HighScore.getOwner().highScoretoHomeMenu();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        if(HighScore.getBackButton().contains(p)) {
            HighScore.setBackBtnClicked(true);
            GameFrame.getHighScore().repaint(HighScore.getBackButton().x, HighScore.getBackButton().y, HighScore.getBackButton().width + 1, HighScore.getBackButton().height + 1);

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(HighScore.isBackBtnClicked()){
            HighScore.setBackBtnClicked(false);
            GameFrame.getHighScore().repaint(HighScore.getBackButton().x,HighScore.getBackButton().y,HighScore.getBackButton().width+1,HighScore.getBackButton().height+1);
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
        if(HighScore.getBackButton().contains(p))
            GameFrame.getHighScore().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            GameFrame.getHighScore().setCursor(Cursor.getDefaultCursor());
    }
}
