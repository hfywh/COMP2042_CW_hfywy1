package Controller;

import Model.BallModel;
import Model.RubberBall;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {
    PlayerController playerController = new PlayerController(new Point(300, 430), 150,10, new Rectangle(600,450));
    BallModel ball = new RubberBall(new Point(300,430));

    @Test
    void impact() {
        assertTrue(playerController.impact(ball));
    }

    @Test
    void moveLeft() {
        playerController.moveLeft();
        assertEquals(-5,playerController.getMoveAmount());
    }

    @Test
    void movRight() {
        playerController.movRight();
        assertEquals(5,playerController.getMoveAmount());
    }

    @Test
    void stop() {
        playerController.stop();
        assertEquals(0,playerController.getMoveAmount());
    }

    @Test
    void getBorderColor() {
        assertEquals(Color.GREEN.darker().darker(), playerController.getBorderColor());
    }

    @Test
    void getInnerColor() {
        assertEquals(new Color(0,124,0), playerController.getBorderColor());
    }
}