package Controller;

import Model.HighScore;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * controller for high score
 * listen to mouse of the user and give responses
 */
public class HighScoreController implements MouseListener, MouseMotionListener {
    /**
     * back to home menu if back button is clicked
     * @param e mouse clicked
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if(HighScore.getBackButton().contains(p)){
            HighScore.getOwner().highScoretoHomeMenu();
        }
    }

    /**
     * set the button and text to red if any buttons is pressed
     * @param e mouse pressed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        if(HighScore.getBackButton().contains(p)) {
            HighScore.setBackBtnClicked(true);
            GameFrame.getHighScore().repaint(HighScore.getBackButton().x, HighScore.getBackButton().y, HighScore.getBackButton().width + 1, HighScore.getBackButton().height + 1);

        }

    }

    /**
     * set the button and text to black if the buttons is not pressed
     * @param e mouse released
     */
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

    /**
     * set to hand cursor when the cursor is on the button
     * @param e mouse moved
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();
        if(HighScore.getBackButton().contains(p))
            GameFrame.getHighScore().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            GameFrame.getHighScore().setCursor(Cursor.getDefaultCursor());
    }
}
