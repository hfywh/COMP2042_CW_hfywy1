package Model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SteelBrickModelTest {
    SteelBrickModel steelBrickModel = new SteelBrickModel(new Point(),new Dimension());

    @Test
    void makeBrickFace() {
        assertEquals(steelBrickModel.makeBrickFace(new Point(), new Dimension()),steelBrickModel.getbrickFace());
    }

    @Test
    void repair() {
        steelBrickModel.repair();
        assertFalse(steelBrickModel.isBroken());
    }

    @Test
    void receiveImpact() {
        steelBrickModel.setRandomNumber(0.3);
        steelBrickModel.impact();
        assertTrue(steelBrickModel.isBroken());
    }

    @Test
    void immuneImpact() {
        steelBrickModel.setRandomNumber(0.4);
        steelBrickModel.impact();
        assertFalse(steelBrickModel.isBroken());
    }
}