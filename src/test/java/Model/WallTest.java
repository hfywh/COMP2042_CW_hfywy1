package Model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    Wall wall = new Wall(new Rectangle(0,0), new Point() );

    @Test
    void move() {
    }

    @Test
    void findImpacts() {
    }

    @Test
    void getBrickCount() {
        wall.setBrickCount(10);
        assertEquals(10,wall.getBrickCount());
    }

    @Test
    void getBallCount() {
        assertEquals(3, wall.getBallCount());
    }

    @Test
    void isBallLost() {
        assertFalse(wall.isBallLost());
    }

    @Test
    void ballReset() {
        assertNotEquals(0, wall.getBallCount());
        wall.setBallLost(true);
        assertTrue(wall.isBallLost());
        wall.ballReset();
        assertFalse(wall.isBallLost());
        assertEquals(3,wall.getBall().getSpeedX());
        assertEquals(-5,wall.getBall().getSpeedY());
    }

    @Test
    void ballEnd() {
        assertFalse(wall.ballEnd());
    }

    @Test
    void isDone() {
        assertTrue(wall.isDone());
    }

    @Test
    void resetBallCount() {
        wall.resetBallCount();
        assertEquals(3,wall.getBallCount());
    }
}