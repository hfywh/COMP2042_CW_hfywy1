package Model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ClayBrickModelTest {

    ClayBrickModel clayBrickModel = new ClayBrickModel(new Point(), new Dimension());

    @Test
    void makeBrickFace() {
        assertEquals(clayBrickModel.makeBrickFace(new Point(), new Dimension()),clayBrickModel.getbrickFace());
    }

    @Test
    void setImpact() {
        clayBrickModel.impact();
        assertTrue(clayBrickModel.isBroken());
    }

    @Test
    void repair() {
        clayBrickModel.repair();
        assertFalse(clayBrickModel.isBroken());
    }
}