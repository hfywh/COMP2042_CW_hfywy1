package Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {
    Time time = new Time();

    @Test
    void resetGame() {
        time.resetGame();
        assertEquals(0,Time.getSeconds());
        assertEquals(0,Time.getMinutes());
        assertEquals(0,time.getTempMin());
        assertEquals(0,time.getTempSec());
        assertFalse(time.getPlaying());
    }

    @Test
    void setTempSec() {
        time.setTempSec(8);
        assertEquals(8,time.getTempSec());
    }

    @Test
    void getSeconds() {
        time.setSeconds(10);
        assertEquals(10, Time.getSeconds());
    }

    @Test
    void setSeconds() {
        time.setSeconds(10);
        assertEquals(10, Time.getSeconds());
    }

    @Test
    void setPlaying() {
        time.setPlaying(true);
        assertTrue(time.getPlaying());
        time.setPlaying(false);
        assertFalse(time.getPlaying());
    }

    @Test
    void getMinutes() {
        time.setMinutes(10);
        assertEquals(10, Time.getMinutes());
    }

    @Test
    void setMinutes() {
        time.setMinutes(10);
        assertEquals(10, Time.getMinutes());
    }

    @Test
    void setTempMin() {
        time.setTempMin(10);
        assertEquals(10, time.getTempMin());
    }

    @Test
    void getTempSec() {
        time.setTempSec(8);
        assertEquals(8,time.getTempSec());
    }

    @Test
    void getTempMin() {
        time.setTempMin(10);
        assertEquals(10, time.getTempMin());
    }
}