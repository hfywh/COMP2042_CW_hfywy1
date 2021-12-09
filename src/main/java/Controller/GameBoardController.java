package Controller;

import Model.GameBoardModel;
import Model.Wall;

import java.awt.*;
import java.awt.event.*;

/**
 * controller of game board
 * listen to key and mouse of the user and give responses
 */
public class GameBoardController implements KeyListener, MouseListener, MouseMotionListener {
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
                GameBoardModel.getWall().getPlayer().moveLeft();
                break;
            case KeyEvent.VK_D:
                GameBoardModel.getWall().getPlayer().movRight();
                break;
            case KeyEvent.VK_ESCAPE:
                GameBoardModel.setShowPauseMenu(!GameBoardModel.isShowPauseMenu());
                GameBoardModel.getTime().setPlaying(false);
                GameFrame.getGameBoardModel().repaint();
                GameBoardModel.getGameTimer().stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!GameBoardModel.isShowPauseMenu())
                    if(GameBoardModel.getGameTimer().isRunning()) {
                        GameBoardModel.getTime().setPlaying(false);
                        GameBoardModel.getGameTimer().stop();
                    }
                    else {
                        GameBoardModel.getGameTimer().start();
                    }
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    GameBoardModel.getDebugConsole().setVisible(true);
            default:
                GameBoardModel.getWall().getPlayer().stop();
        }
    }

    /**
     * stop when the key is released
     * @param keyEvent key release
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        GameBoardModel.getWall().getPlayer().stop();
    }

    /**
     * when mouse clicked on button of the pause menu perform related function
     * @param mouseEvent mouse click
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!GameBoardModel.isShowPauseMenu())
            return;
        if(GameBoardModel.getContinueButtonRect().contains(p)){
            GameBoardModel.setShowPauseMenu(false);
            GameFrame.getGameBoardModel().repaint();
        }
        else if(GameBoardModel.getRestartButtonRect().contains(p)){
            GameBoardModel.setMessage("Restarting Game...");
            GameBoardModel.setTimeMessage("");
            GameBoardModel.setBrickMessage("");
            GameBoardModel.getWall().ballReset();
            GameBoardModel.getWall().wallReset();
            GameBoardModel.getTime().setSeconds(GameBoardModel.getTime().getTempSec());
            GameBoardModel.getTime().setMinutes(GameBoardModel.getTime().getTempMin());
            Wall.setTotalBrickDestroyed((Levels.getLevel() - 1) * GameBoardModel.getWall().getBrickCount());
            GameBoardModel.setShowPauseMenu(false);
            GameFrame.getGameBoardModel().repaint();
        }
        else if(GameBoardModel.getHomeMenuButtonRect().contains(p)){
            System.out.println("Home Menu Button");
            GameBoardModel.setShowPauseMenu(false);
            GameFrame obj = new GameFrame();
            obj.pauseMenutoHomeMenu();
        }
        else if(GameBoardModel.getExitButtonRect().contains(p)){
            GameBoardModel.getTime().resetGame();
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
        if(GameBoardModel.getExitButtonRect() != null && GameBoardModel.isShowPauseMenu()) {
            if (GameBoardModel.getExitButtonRect().contains(p) || GameBoardModel.getContinueButtonRect().contains(p) || GameBoardModel.getRestartButtonRect().contains(p) || GameBoardModel.getHomeMenuButtonRect().contains(p))
                GameFrame.getGameBoardModel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                GameFrame.getGameBoardModel().setCursor(Cursor.getDefaultCursor());
        }
        else{
            GameFrame.getGameBoardModel().setCursor(Cursor.getDefaultCursor());
        }
    }
}
