package Model;

import Controller.GameFrame;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class HighScoreTest {
    GameFrame gameFrame = new GameFrame();

    @Test
    void getOwner() {
        HighScore.setOwner(gameFrame);
        assertEquals(gameFrame, HighScore.getOwner());
    }

    @Test
    void setOwner() {
        HighScore.setOwner(gameFrame);
        assertEquals(gameFrame, HighScore.getOwner());
    }

    @Test
    void getBackButton() {
        HighScore.setBackButton(new Rectangle());
        assertEquals(new Rectangle(),HighScore.getBackButton());
    }

    @Test
    void setBackButton() {
        HighScore.setBackButton(new Rectangle());
        assertEquals(new Rectangle(),HighScore.getBackButton());
    }

    @Test
    void isBackBtnClicked() {
        HighScore.setBackBtnClicked(true);
        assertTrue(HighScore.isBackBtnClicked());
        HighScore.setBackBtnClicked(false);
        assertFalse(HighScore.isBackBtnClicked());
    }

    @Test
    void setBackBtnClicked() {
        HighScore.setBackBtnClicked(true);
        assertTrue(HighScore.isBackBtnClicked());
        HighScore.setBackBtnClicked(false);
        assertFalse(HighScore.isBackBtnClicked());
    }
}