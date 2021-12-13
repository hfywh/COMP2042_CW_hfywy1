package Model;

import org.junit.jupiter.api.Test;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CementBrickModelTest {
    CementBrickModel cementBrickModel = new CementBrickModel(new Point(), new Dimension());

    @Test
    void makeBrickFace() {
        assertEquals(cementBrickModel.makeBrickFace(new Point(), new Dimension()),cementBrickModel.getbrickFace());
    }

    @Test
    void setImpact() {
        cementBrickModel.impact();
        cementBrickModel.impact();
        assertTrue(cementBrickModel.isBroken());
    }

    @Test
    void repair() {
        cementBrickModel.repair();
        assertFalse(cementBrickModel.isBroken());
    }
}