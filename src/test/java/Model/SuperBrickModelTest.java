package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

class SuperBrickModelTest {
    SuperBrickModel superBrickModel = new SuperBrickModel(new Point(),new Dimension());

    @Test
    void makeBrickFace() {
        assertEquals(superBrickModel.makeBrickFace(new Point(), new Dimension()),superBrickModel.getbrickFace());
    }

    @Test
    void receiveImpact() {
        superBrickModel.setRandomNumber(0.3);
        superBrickModel.impact();
        superBrickModel.impact();
        assertTrue(superBrickModel.isBroken());
    }

    @Test
    void repair() {
        superBrickModel.repair();
        assertFalse(superBrickModel.isBroken());
    }

    @Test
    void immuneImpact() {
        superBrickModel.setRandomNumber(0.4);
        superBrickModel.impact(new Point(),1);
        superBrickModel.setRandomNumber(0.4);
        superBrickModel.impact(new Point(), 1);
        assertFalse(superBrickModel.isBroken());
    }
}