package Controller;

import Model.Wall;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class LevelsTest {
    Wall wall = new Wall(new Rectangle(),new Point());
    Levels levels = new Levels(new Rectangle(),5 ,1,0.5,wall);

    @Test
    void nextLevel() {
        levels.nextLevel();
        assertEquals(5,wall.getBrickCount());
    }

    @Test
    void hasLevel() {
        assertTrue(levels.hasLevel());//level 1
        levels.nextLevel();
        assertTrue(levels.hasLevel());//level 2
        levels.nextLevel();
        assertTrue(levels.hasLevel());//level 3
        levels.nextLevel();
        assertTrue(levels.hasLevel());//level 4
        levels.nextLevel();
        assertTrue(levels.hasLevel());//level 5
        levels.nextLevel();
        assertTrue(levels.hasLevel());//level 6
        levels.nextLevel();
        assertFalse(levels.hasLevel());//level 7, which is not available
    }
}