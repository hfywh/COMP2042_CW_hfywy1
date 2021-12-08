package test;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InfoController implements MouseListener, MouseMotionListener {

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(InfoModel.getBackButton().contains(p)){
            InfoModel.getOwner().infotoHomeMenu();

        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(InfoModel.getBackButton().contains(p)){
            InfoModel.backClicked = true;
            GameFrame.getInfoModel().repaint(InfoModel.getBackButton().x,InfoModel.getBackButton().y,InfoModel.getBackButton().width+1,InfoModel.getBackButton().height+1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(InfoModel.backClicked ){
            InfoModel.backClicked = false;
            GameFrame.getInfoModel().repaint(InfoModel.getBackButton().x,InfoModel.getBackButton().y,InfoModel.getBackButton().width+1,InfoModel.getBackButton().height+1);
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

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(InfoModel.getBackButton().contains(p))
            GameFrame.getInfoModel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            GameFrame.getInfoModel().setCursor(Cursor.getDefaultCursor());

    }

}