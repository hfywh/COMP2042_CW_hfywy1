package Model;

import Controller.GameFrame;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class HomeMenuModelTest {
    HomeMenuModel homeMenuModel = new HomeMenuModel(new GameFrame(), new Dimension());//run the class to test the functionalities

    @Test
    void getGreetingsFont() {
        HomeMenuModel.setGreetingsFont(new Font("Monospaced",Font.PLAIN,10));
        assertEquals(new Font("Monospaced",Font.PLAIN,10),HomeMenuModel.getGreetingsFont());
    }

    @Test
    void setGreetingsFont() {
        HomeMenuModel.setGreetingsFont(new Font("Monospaced",Font.PLAIN,10));
        assertEquals(new Font("Monospaced",Font.PLAIN,10),HomeMenuModel.getGreetingsFont());
    }

    @Test
    void getGameTitleFont() {
        HomeMenuModel.setGameTitleFont(new Font("Monospaced",Font.PLAIN,10));
        assertEquals(new Font("Monospaced",Font.PLAIN,10),HomeMenuModel.getGameTitleFont());
    }

    @Test
    void setGameTitleFont() {
        HomeMenuModel.setGameTitleFont(new Font("Monospaced",Font.PLAIN,10));
        assertEquals(new Font("Monospaced",Font.PLAIN,10),HomeMenuModel.getGameTitleFont());
    }

    @Test
    void getCreditsFont() {
        HomeMenuModel.setCreditsFont(new Font("Monospaced",Font.PLAIN,10));
        assertEquals(new Font("Monospaced",Font.PLAIN,10),HomeMenuModel.getCreditsFont());
    }

    @Test
    void setCreditsFont() {
        HomeMenuModel.setCreditsFont(new Font("Monospaced",Font.PLAIN,10));
        assertEquals(new Font("Monospaced",Font.PLAIN,10),HomeMenuModel.getCreditsFont());
    }

    @Test
    void getButtonFont() {
        HomeMenuModel.setButtonFont(new Font("Monospaced",Font.PLAIN,10));
        assertEquals(new Font("Monospaced",Font.PLAIN,10),HomeMenuModel.getButtonFont());
    }

    @Test
    void setButtonFont() {
        HomeMenuModel.setButtonFont(new Font("Monospaced",Font.PLAIN,10));
        assertEquals(new Font("Monospaced",Font.PLAIN,10),HomeMenuModel.getButtonFont());
    }

    @Test
    void isStartClicked() {
        assertFalse(HomeMenuModel.isStartClicked());//default button not clicked
        HomeMenuModel.setStartClicked(true);
        assertTrue(HomeMenuModel.isStartClicked());
        HomeMenuModel.setStartClicked(false);
        assertFalse(HomeMenuModel.isStartClicked());
    }

    @Test
    void setStartClicked() {
        assertFalse(HomeMenuModel.isStartClicked());//default button not clicked
        HomeMenuModel.setStartClicked(true);
        assertTrue(HomeMenuModel.isStartClicked());
        HomeMenuModel.setStartClicked(false);
        assertFalse(HomeMenuModel.isStartClicked());
    }

    @Test
    void getExitButton() {
        HomeMenuModel.setExitButton(new Rectangle());
        assertEquals(new Rectangle(),HomeMenuModel.getExitButton());
    }

    @Test
    void setExitButton() {
        HomeMenuModel.setExitButton(new Rectangle());
        assertEquals(new Rectangle(),HomeMenuModel.getExitButton());
    }

    @Test
    void getInfoButton() {
        HomeMenuModel.setInfoButton(new Rectangle());
        assertEquals(new Rectangle(),HomeMenuModel.getInfoButton());
    }

    @Test
    void setInfoButton() {
        HomeMenuModel.setInfoButton(new Rectangle());
        assertEquals(new Rectangle(),HomeMenuModel.getInfoButton());
    }

    @Test
    void getHighScoreButton() {
        HomeMenuModel.setHighScoreButton(new Rectangle());
        assertEquals(new Rectangle(),HomeMenuModel.getHighScoreButton());
    }

    @Test
    void setHighScoreButton() {
        HomeMenuModel.setHighScoreButton(new Rectangle());
        assertEquals(new Rectangle(),HomeMenuModel.getHighScoreButton());
    }

    @Test
    void isExitClicked() {
        assertFalse(HomeMenuModel.isExitClicked());//default button not clicked
        HomeMenuModel.setExitClicked(true);
        assertTrue(HomeMenuModel.isExitClicked());
        HomeMenuModel.setExitClicked(false);
        assertFalse(HomeMenuModel.isExitClicked());
    }

    @Test
    void setExitClicked() {
        assertFalse(HomeMenuModel.isExitClicked());//default button not clicked
        HomeMenuModel.setExitClicked(true);
        assertTrue(HomeMenuModel.isExitClicked());
        HomeMenuModel.setExitClicked(false);
        assertFalse(HomeMenuModel.isExitClicked());
    }

    @Test
    void isInfoClicked() {
        assertFalse(HomeMenuModel.isInfoClicked());//default button not clicked
        HomeMenuModel.setInfoClicked(true);
        assertTrue(HomeMenuModel.isInfoClicked());
        HomeMenuModel.setInfoClicked(false);
        assertFalse(HomeMenuModel.isInfoClicked());
    }

    @Test
    void setInfoClicked() {
        assertFalse(HomeMenuModel.isInfoClicked());//default button not clicked
        HomeMenuModel.setInfoClicked(true);
        assertTrue(HomeMenuModel.isInfoClicked());
        HomeMenuModel.setInfoClicked(false);
        assertFalse(HomeMenuModel.isInfoClicked());
    }

    @Test
    void isHighScoreClicked() {
        assertFalse(HomeMenuModel.isHighScoreClicked());//default button not clicked
        HomeMenuModel.setHighScoreClicked(true);
        assertTrue(HomeMenuModel.isHighScoreClicked());
        HomeMenuModel.setHighScoreClicked(false);
        assertFalse(HomeMenuModel.isHighScoreClicked());
    }

    @Test
    void setHighScoreClicked() {
        assertFalse(HomeMenuModel.isHighScoreClicked());//default button not clicked
        HomeMenuModel.setHighScoreClicked(true);
        assertTrue(HomeMenuModel.isHighScoreClicked());
        HomeMenuModel.setHighScoreClicked(false);
        assertFalse(HomeMenuModel.isHighScoreClicked());
    }

    @Test
    void getOwner() {
        assertNotNull(HomeMenuModel.getOwner());
    }

    @Test
    void setOwner() {
        assertNotNull(HomeMenuModel.getOwner());
    }

    @Test
    void getExitFace() {
        homeMenuModel.setExitFace(new Rectangle());
        assertEquals(new Rectangle(),HomeMenuModel.getExitFace());
    }

    @Test
    void setExitFace() {
        homeMenuModel.setExitFace(new Rectangle());
        assertEquals(new Rectangle(),HomeMenuModel.getExitFace());
    }

    @Test
    void getStartButton() {
        homeMenuModel.setStartButton(new Rectangle());
        assertEquals(new Rectangle(),HomeMenuModel.getStartButton());
    }

    @Test
    void setStartButton() {
        homeMenuModel.setStartButton(new Rectangle());
        assertEquals(new Rectangle(),HomeMenuModel.getStartButton());
    }

    @Test
    void getTextColor() {
        assertNotNull(HomeMenuModel.getTextColor());
    }

    @Test
    void getClickedButtonColor() {
        assertNotNull(HomeMenuModel.getClickedButtonColor());
    }

    @Test
    void getClickedText() {
        assertNotNull(HomeMenuModel.getClickedText());
    }

    @Test
    void getGreetings() {
        assertNotNull(HomeMenuModel.getGreetings());
    }

    @Test
    void getGameTitle() {
        assertNotNull(HomeMenuModel.getGameTitle());
    }

    @Test
    void getCredits() {
        assertNotNull(HomeMenuModel.getCredits());
    }

    @Test
    void getStartText() {
        assertNotNull(HomeMenuModel.getStartText());
    }

    @Test
    void getExitText() {
        assertNotNull(HomeMenuModel.getExitText());
    }

    @Test
    void getInfoText() {
        assertNotNull(HomeMenuModel.getInfoText());
    }

    @Test
    void getHighScoreText() {
        assertNotNull(HomeMenuModel.getHighScoreText());
    }
}