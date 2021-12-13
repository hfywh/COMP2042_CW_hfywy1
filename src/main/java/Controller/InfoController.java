package Controller;

import Model.InfoModel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Controller for the info,
 * listen to mouse of the user and give responses
 * @author Yong Wei Hian
 * @since 12/11/2021
 * @version 1.2
 */
public class InfoController implements MouseListener, MouseMotionListener {

    /**
     * when the back button is clicked, back to home menu
     * @param mouseEvent mouse clicked
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(InfoModel.getBackButton().contains(p)){
            InfoModel.getOwner().infotoHomeMenu();

        }
    }

    /**
     * set the color of button to gray and color of text to red if any buttons is pressed
     * @param mouseEvent mouse pressed
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(InfoModel.getBackButton().contains(p)){
            InfoModel.backClicked = true;
            repaintBackBtn();
        }
    }

    /**
     * set the color of button and color of text to white if any buttons is not pressed
     * @param mouseEvent mouse released
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(InfoModel.backClicked ){
            InfoModel.backClicked = false;
            repaintBackBtn();
        }
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
     * set to hand cursor when the cursor is on any buttons
     * @param mouseEvent mouse moved
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(InfoModel.getBackButton().contains(p))
            GameFrame.getInfoModel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            GameFrame.getInfoModel().setCursor(Cursor.getDefaultCursor());

    }

    /**
     * repaint back button
     */
    public void repaintBackBtn(){
        GameFrame.getInfoModel().repaint(InfoModel.getBackButton().x,InfoModel.getBackButton().y,InfoModel.getBackButton().width+1,InfoModel.getBackButton().height+1);
    }

}