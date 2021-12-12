package Model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RubberBallTest {
    RubberBall rubberBall = new RubberBall(new Point());

    @Test
    void setSpeed() {
        rubberBall.setSpeed(3,-5);
        assertEquals(3,rubberBall.getSpeedX());
        assertEquals(-5,rubberBall.getSpeedY());
    }

    @Test
    void setXSpeed() {
        rubberBall.setXSpeed(2);
        assertEquals(2,rubberBall.getSpeedX());
    }

    @Test
    void setYSpeed() {
        rubberBall.setYSpeed(-10);
        assertEquals(-10,rubberBall.getSpeedY());
    }

    @Test
    void reverseX() {
        rubberBall.setXSpeed(3);
        rubberBall.reverseX();
        assertEquals(-3,rubberBall.getSpeedX());
    }

    @Test
    void reverseY() {
        rubberBall.setYSpeed(-10);
        rubberBall.reverseY();
        assertEquals(10,rubberBall.getSpeedY());
    }

    @Test
    void getBorderColor() {
        rubberBall.setBorderColor(Color.GREEN);
        assertEquals(Color.GREEN,rubberBall.getBorderColor());
    }

    @Test
    void getInnerColor() {
        rubberBall.setInnerColor(Color.GREEN);
        assertEquals(Color.GREEN,rubberBall.getInnerColor());
    }

}