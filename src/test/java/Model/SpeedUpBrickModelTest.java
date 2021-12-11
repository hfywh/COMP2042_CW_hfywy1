package Model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SpeedUpBrickModelTest {
    SpeedUpBrickModel speedUpBrickModel = new SpeedUpBrickModel(new Point(), new Dimension());

    @Test
    void makeBrickFace() {
        assertEquals(speedUpBrickModel.makeBrickFace(new Point(), new Dimension()),speedUpBrickModel.getbrickFace());
    }

    @Test
    void setImpact() {
        speedUpBrickModel.impact();
        assertTrue(speedUpBrickModel.isBroken());
    }

    @Test
    void repair() {
        speedUpBrickModel.repair();
        assertFalse(speedUpBrickModel.isBroken());
    }
}