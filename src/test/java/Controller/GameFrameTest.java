package Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameFrameTest {
    GameFrame gameFrame = new GameFrame();

    @Test
    void getInfoModel() {
        assertNotNull(GameFrame.getInfoModel());
    }

    @Test
    void getHighScore() {
        assertNotNull(GameFrame.getHighScore());
    }

    @Test
    void getHomeMenuModel() {
        assertNotNull(GameFrame.getHomeMenuModel());
    }

    @Test
    void getGameBoardModel() {
        assertNotNull(GameFrame.getGameBoardModel());
    }
}