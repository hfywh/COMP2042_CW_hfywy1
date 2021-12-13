package Model;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardModelTest {
    GameBoardModel gameBoardModel = new GameBoardModel(new JFrame());//use to make the game board model run to check functionalities

    @Test
    void getMessage() {
        GameBoardModel.setMessage("ABC");
        assertEquals("ABC",GameBoardModel.getMessage());
    }

    @Test
    void setMessage() {
        GameBoardModel.setMessage("ABC");
        assertEquals("ABC",GameBoardModel.getMessage());
    }

    @Test
    void getTimeMessage() {
        GameBoardModel.setTimeMessage("TIME");
        assertEquals("TIME",GameBoardModel.getTimeMessage());
    }

    @Test
    void setTimeMessage() {
        GameBoardModel.setTimeMessage("TIME");
        assertEquals("TIME",GameBoardModel.getTimeMessage());
    }

    @Test
    void getBrickMessage() {
        GameBoardModel.setBrickMessage("BRICK");
        assertEquals("BRICK",GameBoardModel.getBrickMessage());
    }

    @Test
    void setBrickMessage() {
        GameBoardModel.setBrickMessage("BRICK");
        assertEquals("BRICK",GameBoardModel.getBrickMessage());
    }

    @Test
    void isShowPauseMenu() {
        assertFalse(GameBoardModel.isShowPauseMenu());//default not showing the pause menu
        GameBoardModel.setShowPauseMenu(true);
        assertTrue(GameBoardModel.isShowPauseMenu());
        GameBoardModel.setShowPauseMenu(false);
        assertFalse(GameBoardModel.isShowPauseMenu());
    }

    @Test
    void setShowPauseMenu() {
        assertFalse(GameBoardModel.isShowPauseMenu());//default not showing the pause menu
        GameBoardModel.setShowPauseMenu(true);
        assertTrue(GameBoardModel.isShowPauseMenu());
        GameBoardModel.setShowPauseMenu(false);
        assertFalse(GameBoardModel.isShowPauseMenu());
    }

    @Test
    void getMenuFont() {
        GameBoardModel.setMenuFont(new Font("Monospaced",Font.PLAIN,20));
        assertEquals(new Font("Monospaced",Font.PLAIN,20), GameBoardModel.getMenuFont());
    }

    @Test
    void setMenuFont() {
        GameBoardModel.setMenuFont(new Font("Monospaced",Font.PLAIN,20));
        assertEquals(new Font("Monospaced",Font.PLAIN,20), GameBoardModel.getMenuFont());
    }

    @Test
    void getStrLen() {
        GameBoardModel.setStrLen(5);
        assertEquals(5, GameBoardModel.getStrLen());
    }

    @Test
    void setStrLen() {
        GameBoardModel.setStrLen(5);
        assertEquals(5, GameBoardModel.getStrLen());
    }

    @Test
    void getContinueButtonRect() {
        GameBoardModel.setContinueButtonRect(new Rectangle());
        assertEquals(new Rectangle(), GameBoardModel.getContinueButtonRect());
    }

    @Test
    void setContinueButtonRect() {
        GameBoardModel.setContinueButtonRect(new Rectangle());
        assertEquals(new Rectangle(), GameBoardModel.getContinueButtonRect());
    }

    @Test
    void getRestartButtonRect() {
        GameBoardModel.setRestartButtonRect(new Rectangle());
        assertEquals(new Rectangle(), GameBoardModel.getRestartButtonRect());
    }

    @Test
    void setRestartButtonRect() {
        GameBoardModel.setRestartButtonRect(new Rectangle());
        assertEquals(new Rectangle(), GameBoardModel.getRestartButtonRect());
    }

    @Test
    void getHomeMenuButtonRect() {
        GameBoardModel.setHomeMenuButtonRect(new Rectangle());
        assertEquals(new Rectangle(), GameBoardModel.getHomeMenuButtonRect());
    }

    @Test
    void setHomeMenuButtonRect() {
        GameBoardModel.setHomeMenuButtonRect(new Rectangle());
        assertEquals(new Rectangle(), GameBoardModel.getHomeMenuButtonRect());
    }

    @Test
    void getExitButtonRect() {
        GameBoardModel.setExitButtonRect(new Rectangle());
        assertEquals(new Rectangle(), GameBoardModel.getExitButtonRect());
    }

    @Test
    void setExitButtonRect() {
        GameBoardModel.setExitButtonRect(new Rectangle());
        assertEquals(new Rectangle(), GameBoardModel.getExitButtonRect());
    }

    @Test
    void getDebugConsole() {
        assertNotNull(GameBoardModel.getDebugConsole());
    }

    @Test
    void setDebugConsole() {
        assertNotNull(GameBoardModel.getDebugConsole());
    }

    @Test
    void getWall() {
        assertNotNull(GameBoardModel.getWall());
    }

    @Test
    void getBackgroundColor() {
        assertNotNull(GameBoardModel.getBackgroundColor());
    }

    @Test
    void getMenuColor() {
        assertNotNull(GameBoardModel.getMenuColor());
    }

    @Test
    void getPause() {
        assertNotNull(GameBoardModel.getPause());
    }

    @Test
    void getContinue() {
        assertNotNull(GameBoardModel.getContinue());
    }

    @Test
    void getRestart() {
        assertNotNull(GameBoardModel.getRestart());
    }

    @Test
    void getHomeMenu() {
        assertNotNull(GameBoardModel.getHomeMenu());
    }

    @Test
    void getExit() {
        assertNotNull(GameBoardModel.getExit());
    }

    @Test
    void getTime() {
        assertNotNull(GameBoardModel.getTime());
    }

    @Test
    void getGameTimer() {
        assertNotNull(GameBoardModel.getGameTimer());
    }
}